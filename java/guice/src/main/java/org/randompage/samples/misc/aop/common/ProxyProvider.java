package org.randompage.samples.misc.aop.common;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Provider;
import com.google.inject.matcher.Matcher;
import com.google.inject.util.Objects;

public class ProxyProvider<T> implements Provider<T> {
	private final Provider<? extends T> delegateProvider;

	private final List<MethodAspect> aspects = new ArrayList<MethodAspect>();

	private final Map<Class<?>, Map<Method, List<MethodInterceptor>>> interceptorsCache = new WeakHashMap<Class<?>, Map<Method, List<MethodInterceptor>>>();

	public ProxyProvider(Provider<? extends T> delegateProvider) {
		this.delegateProvider = delegateProvider;
	}

	public ProxyProvider(Provider<? extends T> delegateProvider,
			Matcher<Method> methodMatcher, MethodInterceptor... interceptors) {
		this(delegateProvider);
		bindInterceptor(methodMatcher, interceptors);
	}

	public ProxyProvider<T> bindInterceptor(
			Matcher<? super Method> methodMatcher,
			MethodInterceptor... interceptors) {
		aspects.add(new MethodAspect(methodMatcher, interceptors));
		return this;
	}

	@SuppressWarnings("unchecked")
	public T get() {
		T delegate = delegateProvider.get();
		Class delegateClass = delegate.getClass();

		Map<Method, List<MethodInterceptor>> interceptors;
		synchronized (interceptorsCache) {
			if (interceptorsCache.containsKey(delegateClass)) {
				interceptors = interceptorsCache.get(delegateClass);
			} else {
				interceptors = new WeakHashMap<Method, List<MethodInterceptor>>();

				boolean anyMatched = false;
				for (Class<?> iface : delegateClass.getInterfaces()) {
					for (Method method : Arrays.asList(iface
							.getDeclaredMethods())) {
						for (MethodAspect methodAspect : aspects) {
							if (methodAspect.matches(method)) {
								List<MethodInterceptor> interceptorsList = interceptors
										.get(method);
								if (interceptorsList == null) {
									interceptorsList = new ArrayList<MethodInterceptor>(
											methodAspect.interceptors());
									interceptors.put(method, interceptorsList);
								} else {
									interceptorsList.addAll(methodAspect
											.interceptors());
								}
								anyMatched = true;
							}
						}
					}
				}

				if (!anyMatched) {
					interceptors = null;
				}
				interceptorsCache.put(delegateClass, interceptors);
			}

			if (interceptors == null) {
				return delegate;
			}

			return (T) Proxy.newProxyInstance(delegateClass.getClassLoader(),
					delegateClass.getInterfaces(),
					new DelegatingInvocationHandler(delegate, interceptors));
		}
	}

	private static class MethodAspect {
		final Matcher<? super Method> methodMatcher;

		final List<MethodInterceptor> interceptors;

		MethodAspect(Matcher<? super Method> methodMatcher,
				MethodInterceptor... interceptors) {
			this.methodMatcher = Objects.nonNull(methodMatcher,
					"method matcher");
			this.interceptors = Arrays.asList(Objects.nonNull(interceptors,
					"interceptors"));
		}

		boolean matches(Method method) {
			return methodMatcher.matches(method);
		}

		List<MethodInterceptor> interceptors() {
			return interceptors;
		}
	}

	private static class DelegatingInvocationHandler implements
			InvocationHandler {
		private final Object delegate;

		private final Map<Method, List<MethodInterceptor>> interceptors;

		public DelegatingInvocationHandler(Object delegate,
				Map<Method, List<MethodInterceptor>> interceptors) {
			this.delegate = delegate;
			this.interceptors = interceptors;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			List<MethodInterceptor> methodInterceptors = interceptors
					.get(method);
			if (methodInterceptors == null) {
				return method.invoke(delegate, args);
			} else {
				return new InterceptedMethodInvocation(delegate, method, args,
						methodInterceptors).proceed();
			}
		}
	}

	private static class InterceptedMethodInvocation implements
			MethodInvocation {
		final Object delegate;

		final Method method;

		final Object[] arguments;

		final List<MethodInterceptor> interceptors;

		int index = -1;

		public InterceptedMethodInvocation(Object delegate, Method method,
				Object[] arguments, List<MethodInterceptor> interceptors) {
			this.delegate = delegate;
			this.method = method;
			this.arguments = arguments;
			this.interceptors = interceptors;
		}

		public Object proceed() throws Throwable {
			try {
				index++;
				return index == interceptors.size() ? method.invoke(delegate,
						arguments) : interceptors.get(index).invoke(this);
			} finally {
				index--;
			}
		}

		public Method getMethod() {
			return method;
		}

		public Object[] getArguments() {
			return arguments;
		}

		public Object getThis() {
			return delegate;
		}

		public AccessibleObject getStaticPart() {
			return getMethod();
		}
	}
}
