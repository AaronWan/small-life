package com.smalllife.rest.proxy.annotation;

import java.lang.annotation.*;

/**
 * Created by Aaron on 07/01/2017.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DELETE {
    String value() default "";

    String desc() default "";

    String contentType() default "";
}
