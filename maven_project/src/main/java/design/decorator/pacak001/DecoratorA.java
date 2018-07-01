package design.decorator.pacak001;

// 装饰器A
public class DecoratorA implements Component {  //这里的implements有的书籍也写为extends
	private Component component;  //装饰器类一定包含了一个业务接口的属性 

	public DecoratorA(Component component) {
		this.component = component;
	}
	
	@Override
	public void f(String str) {
		component.f(str);
		System.out.println("DecoratorA 对象的f方法执行了" + str);
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
}
