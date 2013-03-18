package org.randompage.security.impl.spring;

import java.lang.reflect.Type;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.randompage.security.ArgumentAuditor;
import org.randompage.security.ResultAuditor;
import org.randompage.security.annotations.Auditable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditInterceptor {
	@Autowired
	private ApplicationContext applicationContext;

	private Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);

	// spring injection
	private void autowire(Object target) {
		applicationContext.getAutowireCapableBeanFactory().autowireBean(target);
	}

	@Around("securityAnnotated()")
	public Object securityAdvice(ProceedingJoinPoint jp) throws Throwable {
		// retrieve principal
		SecurityContext ctx = SecurityContextHolder.getContext();
		String principal = ctx.getAuthentication().getName();

		// extract method information
		Object[] args = jp.getArgs();
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Auditable audit = signature.getMethod().getAnnotation(Auditable.class);
		String methodName = signature.getName();

		// is this a void method ?
		Type returnType = signature.getMethod().getGenericReturnType();
		final boolean voidMethod = returnType.equals(void.class);

		// use provided argument checker class if any
		if (!audit.argumentAuditor().equals(ArgumentAuditor.class)) {
			if (args == null || args.length == 0) {
				throw new IllegalStateException(
						"can only audit arguments for method that takes parameters");
			}
			ArgumentAuditor argAudit = audit.argumentAuditor().newInstance();
			autowire(argAudit);
			argAudit.setPrincipal(principal);
			argAudit.checkAuguments(args);
		}

		// continue execution
		Object res = null;
		try {
			res = jp.proceed();
			return res;
		} finally {
			// check return value if needed
			if (!audit.resultAuditor().equals(ResultAuditor.class)) {
				if (voidMethod) {
					throw new IllegalStateException(
							"cannot audit result from void method");
				}
				ResultAuditor resAudit = audit.resultAuditor().newInstance();
				autowire(resAudit);
				resAudit.setPrincipal(principal);
				resAudit.checkResult(res);
			}
			// log method signature, arguments and return value
			String returnValue = (res == null) ? "<null>" : res.toString();
			if (voidMethod)
				returnValue = "<void>";
			final String msg = String.format(
					"executing: %s as %s with args: '%s' which returned: %s",
					methodName, principal, args.toString(), returnValue);
			logger.debug(msg);
		}
	}

	@Pointcut("@annotation(org.randompage.security.Auditor)")
	public void securityAnnotated() {
	}
}
