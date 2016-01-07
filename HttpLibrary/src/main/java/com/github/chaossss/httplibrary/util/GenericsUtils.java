package com.github.chaossss.httplibrary.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * An util helps u get super class by reflection
 * Created by chaos on 2016/1/1.
 */
public class GenericsUtils {
    /**
     * Returns the type of super class's generic param by using reflection,
     * such as ComicBookList extends BookList<Book>
     *
     * @param clazz the subclass u want to get it's generic param
     *
     * @return the first generic declarated param of super class, or <code>Object.class</code>
     * if can't be determined
     */
    public static Class getSuperClassGenricType(Class clazz){
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * Returns the type of super class's generic param by using reflection,
     * such as ComicBookList extends BookList<Book>
     *
     * @param clazz the subclass u want to get it's generic param
     * @param index the index of generic declaration, start from 0
     *
     * @return the first generic declarated param of super class, or <code>Object.class</code>
     * if can't be determined
     */
    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException{
        Type genType = clazz.getGenericSuperclass();

        if(!(genType instanceof ParameterizedType)){
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if(index >= params.length || index < 0){
            return Object.class;
        }

        if(!(params[index] instanceof Class)){
            return Object.class;
        }

        return (Class) params[index];
    }
}
