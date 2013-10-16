package el;

import static org.junit.Assert.*;

import javax.el.ELContext;
import javax.el.ELProcessor;
import javax.el.LambdaExpression;
import javax.el.TypeConverter;

import org.junit.BeforeClass;
import org.junit.Test;

public class ElTest {
	private static ELProcessor elProcessor;
	
	@BeforeClass
	public static void setupTest() {
		elProcessor = new ELProcessor();
	}
	
	@Test
	public void testEl() {
		Object message = elProcessor.eval("hello += ${user.name}");
		// eval("Boolean.TRUE")
	}
	
	@Test
	public void testLambdaEl() {
		LambdaExpression increment = (LambdaExpression) elProcessor.eval("x -> x+1");
		assertEquals(increment.invoke(10), equals(11));
		
		LambdaExpression sum = (LambdaExpression) elProcessor.eval("(x,y) -> x + y");
		assertEquals(sum.invoke(3,4), equals(7));
		
		LambdaExpression constant = (LambdaExpression) elProcessor.eval("() -> 64");
		assertEquals(constant.invoke(), equals(64));
	}
	
	
	@Test
	public void customElConverter() {
		elProcessor.getELManager().addELResolver(new TypeConverter() {
			@Override
			public Object convertToType(ELContext context, Object obj, Class<?> type) {
				if(obj instanceof String && type == User.class) {
					context.setPropertyResolved(true);
					return new User((String)obj);
				}
				return null;
			}
		});
		User user =  (User) elProcessor.getValue("'John Doe'", User.class);
		assertEquals(user.name, "John Doe");
	}
}

class User {
	public String name;
	public User(String name) {
		this.name= name;
	}
}
