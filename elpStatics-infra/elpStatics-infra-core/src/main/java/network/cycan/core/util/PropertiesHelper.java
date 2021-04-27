package network.cycan.core.util;

import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;


import org.apache.commons.lang.StringUtils;

/**
 *
 * @author 林坚丁
 * @date 2020/5/11 17:43
 * @Description  属性文件读取
 */
public class PropertiesHelper {
    Properties p;

    public PropertiesHelper(Properties properties) {
        this.p = properties;
    }

    public Properties getProperties() {
        return p;
    }

    public String getProperty(String key, String defaultValue) {
        String value = this.getProperty(key);
        return value == null ? defaultValue : value;
    }

    public String getProperty(String key) {
        String value = getProperties().getProperty(key);
        String returnSrt = value;
        if (StringUtils.isNotBlank(value)) {
            //returnSrt = JcSecureUtil.decode(value);
        }
        return returnSrt == null ? value : returnSrt;
    }

    public String getNullIfBlank(String key) {
        String value = this.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value;
    }

    public String getRequiredProperty(String key) {
        String value = getProperty(key);
        if (value == null || "".equals(value.trim())) {
            throw new IllegalStateException("required property is blank by key=" + key);
        }
        return value;
    }

    public Integer getInt(String key) {
        if (getProperty(key) == null) {
            return null;
        }
        return Integer.parseInt(getRequiredProperty(key));
    }

    public int getInt(String key, int defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Integer.parseInt(getRequiredProperty(key));
    }

    public int getRequiredInt(String key) {
        return Integer.parseInt(getRequiredProperty(key));
    }

    public Boolean getBoolean(String key) {
        if (getProperty(key) == null) {
            return null;
        }
        return Boolean.parseBoolean(getRequiredProperty(key));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (getProperty(key) == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(getRequiredProperty(key));
    }

    public boolean getRequiredBoolean(String key) {
        return Boolean.parseBoolean(getRequiredProperty(key));
    }

    public PropertiesHelper setProperty(String key, String value) {
        p.setProperty(key, value);
        return this;
    }

    public void clear() {
        p.clear();
    }

    public Set<Entry<Object, Object>> entrySet() {
        return p.entrySet();
    }

    public Enumeration<?> propertyNames() {
        return p.propertyNames();
    }

}
