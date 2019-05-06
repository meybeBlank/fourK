package com.fengz.personal.fourweeks.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/3/29
 * 作   者：fengzhen
 * <p>
 * 功能描述：反射方式解析驼峰命名JSon数据 -> bean
 * 接口数据仅支持List
 * 写着玩儿，这样写只是有更好的适应性，可以随时改。
 * 实际使用还是推荐Gson进行解析，效率更高。
 */
public class JSonParser {

    public static <T> T parse(Class<T> tClass, JSONObject jsonObj) throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field :
                fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            if (fieldType.isPrimitive()) {
                // 解析基础类
                field.set(instance, jsonObj.opt(field.getName()));
            } else {
                if ("java.util.List".equals(fieldType.getName())
                        || "java.util.ArrayList".equals(fieldType.getName())) {
                    // 得到类型的结构，java.util.ArrayList<com.xxx.xxx>
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Class<?> aClass = ((Class<?>) genericType.getActualTypeArguments()[0]);
                    List list = new ArrayList();
                    JSONArray jsonArray = jsonObj.optJSONArray(field.getName());
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.optJSONObject(i);
                            list.add(parse(aClass, jsonObject));
                        }
                    }
                    field.set(instance, list);
                } else {
                    Object newInstance = fieldType.newInstance();
                    if (newInstance instanceof String) {
                        field.set(instance, jsonObj.optString(field.getName()));
                    } else {
                        // 自定义类
                        Object parse = parse(fieldType, jsonObj.optJSONObject(field.getName()));
                        field.set(instance, parse);
                    }
                }
            }
        }
        return instance;
    }
}
