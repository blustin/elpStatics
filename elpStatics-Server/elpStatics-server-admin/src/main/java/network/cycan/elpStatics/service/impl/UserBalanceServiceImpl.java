package network.cycan.elpStatics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import network.cycan.elpStatics.model.entity.UserBalance;
import network.cycan.elpStatics.mapper.UserBalanceMapper;
import network.cycan.elpStatics.service.IUserBalanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

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

    @Override
    public Long getMaxBlockNo(String userType) {
        QueryWrapper<UserBalance>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userType",userType);
        queryWrapper.orderByDesc("blockNum").last("limit 1");
        UserBalance userBalance=  getOne(queryWrapper);
        if(userBalance==null) {
            return  0L;
        }
        return userBalance.getBlockNum();
    }
}
