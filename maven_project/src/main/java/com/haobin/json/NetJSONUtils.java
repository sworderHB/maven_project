package com.haobin.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.haobin.json.JsonConvent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON和JAVA的POJO的相互转换
 * 
 * @author jiangzhong
 * @date 2011-5-15
 */
public class NetJSONUtils {
	// 将String转换成JSON
	NetJSONUtils() {

	}

	public static String string2json(String key, String value) {
		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}

	// 将JSON转换成数组,其中valueClz为数组中存放的对象的Class
	@SuppressWarnings("unchecked")
	public static Object json2Array(String json, Class valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toArray(jsonArray, valueClz);
	}

	// 将Collection转换成JSON
	public static String collection2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	// 将JSON转换成Collection,其中collectionClz为Collection具体子类的Class,
	// valueClz为Collection中存放的对象的Class
	@SuppressWarnings("unchecked")
	public static Collection json2Collection(String json, Class collectionClz, Class valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toCollection(jsonArray, valueClz);
	}

	// 将数组转换成JSON
	public static String array2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	// 将Map转换成JSON
	public static String map2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// 将JSON转换成Map,其中valueClz为Map中value的Class,keyArray为Map的key
	@SuppressWarnings("unchecked")
	public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map classMap = new HashMap();

		for (int i = 0; i < keyArray.length; i++) {
			classMap.put(keyArray[i], valueClz);
		}
		return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
	}

	// 将POJO转换成JSON
	public static String bean2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// 将JSON转换成POJO,其中beanClz为POJO的Class
	@SuppressWarnings("unchecked")
	public static Object json2Object(String json, Class beanClz) {
		return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
	}

	// 将JSON转换成String
	public static String json2String(String json, String key) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key).toString();
	}

	public static <T> T invokeAnno(Class<T> cl, JSONObject rs) throws Exception {
		T t = cl.newInstance();
		Field[] fields = cl.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			Annotation[] s = f.getAnnotations();
			String jsonKey = "";
			for (Annotation anno : s) {
				if (anno instanceof JsonConvent) {
					jsonKey = ((JsonConvent) anno).value();
					String jsonValue = getkeyAnno(rs, jsonKey, "");
					if (f.getType() == String.class) {
						f.set(t, jsonValue);
					} else if (f.getType() == Long.class) {
						if (!jsonValue.equals("null") && !jsonValue.equals("")) {
							f.set(t, Long.valueOf(jsonValue));
						}
					} else if (f.getType() == Integer.class) {
						if (!jsonValue.equals("null") && !jsonValue.equals("")) {
							f.set(t, Integer.valueOf(jsonValue));
						}
					} else if (f.getType() == Float.class) {
						if (!jsonValue.equals("null") && !jsonValue.equals("")) {
							f.set(t, Float.valueOf(jsonValue));
						}
					} else if (f.getType() == Short.class) {
						if (!jsonValue.equals("null") && !jsonValue.equals("")) {
							f.set(t, Short.valueOf(jsonValue));
						}
					} else if (f.getType() == Double.class) {
						if (!jsonValue.equals("null") && !jsonValue.equals("")) {
							f.set(t, Double.valueOf(jsonValue));
						}
					} else if (f.getType() == List.class) {
						if (!jsonValue.equals("null") && !jsonValue.equals("")) {
							// 关键的地方，如果是List类型，得到其Generic的类型
							Type fc = f.getGenericType();
							if (fc == null)
								continue;
							// 【3】如果是泛型参数的类型
							if (fc instanceof ParameterizedType) {
								ParameterizedType pt = (ParameterizedType) fc;
								Class genericClazz = (Class) pt.getActualTypeArguments()[0];
								// 判断是否为基本类型
								if (genericClazz.isPrimitive())
									continue;
								// getName()返回field的类型全路径
								if (genericClazz.getName().startsWith("java.lang"))
									continue;

								JSONArray array = rs.getJSONArray(jsonKey);
								System.out.println(array.toString());
								Object[] objs = array.toArray();
								JSONObject objJson = null;
								List lsit = new ArrayList();
								for (int i = 0; i < objs.length; i++) {
									objJson = JSONObject.fromObject(objs[i]);
									lsit.add(invokeAnno(genericClazz, objJson));
								}
								f.set(t, lsit);
							}
						}
					}
					break;
				}
			}
		}
		return t;
	}

	public static String getkeyAnno(JSONObject obj, String key, String defaultValue) {
		String result = "";
		try {
			result = obj.get(key) + "";
		} catch (Exception ex) {
			result = defaultValue;
		}
		return result;
	}

	public static String annoBean2json(Object obj) throws InstantiationException, IllegalAccessException {
		String tmp = JSONObject.fromObject(obj).toString();
		return jsonKeyConversion(obj, tmp);
	}

	private static String jsonKeyConversion(Object obj, String str)
			throws InstantiationException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		Field[] superFields = obj.getClass().getSuperclass().getDeclaredFields();
		Field[] totalFields = Arrays.copyOf(fields, fields.length+superFields.length);
		System.arraycopy(superFields, 0,totalFields, fields.length, superFields.length);
		for (Field field : totalFields) {
			Annotation[] annos = field.getAnnotations();
			String jsonKey = "";
			for (Annotation anno : annos) {
				if (anno instanceof JsonConvent) {
					if (field.getType() == List.class) {
						// 关键的地方，如果是List类型，得到其Generic的类型
						Type fc = field.getGenericType();
						if (fc == null)
							continue;
						// 【3】如果是泛型参数的类型
						if (fc instanceof ParameterizedType) {
							ParameterizedType pt = (ParameterizedType) fc;
							Class genericClazz = (Class) pt.getActualTypeArguments()[0];
							// 判断是否为基本类型
							if (genericClazz.isPrimitive())
								continue;
							// getName()返回field的类型全路径
							if (genericClazz.getName().startsWith("java.lang"))
								continue;

							str = jsonKeyConversion(genericClazz.newInstance(), str);
						}
					}
					jsonKey = ((JsonConvent) anno).value();
					str = str.replace(field.getName(), jsonKey);
				}
			}
		}
		return str;
	}

	/**
	 * 函数注释：parseJSON2MapString()<br>
	 * 用途：该方法用于json数据转换为<Map<String, String><br>
	 * 备注：***<br>
	 */
	public static Map<String, String> parseJSON2MapString(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (null != v) {
				map.put(k.toString(), v.toString());
			}
		}
		return map;
	}

}
