package com.zhicheng.utils.common;

import com.zhicheng.BaseApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donson on 2017/2/22.
 */

public class BeanFactory {
    private BeanFactory() {
    }

    private static Map<String, Object> obj = new HashMap<>();
    private static List<String> className = new ArrayList<>();

    public static void createBean(Class clazz) {
        try {
            obj.put(clazz.getSimpleName(), clazz.newInstance());
            className.add(clazz.getSimpleName());
            Class[] clz = clazz.getClasses();
            for (Class c : clz) {
                createBean(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertBean(String name, String methodName, Object args) {
        try {
            Class c = obj.get(name).getClass();
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set") && method.getName().endsWith(methodName)) {
                    method.invoke(obj.get(name), args);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    public static void insertListBean(String name, List<String> args) {
        try {
            Class c = obj.get(name).getClass();
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public static <T> T getObject(){
//        int num = className.size() - 1;
//        for (int i=num;i>=0;i--){
//            obj.get(className.get(i)).getClass()
//        }
//        obj.get(className.get(num)
//    }

    private void saveObject(Object a, Object b) {
        Method[] methods = a.getClass().getDeclaredMethods();
    }

    public static void clearObj() {
        obj.clear();
    }
}
