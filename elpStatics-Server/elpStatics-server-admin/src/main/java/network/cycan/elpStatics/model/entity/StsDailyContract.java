package network.cycan.elpStatics.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@ApiModel(value="StsDailyContract对象", description="")
public class StsDailyContract implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("contractStatisticsId")
    private String contractStatisticsId;

    @ApiModelProperty(value = "日期")
    private LocalDate daily;
    @ApiModelProperty(value = "日期字符串")
    private String dailyStr;





    @ApiModelProperty(value = "ELP持币地址数")
    @TableField("elpAddressCount")
    private Long elpAddressCount;

    @ApiModelProperty(value = "LP持币地址总数")
    @TableField("lpAddressCount")
    private Long lpAddressCount;

    @ApiModelProperty(value = "流动性挖矿合约中LP持币地址总数")
    @TableField("movingLpAddressCount")
    private Long movingLpAddressCount;

    @ApiModelProperty(value = "流动性挖矿合约中LP持币地址总数所占百分比")
    @TableField("movingLpAddressRate")
    private BigDecimal movingLpAddressRate;

    @ApiModelProperty(value = "ELP持币余额总数")
    @TableField("elpTotalBalance")
    private BigDecimal elpTotalBalance;

    @ApiModelProperty(value = "LP持币余额总数")
    @TableField("lpTotalBalance")
    private BigDecimal lpTotalBalance;

    @ApiModelProperty(value = "流动性挖矿合约中LP余额总数")
    @TableField("movingLpTotalBalance")
    private BigDecimal movingLpTotalBalance;

    @ApiModelProperty(value = "流动性挖矿合约中LP余额总数所占百分比")
    @TableField("movingLpBalanceRate")
    private BigDecimal movingLpBalanceRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getContractStatisticsId() {
        return contractStatisticsId;
    }

    public void setContractStatisticsId(String contractStatisticsId) {
        this.contractStatisticsId = contractStatisticsId;
    }
    public LocalDate getDaily() {
        return daily;
    }

    public void setDaily(LocalDate daily) {
        this.daily = daily;
    }
    public Long getElpAddressCount() {
        return elpAddressCount;
    }

    public void setElpAddressCount(Long elpAddressCount) {
        this.elpAddressCount = elpAddressCount;
    }
    public Long getLpAddressCount() {
        return lpAddressCount;
    }

    public void setLpAddressCount(Long lpAddressCount) {
        this.lpAddressCount = lpAddressCount;
    }
    public Long getMovingLpAddressCount() {
        return movingLpAddressCount;
    }

    public void setMovingLpAddressCount(Long movingLpAddressCount) {
        this.movingLpAddressCount = movingLpAddressCount;
    }
    public BigDecimal getMovingLpAddressRate() {
        return movingLpAddressRate;
    }

    public void setMovingLpAddressRate(BigDecimal movingLpAddressRate) {
        this.movingLpAddressRate = movingLpAddressRate;
    }
    public BigDecimal getElpTotalBalance() {
        return elpTotalBalance;
    }

    public void setElpTotalBalance(BigDecimal elpTotalBalance) {
        this.elpTotalBalance = elpTotalBalance;
    }
    public BigDecimal getLpTotalBalance() {
        return lpTotalBalance;
    }

    public void setLpTotalBalance(BigDecimal lpTotalBalance) {
        this.lpTotalBalance = lpTotalBalance;
    }
    public BigDecimal getMovingLpTotalBalance() {
        return movingLpTotalBalance;
    }

    public void setMovingLpTotalBalance(BigDecimal movingLpTotalBalance) {
        this.movingLpTotalBalance = movingLpTotalBalance;
    }
    public BigDecimal getMovingLpBalanceRate() {
        return movingLpBalanceRate;
    }

    public void setMovingLpBalanceRate(BigDecimal movingLpBalanceRate) {
        this.movingLpBalanceRate = movingLpBalanceRate;
    }
    public String getDailyStr() {
        return dailyStr;
    }

    public void setDailyStr(String dailyStr) {
        this.dailyStr = dailyStr;
    }
    @Override
    public String toString() {
        return "StsDailyContract{" +
            "id=" + id +
            ", contractStatisticsId=" + contractStatisticsId +
            ", daily=" + daily +
            ", elpAddressCount=" + elpAddressCount +
            ", lpAddressCount=" + lpAddressCount +
            ", movingLpAddressCount=" + movingLpAddressCount +
            ", movingLpAddressRate=" + movingLpAddressRate +
            ", elpTotalBalance=" + elpTotalBalance +
            ", lpTotalBalance=" + lpTotalBalance +
            ", movingLpTotalBalance=" + movingLpTotalBalance +
            ", movingLpBalanceRate=" + movingLpBalanceRate +
        "}";
    }
}
