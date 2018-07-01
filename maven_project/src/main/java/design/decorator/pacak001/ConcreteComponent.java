package design.decorator.pacak001;

// 原始的被装饰对象
public class ConcreteComponent implements Component{
	@Override
	public void f(String str) {
		System.out.println("ConcreteA 对象的f方法被调用了" + str);
	}
}
