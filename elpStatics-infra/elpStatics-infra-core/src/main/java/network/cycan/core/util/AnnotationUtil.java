package network.cycan.core.util;


import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

/**
 * @author 林坚丁
 * @date 2020/5/11 17:33
 * @Description
 */
public class AnnotationUtil {
    /** 获取指定类所有注解 */
    public static <T> Annotation[] getAnnotations(Class<T> srcClass) {
        return srcClass == null ? null : srcClass.getAnnotations();
    }

    /** 获取指定注解 */
    public static <T, A extends Annotation> A getAnnotation(Class<T> srcClass, Class<A> annotationClass) {
        return srcClass == null ? null : srcClass.getAnnotation(annotationClass);
    }

    /** 获取spring.service注解 */
    public static <T> Service getSpringServiceAnnotation(Class<T> srcClass) {
        return srcClass == null ? null : srcClass.getAnnotation(Service.class);
    }

    /** 获取spring.service注解的value属性值 */
    public static <T> String getSpringServiceAnnotationValue(Class<T> srcClass) {
        Service service = getSpringServiceAnnotation(srcClass);
        return service == null ? null : service.value();
    }
}
