package network.cycan.core.propeditor;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;

/**
 * <p>
 * TimestampEditor 类型:
 * </p>
 *
 * @author linjd
 * @since 2021/2/11 15:42
 */

public class TimestampEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        if (StringUtils.isNotBlank(text)) {
            setValue(Timestamp.valueOf(text));
        }
    }

    public static void main(String[] args){
        Timestamp ts = Timestamp.valueOf("2016-10-23 10:09:08");
        System.out.println(System.currentTimeMillis() + "|" + ts.getTime());
    }
}
