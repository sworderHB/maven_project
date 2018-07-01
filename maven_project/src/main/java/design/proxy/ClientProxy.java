package design.proxy;

public class ClientProxy {
	public static void main(String[] args) throws Exception{
		SubjectImpl target = new SubjectImpl();
//		target.f("哈哈");
		
		Subject proxy = (Subject) JdkProxyUtil.bind(target, "design.proxy.ConcreteIntercept001");
		proxy.f("嘿嘿");
	}
}
