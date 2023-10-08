package jpa.spring.utils;


import javax.annotation.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class SpringInjector {
	@Resource
	PlatformTransactionManager transactionManager;
	
    public SpringInjector(Class<?> springConfigClass) {
        // Start Spring
        final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(springConfigClass);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (ctx != null)
                	ctx.close();
            }
        });
        // Inject spring beans
        ctx.getAutowireCapableBeanFactory().autowireBean(this);
    }
        
    protected <T> T doInTransaction(TransactionCallback<T> transactionCallback) {
    	TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    	return transactionTemplate.execute(transactionCallback);
    }
}
