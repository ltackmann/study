package org.randompage.samples.misc.aop;

import static com.google.inject.matcher.Matchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.randompage.samples.misc.aop.common.ProxyProvider;


import com.google.inject.Provider;

public class ProxyTest {
	@Test
	public void proxyTest() {
		final String val = "Hello World";
		Foo instance = new FooImpl(val);
		CountInterceptor countInterceptor = new CountInterceptor();
		ProxyProvider<Foo> provider = new ProxyProvider<Foo>(new FooProvider(instance))
			.bindInterceptor(any(), countInterceptor);
		
		Foo impl = provider.get();
		
		// the equals method is delegated
		assertThat(instance, equalTo(impl));
		
		// the memory addresses do however differ
		assertThat(instance == impl, equalTo(false));
		
		// IMPL is intercepted
		assertThat(impl.bar(), equalTo(val));
		assertThat(countInterceptor.count, equalTo(1));
		
		// instance is not intercepted
		assertThat(instance.bar(), equalTo(val));
		assertThat(countInterceptor.count, equalTo(1));
	}

	public static interface Foo {
		String bar();
	}

	static class FooImpl implements Foo {
		private final String string;
		FooImpl(String string) {
			this.string = string;
		}
		
		@Override
		public String bar() {
			return string;
		}
	}
	
	static class FooProvider implements Provider<Foo> {
		private final Foo instance;
		public FooProvider(Foo instance) {
			this.instance = instance;
		}
		
		@Override
		public Foo get() {
			return instance;
		}	
	}

	static class CountInterceptor implements MethodInterceptor {
		int count;
		
		public Object invoke(MethodInvocation m) throws Throwable {
			count++;
			return m.proceed();
		}
	}
}