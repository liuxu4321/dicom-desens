package com.neusoftmedical.neumiva.dicompro.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * Created by Jeffer on 2020/2/14.
 */
public class ClassUtils {

    public static void iteratorClass(Object obj, Vector vector) {
        Field[] field = obj.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {
            String listItem = "";
            String type = field[j].getGenericType().toString();
            String name = field[j].getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                Method m = obj.getClass().getMethod("get" + name);
                String value = (String) m.invoke(obj);
                listItem = name + " : " + value;
                vector.add(listItem);
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }
}
