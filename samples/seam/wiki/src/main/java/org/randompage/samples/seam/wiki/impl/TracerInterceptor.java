package org.randompage.samples.seam.wiki.impl;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracerInterceptor {
	private Logger logger = Logger.getLogger(TracerInterceptor.class);
	private static int invocationCount = 0;

	@Around("@annotation(org.randompage.samples.seam.wiki.client.Tracer)")
	public Object doTrace(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.nanoTime();
		invocationCount++;
		
		try {
			return pjp.proceed();
		} finally {
			long time = System.nanoTime() - start;
			logger.info(pjp.toShortString() + " took: " + time);
		}
	}

	public static int getInvocationCount() {
		return invocationCount;
	}
}
