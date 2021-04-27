package network.cycan.model;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author 林坚丁
 * @date 2020/5/10 21:54
 * @Description
 */
public class QuotEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isBlank(text)){
            setValue(null);
        }
        else if(text.contains("&quot;")){
            setValue(text.replaceAll("&quot;", "\""));
        }
        else{
            setValue(text);
        }
    }
}
