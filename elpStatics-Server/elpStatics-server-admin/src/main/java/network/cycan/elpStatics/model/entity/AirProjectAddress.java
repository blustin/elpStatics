package network.cycan.elpStatics.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author blustin
 * @since 2021-05-21
 */
@ApiModel(value="AirProjectAddress对象", description="")
public class AirProjectAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("projectId")
    private String projectId;

    @TableField("projectBatchNo")
    private String projectBatchNo;

    @TableField("sourceType")
    private String sourceType;

    @TableField("userAdderss")
    private String userAdderss;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getProjectBatchNo() {
        return projectBatchNo;
    }

    public void setProjectBatchNo(String projectBatchNo) {
        this.projectBatchNo = projectBatchNo;
    }
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    public String getUserAdderss() {
        return userAdderss;
    }

    public void setUserAdderss(String userAdderss) {
        this.userAdderss = userAdderss;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AirProjectAddress{" +
            "id=" + id +
            ", projectId=" + projectId +
            ", projectBatchNo=" + projectBatchNo +
            ", sourceType=" + sourceType +
            ", userAdderss=" + userAdderss +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
