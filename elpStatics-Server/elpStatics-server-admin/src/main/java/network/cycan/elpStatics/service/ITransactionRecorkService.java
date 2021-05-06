package network.cycan.elpStatics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.TransactionDto;
import network.cycan.elpStatics.model.entity.TransactionRecork;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blustin
 * @since 2021-04-28
 */
public interface ITransactionRecorkService extends IService<TransactionRecork> {

    IPage<TransactionRecork> pageByCondition(TransactionDto dto);
}
