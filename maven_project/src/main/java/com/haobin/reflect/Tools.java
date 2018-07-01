package com.haobin.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class Tools {
	/**
	 * 对象相同属性copy
	 * 		此方法会过滤final字段
	 * 		此方法会过滤对象字段
	 * 		此方法会兼容同对象之间、不同对象之间属性值复制
	 * @param obj
	 * @param toResult
	 * @return
	 * @throws Exception
	 *             转换报错
	 */
	public static <T> T cloneObj(Object obj, Class<T> toResult) {
		if (obj == null) {
			return null;
		}
		try {
			T t = toResult.newInstance();
			Field[] fields = toResult.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);// 修改访问权限
				if (Modifier.isFinal(field.getModifiers()))
					continue;
				if (isWrapType(field)) {
					String firstLetter = field.getName().substring(0, 1).toUpperCase(); // 首字母大写
					String getMethodName = "get" + firstLetter + field.getName().substring(1);
					String setMethodName = "set" + firstLetter + field.getName().substring(1);
					
					
					Method getMethod;
					try {
						getMethod = obj.getClass().getMethod(getMethodName);
					}
					catch (Exception e) {
						System.out.println("目标对象的" + field.getName() + "属性，源对象不存在");
						continue;
					}
					
					
					Method setMethod = toResult.getMethod(setMethodName, new Class[] { field.getType() }); // 从目标对象获取set方法

					// 如果get 和 set方法都从一个对象中获取会出现object is not an instance of declaring class这个错误
					// like: User{name} People{name}
					// 因为如果从源对象中获取，在setMethod.invoke调用的时候，虽然名字相同，会由于类的不同，导致
					// 调用的方法却不是目标对象中应有的方法。实际就是:getMethod = com.package.User.getName();setMethod = com.package.User.setName();
					// 而setMethod.invoke调用的结果就变成 People.setName() == People.(com.package.User.setName())
					// 这里的setName却不是People该有的，so 报错了
					// 同理,如果从目标对象中获取，在getMethod.invoke调用的时候也会出错。
					// 因此，对于getMethod和setMethod的获取应该根据源对象和目标对象区别对待。

					// 当然如果只是进行单独的对象复制，就不用担心会出现调用不属于本身的方法，也就不用区分对象get和set

					Object value = getMethod.invoke(obj); // get 获取的是源对象的值
					setMethod.invoke(t, new Object[] { value }); // set 设置的是目标对象的值
				}
			}
			return t;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 是否是基本类型、包装类型、String类型
	 */
	private static boolean isWrapType(Field field) {
		String[] types = { "java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long", "java.lang.Short",
				"java.lang.Byte", "java.lang.Boolean", "java.lang.Char", "java.lang.String", "int", "double", "long", "short", "byte",
				"boolean", "char", "float" };
		List<String> typeList = Arrays.asList(types);
		return typeList.contains(field.getType().getName()) ? true : false;
	}

	public static void  f1() throws IllegalAccessException, InvocationTargetException {
		User user = new User();
		user.setName("AAA");
		user.setPassword("vvv");
		user.setRank(22);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("aa", "one");
//		map.put("bb", "哈哈");
//		user.setMap1(map);
//		List<String> list = new ArrayList<String>();
//		list.add("zhangsan");
//		list.add("李四");
//		user.setList1(list);
		
		System.out.println("11 user = " + user);
		
		People people = new People();
		System.out.println("2 people = " + people);
		
		BeanUtils.copyProperties(people, user);
		
		System.out.println("33 p = " + people);
	}
	
	public static void  f2() throws IllegalAccessException, InvocationTargetException {
		User user = new User();
		user.setName("AAA");
		user.setPassword("vvv");
		System.out.println("1 user = " + user);
		
		People people = new People();
		System.out.println("2 people = " + people);

		User user2 = cloneObj(user, User.class);
		System.out.println("3 user2 = " + user2);

		People p = cloneObj(user, People.class);
		System.out.println("4 p = " + p);
	}
	
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		f1();
	}
}