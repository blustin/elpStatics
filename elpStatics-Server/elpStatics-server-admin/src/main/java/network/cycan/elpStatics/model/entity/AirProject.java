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
 * 空头项目名称
 * </p>
 *
 * @author blustin
 * @since 2021-05-21
 */
@ApiModel(value="AirProject对象", description="空头项目名称")
public class AirProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("projectId")
    private String projectId;

    @TableField("projectName")
    private String projectName;

    @TableField("projectBatchNo")
    private String projectBatchNo;

    private String remark;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    @TableField("createUserId")
    private String createUserId;

    @TableField("createUserName")
    private String createUserName;

    @TableField("updateUserId")
    private String updateUserId;

    @TableField("updateUserName")
    private String updateUserName;

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
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectBatchNo() {
        return projectBatchNo;
    }

    public void setProjectBatchNo(String projectBatchNo) {
        this.projectBatchNo = projectBatchNo;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Override
    public String toString() {
        return "AirProject{" +
            "id=" + id +
            ", projectId=" + projectId +
            ", projectName=" + projectName +
            ", projectBatchNo=" + projectBatchNo +
            ", remark=" + remark +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createUserId=" + createUserId +
            ", createUserName=" + createUserName +
            ", updateUserId=" + updateUserId +
            ", updateUserName=" + updateUserName +
        "}";
    }
}
