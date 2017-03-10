package el;

import static org.testng.Assert.assertEquals;

import javax.el.ELContext;
import javax.el.ELProcessor;
import javax.el.LambdaExpression;
import javax.el.TypeConverter;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ElTest {
	private static ELProcessor elProcessor;
	
	@BeforeClass
	public static void setupTest() {
		elProcessor = new ELProcessor();
	}
	
	@Test
	public void testEl() {
		elProcessor.defineBean("user", new User("John Doe"));
		String message = (String) elProcessor.eval("${user.name}");
		assertEquals(message, "John Doe");
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
		assertEquals(user.getName(), "John Doe");
	}
}

class User {
	private String name;
	
	public User() {
		
	}
	
	public User(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
