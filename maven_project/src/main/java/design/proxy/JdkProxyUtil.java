package design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyUtil implements InvocationHandler {
	// 拦截器， 即切面
	private String intercept;
	
	// 被代理的真实对象
	private Subject target;
	
	private JdkProxyUtil(String intercept, Subject target) {
		this.intercept = intercept;
		this.target = target;
	}
	
	/** bind方法生成的就是被代理对象 */
	public static Object bind(Subject target, String intercept) {
		return  Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new JdkProxyUtil(intercept, target)
		);		//25 行
				
	}
	
	/**
	 * 这里第一个proxy 代表的是25行生成的对象， 即this  
	 * 严格讲, invoke方法是不应该放回proxy的，返回值应该是method.invoke(target, args);
	 * 即原来方法返回类型是什么，这里返回类型就应该是什么
	 * 如果希望链式编程的话，可以考虑返回proxy，但是这就破坏了被代理对象target的代码逻辑了
	 * 
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (null == this.intercept) {
			return method.invoke(target, args);
		}
		else {
			Object result = null;
			
			Intercept interceptObj = (Intercept) Class.forName(this.intercept).newInstance();
			boolean flag = interceptObj.brefore(proxy, method, target, args);
			// 如果before执行成功过，则执行原始方法，否则执行 拦截器的substitute方法
			if (flag) {
				result = method.invoke(target, args);
			}
			else {
				interceptObj.substitute(proxy, method, target, args);
			}
			
			// 最后执行after方法
			interceptObj.after(proxy, method, target, args);
			
			return result;
		}
	}

}
