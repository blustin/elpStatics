package network.cycan.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林坚丁
 * @date 2020/5/9 11:36
 * @Description
 */
public abstract class ReflectUtils {
    private static Map<String, Annotation> annotationMap = new HashMap<String, Annotation>();

    private static final Object ANNOTATION_LOCK = new Object();

    /**
     * 获取方法上的注解
     *
     * @param method
     * @param annotationClass
     * @return
     */
    public static Annotation getAnnotationByMethod(Method method, Class<? extends Annotation> annotationClass) {
        if (method == null || annotationClass == null) {
            return null;
        }

        // 校验是否已解析注解
        String key = method.getDeclaringClass().getName() + "." + method.getName() + "#" + annotationClass.getName();
        boolean contains = annotationMap.containsKey(key);
        if (contains) {
            return annotationMap.get(key);
        }

        // 解析注解
        Annotation result = annotationMap.get(key);
        if (result == null) {
            synchronized (ANNOTATION_LOCK) {
                result = annotationMap.get(key);
                if (result == null) {
                    result = method.getAnnotation(annotationClass);
                    annotationMap.put(key, result);
                }
            }
        }
        return result;
    }
}
