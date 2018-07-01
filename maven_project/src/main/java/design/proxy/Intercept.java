package design.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface Intercept {
	/**
	 * <pre class="code"><b>{@code
	 *   Title: 方法前执行  如果返回true 则继续执行被代理方法， 返回false，则不执行被代理方法，执行substitute 方法
	 *
	 * @param proxy		 代理对象, 即Proxy.Proxy.newProxyInstance()生成的对象
	 * @param method	被拦截的方法
	 * @param target	被拦截的方法所属的对象，即被代理的真实对象
	 * @param args		被拦截方法的形参
	 * @return  
	 * 
	 * @author  haobin
	 * @date    2018年4月22日 下午3:01:21
	 * </b></pre>
	 */
	public boolean brefore(Object proxy, Method method, Object target, Object[] args);
	
	// 方法后执行
	public void after(Object proxy, Method method, Object tartargetgt, Object[] args);
	
	// 如果before()为false，则本方法将替代原始方法
	public void substitute(Object proxy, Method method, Object target, Object[] args);
}
