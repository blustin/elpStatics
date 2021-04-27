package network.cycan.core.propeditor;

import network.cycan.core.util.BaseUtils;

import java.beans.PropertyEditorSupport;

/**
 * <p>
 * CustomTransferSymbolEditor 类型:
 * </p>
 *
 * @author linjd
 * @since 2021/2/11 15:43
 */
public class CustomTransferSymbolEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        setValue(BaseUtils.replaceHtmlSymbols(text)); // 去掉字符串中的'<' '>' '"'字符
    }
}
