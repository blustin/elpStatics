package network.cycan.common.apiInfor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author 林坚丁
 * @date 2020/5/10 21:18
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "通用API接口返回", description = "Common Api Response")
public class ApiResponse<T> implements Serializable {
    
	private static final long serialVersionUID = 6843951841165701879L;
    private static final Logger _log = LoggerFactory.getLogger(ApiResponse.class);
    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功", required = true)
	private boolean success=true;

    /**
     * 返回状态码
     */
    @ApiModelProperty(value = "返回状态码", required = true)
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息", required = false)
    private String msg;


    /**
     * 通用返回数据
     */
    @ApiModelProperty(value = "返回数据", required = false)
    private T data;

    public static<T> ApiResponse<T> ConvertRet(String retJson, Class<T> clazz){
        try{
            ApiResponse<T> ret= JSONObject.parseObject(retJson, new TypeReference<ApiResponse<T>>(clazz){});
            return ret;
        }catch(Exception e){
            _log.error("错误的转换：BeanUtil.convertValue() --->" + e.getMessage());
            return null;
        }
    }




}
