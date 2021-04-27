package network.cycan.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;


/**
 * <p>
 * GeneralProperties 类型:属性文件读取
 * </p>
 *
 * @author linjd
 * @since 2020/6/4 11:23
 */
public class GeneralProperties {
    static final String PROPERTIES_FILE_NAME = "exCloud.properties";
    private static final Logger _log = LoggerFactory.getLogger(GeneralProperties.class);
    static PropertiesHelper props;
    private GeneralProperties() {
    }
    private static void loadProperties() {
        try {
            if (_log.isDebugEnabled())
                _log.debug("加载属性文件 [exCloud.properties]");
            Properties properties=loadAllPropertiesByClassLoader(PROPERTIES_FILE_NAME);
            if(properties!=null) {
                props = new PropertiesHelper(properties);
                for (Iterator<Entry<Object, Object>> it = props.entrySet().iterator(); it.hasNext(); ) {
                    Entry<Object, Object> entry = it.next();
                    if (_log.isDebugEnabled())
                        _log.debug("[属性] " + entry.getKey() + "=" + entry.getValue());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Load Properties error", e);
        } catch (Exception e) {
            throw new RuntimeException("Load Properties error", e);
        }
    }
    public static Properties getProperties() {
        return getHelper().getProperties();
    }

    private static PropertiesHelper getHelper() {
        if (props == null)
           loadProperties();
        return props;
    }

    public static String getProperty(String key, String defaultValue) {
        return getHelper().getProperty(key, defaultValue);
    }

    public static String getProperty(String key) {
        return getHelper().getProperty(key);
    }

    public static String getRequiredProperty(String key) {
        return getHelper().getRequiredProperty(key);
    }

    public static int getRequiredInt(String key) {
        return getHelper().getRequiredInt(key);
    }

    public static boolean getRequiredBoolean(String key) {
        return getHelper().getRequiredBoolean(key);
    }

    public static String getNullIfBlank(String key) {
        return getHelper().getNullIfBlank(key);
    }

    public static void setProperty(String key, String value) {
        getHelper().setProperty(key, value);
    }

    public static void setProperties(Properties v) {
        props = new PropertiesHelper(v);
    }

    public static Properties loadAllPropertiesByClassLoader(String resourceName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = GeneralProperties.class.getClassLoader().getResourceAsStream(resourceName);
        if (inputStream != null) {
            properties.load(new InputStreamReader(inputStream,"UTF-8"));
           // properties.load(inputStream,"utf8");
            return properties;
        }
        return null;
    }

}
