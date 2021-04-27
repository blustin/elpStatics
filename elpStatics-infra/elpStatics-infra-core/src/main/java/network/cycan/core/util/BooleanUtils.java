package network.cycan.core.util;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author 林坚丁
 * @date 2020/5/9 11:32
 * @Description
 */
public abstract class BooleanUtils {
    /**
     * 若不为null且true则返回true
     *
     * @param params 参数
     * @return boolean
     */
    public static boolean isTrue(Boolean params) {
        return params != null && params;
    }

    /**
     * 是否true，若为1或org.apache.commons.lang3.BooleanUtils.toBoolean(params)则返回true
     *
     * @param params 校验参数
     * @return boolean
     */
//    public static boolean isTrue(String params) {
//
//        return CommonConstant.ONE == NumberUtils.toInt(params) || org.apache.commons.lang.BooleanUtils.toBoolean(params);
//    }

    /**
     * 校验参数是否为null或""
     *
     * @param params
     * @return
     */
    public static boolean isEmpty(String params) {
        if (params == null || params.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String params) {
        return !isEmpty(params);
    }

    /**
     * 校验数组是否为null或长度为0
     *
     * @param params
     * @return
     */
    public static boolean isEmpty(int... params) {
        if (params == null || params.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(int... params) {
        return !isEmpty(params);
    }

    /**
     * 校验数组是否为null或长度为0
     *
     * @param params
     * @return
     */
    public static <T> boolean isEmpty(T[] params) {
        if (params == null || params.length == 0) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotEmpty(T[] params) {
        return !isEmpty(params);
    }

    /**
     * 校验参数是否为null或去掉空格后为""
     *
     * @param params
     * @return
     */
    public static boolean isBlank(String params) {
        boolean result = isEmpty(params);
        if (!result) {
            result = params.trim().equals("");
        }
        return result;
    }

    /**
     * 校验参数是否为null或去掉空格后为""
     *
     * @param params
     * @return
     */
    public static boolean isNotBlank(String params) {
        return !isBlank(params);
    }

    /**
     * 校验集合是否为null或长度为0
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 校验map是否为null或长度为0
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 校验对象是否为基本类型
     *
     * @param object
     * @return
     */
    public static boolean isSimpleType(Object object) {
        if (object == null) {
            return false;
        }
        Class<?> clazz = object.getClass();
        if (String.class.isAssignableFrom(clazz) || Enum.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }

    /**
     * 是否包含
     *
     * @param param
     * @param params
     * @return
     */
    public static <T> boolean isIn(T param, T[] params) {
        if (params == null || BooleanUtils.isEmpty(params)) {
            return false;
        }
        for (T each : params) {
            if (each.equals(param)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否都不为空
     *
     * @param params
     * @return
     */
    public static <T> boolean allNotNull(T[] params) {
        if (BooleanUtils.isEmpty(params)) {
            return false;
        }
        for (T each : params) {
            if (null == each) {
                return false;
            }
        }
        return true;
    }

}
