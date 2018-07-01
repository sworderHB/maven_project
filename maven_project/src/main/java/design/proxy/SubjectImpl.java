package design.proxy;

public class SubjectImpl implements Subject {

	@Override
	public void f(String name) {
		System.out.println("SubjectImpl 你好 " + name);
	}
}
