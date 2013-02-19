package uge8;

public class GenericReflectionTest {
	public static void main(String[] args) {
		AbstractUser<?> user = new User();
		System.out.println("first returned class: " +  user.returnedClass());
		
		user = new Manager();
		System.out.println("second returned class: " +  user.returnedClass());
	}
}
