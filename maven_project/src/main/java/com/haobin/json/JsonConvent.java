package com.haobin.json;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonConvent {
	
	 String value() default "";
	 boolean encrypt() default false;

}
