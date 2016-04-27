package com.iapppay.lixue.permissionlib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共类
 * Created by Administrator on 2016/4/25.
 */
public class Utils {
    private Utils(){}

    /**是否是androidM以上的系统版本**/
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**所有拒绝的权限列表**/
    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity,String... permissions){
        List<String> deniedPermissions = new ArrayList<>();
        int pLength = permissions.length;
        if (pLength > 0){
            for (String pers : permissions){
                if (activity.checkSelfPermission(pers) == PackageManager.PERMISSION_DENIED){
                    deniedPermissions.add(pers);
                }
            }
        }
        return deniedPermissions;
    }

    /**找出有注解的方法**/
    public static List<Method> findAnotionMethods(Class clazz,Class<? extends Annotation> clazz1){
        List<Method> anotionMethods = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method thismethod : methods){
            if (thismethod.isAnnotationPresent(clazz1)){
                anotionMethods.add(thismethod);
            }
        }
        return anotionMethods;
    }

    /**获取当前的activity**/
    public static Activity getActivity(Object object){
        if (object != null){
            if (object instanceof Fragment){
                return ((Fragment)object).getActivity();
            }else if(object instanceof Activity){
                return (Activity)object;
            }
        }
        return null;
    }

}
