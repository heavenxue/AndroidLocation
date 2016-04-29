package com.iapppay.lixue.pemission_annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/4/25.
 */
@Target(ElementType.METHOD)
public @interface PermissionGrant {
    int value();
}
