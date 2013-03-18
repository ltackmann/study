package uge9;

public class InnerClassesTest {
	static class Test {
		Test() {
			System.out.println("Test 2");
		}
	}
	
	public static void main(String[] args) {
		Cathedral c = new Cathedral();
		c.outer();
		Cathedral.Sanctrum s = c.new Sanctrum();
		s.go();
		Test t = new Test();
		Test t2 = new Test();
		System.out.println(t==t2);
	}
}

class Cathedral {
	class Sanctrum {
		void go() {
			System.out.println("Test");
		}
	}
	void outer() {
		
	}
}
