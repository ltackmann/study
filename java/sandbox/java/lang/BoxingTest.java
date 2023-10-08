package lang;

public class BoxingTest {
	static void go(int i, byte l) {
		System.out.println("1");
	}
	
	static void go(byte l, Long i) {
		System.out.println("2");
	}
	
	public void test() {
		byte b = 1;
		byte l = 2;
		go(b, l);
	}
}
