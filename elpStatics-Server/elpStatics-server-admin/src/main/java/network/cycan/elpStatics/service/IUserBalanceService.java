package network.cycan.elpStatics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.UserDto;
import network.cycan.elpStatics.model.entity.UserBalance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
public interface IUserBalanceService extends IService<UserBalance> {

    Long getMaxBlockNo(String userType);

    IPage<UserBalance> pageByCondition(UserDto dto);

}
