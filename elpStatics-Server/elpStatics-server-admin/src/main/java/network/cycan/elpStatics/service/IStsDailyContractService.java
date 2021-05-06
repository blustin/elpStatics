package network.cycan.elpStatics.service;

import network.cycan.elpStatics.model.entity.StsDailyContract;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
public interface IStsDailyContractService extends IService<StsDailyContract> {

    void dailyStatic(Date today);

}
