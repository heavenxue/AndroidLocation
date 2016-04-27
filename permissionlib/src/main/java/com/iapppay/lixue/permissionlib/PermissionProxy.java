package com.iapppay.lixue.permissionlib;

/**
 * 接口
 * Created by Administrator on 2016/4/25.
 */
public interface PermissionProxy<T> {
    void grant(T source,int requestCode);
    void denied(T source,int requestCode);
    void rational(T source,int requestCode);
    void needRational(T source,int requestCode);
}
