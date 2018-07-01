package design.decorator.pacak001;

public class Client {
	public static void main(String[] args) {
		ConcreteComponent concreteA = new ConcreteComponent();
//		concreteA.f("aa");
		
		DecoratorA decoratorA = new DecoratorA(concreteA);
		DecoratorB decoratorb = new DecoratorB(decoratorA);
		
		decoratorb.f("bbb");
		
	}
}
