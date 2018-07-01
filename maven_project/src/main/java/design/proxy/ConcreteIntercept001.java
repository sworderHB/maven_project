package design.proxy;

import java.lang.reflect.Method;

public class ConcreteIntercept001 implements Intercept{
	@Override
	public boolean brefore(Object proxy, Method method, Object targt, Object[] args) {
		System.out.println("ConcreteIntercept001 的before方法执行了");
		return true;
	}

	@Override
	public void after(Object proxy, Method method, Object targt, Object[] args) {
		System.out.println("ConcreteIntercept001 的after方法执行了");
	}

	@Override
	public void substitute(Object proxy, Method method, Object targt, Object[] args) {
		System.out.println("ConcreteIntercept001 的substitute方法执行了");
	}
}
