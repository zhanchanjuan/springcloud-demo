package com.myproject.util;


import com.myproject.common.exception.ProjectException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * bean转化工具类
 * BeanUtil.copyProperties(源对象，目标对象）的使用
 */
public class BeanUtil {

    /**
     * entity to dto
     *
     * @param source 源对象
     * @param clazz  目标对象类型
     * @param <E>
     * @param <D>
     * @return
     */
    public static <D, E> D copy(E source, Class<D> clazz) {
        D res = null;
        try {
            res = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, res);
        } catch (Exception e) {
            throw new ProjectException("BeanUtil转化异常");
        }
        return res;
    }

    /**
     * 复制字段
     * 忽略空字段
     *
     * @param source 源
     * @param target 目标
     * @param <E>
     * @param <D>
     * @return
     */
    public static <D, E> E copyPropertiesWithoutBlank(D source, E target) {
        try {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } catch (Exception e) {
            throw new ProjectException("BeanUtil转化异常");
        }
        return target;
    }

    /**
     * 获取空字段
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
