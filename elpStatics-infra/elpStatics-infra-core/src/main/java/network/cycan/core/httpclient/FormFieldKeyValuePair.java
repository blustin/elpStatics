package network.cycan.core.httpclient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * FormFieldKeyValuePair 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/29 18:08
 */
@Data
public class FormFieldKeyValuePair implements Serializable {
    private static final long serialVersionUID = 3224573363249920571L;
    @ApiModelProperty(value = "字段ID")
    private String key;
    @ApiModelProperty(value = "字段值")
    private Object value;
}
