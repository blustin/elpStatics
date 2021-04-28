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
@ApiModel(value="UserBalanceLog对象", description="")
public class UserBalanceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "GUID")
    @TableField("userBalanceId")
    private String userBalanceId;

    @ApiModelProperty(value = "持币地址")
    @TableField("userAddress")
    private String userAddress;

    @ApiModelProperty(value = "对应余额")
    @TableField("balanceAmount")
    private BigDecimal balanceAmount;

    @ApiModelProperty(value = "类型1:ELP用户给,2：LP 用户 3：流动性挖矿合约持币地址")
    @TableField("userType")
    private String userType;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updateTime")
    private LocalDateTime updateTime;

    @TableField("blockNum")
    private Long blockNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserBalanceId() {
        return userBalanceId;
    }

    public void setUserBalanceId(String userBalanceId) {
        this.userBalanceId = userBalanceId;
    }
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
    public Long getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(Long blockNum) {
        this.blockNum = blockNum;
    }

    @Override
    public String toString() {
        return "UserBalanceLog{" +
            "id=" + id +
            ", userBalanceId=" + userBalanceId +
            ", userAddress=" + userAddress +
            ", balanceAmount=" + balanceAmount +
            ", userType=" + userType +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", blockNum=" + blockNum +
        "}";
    }
}
