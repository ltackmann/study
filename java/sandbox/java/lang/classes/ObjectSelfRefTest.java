package lang.classes;

public class ObjectSelfRefTest {
	Self s = new Self();
}

class Self {
	void doit(Self self) {

	}
}

interface Work {
	void doIt(String s);
}

abstract class BaseJob implements Work {
	public abstract void doIt(String s);
}

abstract class AbstractJob extends BaseJob {
	public void doIt(String s) {
		System.out.println("hello");
	}
}
