package com.smalllife.rest.proxy.annotation;

import java.lang.annotation.*;

/**
 * Created by Aaron on 05/01/2017.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathParams {
}
