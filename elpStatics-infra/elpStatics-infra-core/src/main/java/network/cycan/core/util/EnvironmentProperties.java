package network.cycan.core.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * <p>
 * EnvironmentProperties 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/4 15:50
 */

public class EnvironmentProperties {
    private static final Logger _log = LoggerFactory.getLogger(GeneralProperties.class);
    @Autowired
    private static Environment environment;
    public static String getProperty(String key, String defaultValue) {
        String value= environment.getProperty(key);
        return value == null ? defaultValue : value;
    }
    public static String getProperty(String key) {
        String value= environment.getProperty(key);
        return  value;
    }
    public static String getRequiredProperty(String key) {
        String value= environment.getProperty(key);
        if(value==null||"".equals(value.trim())){
            throw new IllegalStateException("required property is blank by key=" + key);
        }
        return value;
    }

    public static int getRequiredInt(String key) {
        String value= getRequiredProperty(key);
        return Integer.parseInt(value);
    }

    public static boolean getRequiredBoolean(String key) {
        String value= getRequiredProperty(key);
        return Boolean.parseBoolean(value);
    }

    public static String getNullIfBlank(String key) {
        String value = getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value;
    }

}
