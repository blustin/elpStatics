package network.cycan.elpStatics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.StsDailyDto;
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

    IPage<StsDailyContract> pageByCondition(StsDailyDto dto);
}
