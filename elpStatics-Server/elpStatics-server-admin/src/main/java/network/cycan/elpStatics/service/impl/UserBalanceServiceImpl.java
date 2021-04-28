package network.cycan.elpStatics.service.impl;

import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.mapper.UserBalanceMapper;
import network.cycan.elpStatics.service.IUserBalanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
@Service
public class UserBalanceServiceImpl extends ServiceImpl<UserBalanceMapper, UserBalance> implements IUserBalanceService {

}
