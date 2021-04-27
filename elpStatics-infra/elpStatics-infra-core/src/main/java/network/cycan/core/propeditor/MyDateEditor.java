package network.cycan.core.propeditor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * MyDateEditor 类型:
 * </p>
 *
 * @author linjd
 * @since 2021/2/11 15:44
 */
@Slf4j
public class MyDateEditor extends PropertiesEditor {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static{
        dateFormat.setLenient(false); // 严格解析字符串格式
        dateTimeFormat.setLenient(false); // 严格解析字符串格式
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if( StringUtils.isNotBlank(text) ){
            /**
             * 先尝试转换yyyy-MM-dd，报错则再转换yyyy-MM-dd HH:mm:ss格式
             */
            try {
                this.setValue(dateFormat.parse(text.trim()));
            } catch (Exception e) {

                log.error(e.getMessage());
                try{
                    this.setValue(dateTimeFormat.parse(text.trim()));
                } catch (Exception ee){
                    log.error(ee.getMessage());
                    try{
                        this.setValue(new Date(Long.valueOf(text.trim()))); // 一串数字
                    }catch(Exception e3){
                        log.error(e3.getMessage());
                    }
                }
            }
        }
    }
}
