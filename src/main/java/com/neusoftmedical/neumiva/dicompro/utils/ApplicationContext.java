package com.neusoftmedical.neumiva.dicompro.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffer on 2020/2/13.
 */
public class ApplicationContext {

    private static final Map<String, Object> context = new HashMap<>();


    public static void register(String beanName, Object object) {
        context.put(beanName, object);
    }

    public static Object getBean(String beanName) {
        return context.get(beanName);
    }
}
