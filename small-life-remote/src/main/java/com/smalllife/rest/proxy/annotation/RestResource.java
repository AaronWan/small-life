package com.smalllife.rest.proxy.annotation;

import java.lang.annotation.*;

/**
 * Created by Aaron on 26/12/2016.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestResource  {
    String value() ;
    String desc() default "";
    String codec() ;
    String contentType() default "";


}
