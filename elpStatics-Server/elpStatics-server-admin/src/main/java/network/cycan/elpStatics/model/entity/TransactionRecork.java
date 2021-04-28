package network.cycan.elpStatics.model.entity;

import java.math.BigDecimal;
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
 * @since 2021-04-28
 */
@ApiModel(value="TransactionRecork对象", description="")
public class TransactionRecork implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易GUID")
    @TableField("transactionId")
    private String transactionId;

    @ApiModelProperty(value = "交易支出方ID")
    @TableField("fromUserId")
    private String fromUserId;

    @ApiModelProperty(value = "支出方地址")
    @TableField("fromUserAddress")
    private String fromUserAddress;

    @ApiModelProperty(value = "收入方ID")
    @TableField("toUserId")
    private String toUserId;

    @ApiModelProperty(value = "收入方地址")
    @TableField("toUserAddress")
    private String toUserAddress;

    @ApiModelProperty(value = "交易金额")
    @TableField("transactionAmount")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "交易时间")
    @TableField("transactionTime")
    private LocalDateTime transactionTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("upateTime")
    private LocalDateTime upateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("hashCode")
    private String hashCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }
    public String getFromUserAddress() {
        return fromUserAddress;
    }

    public void setFromUserAddress(String fromUserAddress) {
        this.fromUserAddress = fromUserAddress;
    }
    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    public String getToUserAddress() {
        return toUserAddress;
    }

    public void setToUserAddress(String toUserAddress) {
        this.toUserAddress = toUserAddress;
    }
    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(LocalDateTime upateTime) {
        this.upateTime = upateTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public String toString() {
        return "TransactionRecork{" +
            "id=" + id +
            ", transactionId=" + transactionId +
            ", fromUserId=" + fromUserId +
            ", fromUserAddress=" + fromUserAddress +
            ", toUserId=" + toUserId +
            ", toUserAddress=" + toUserAddress +
            ", transactionAmount=" + transactionAmount +
            ", transactionTime=" + transactionTime +
            ", createTime=" + createTime +
            ", upateTime=" + upateTime +
            ", remark=" + remark +
            ", hashCode=" + hashCode +
        "}";
    }
}
