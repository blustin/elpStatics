package network.cycan.elpStatics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.AirProjectAddressDto;
import network.cycan.elpStatics.model.dto.StsDailyDto;
import network.cycan.elpStatics.model.entity.AirProjectAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import network.cycan.elpStatics.model.entity.StsDailyContract;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blustin
 * @since 2021-05-21
 */
public interface IAirProjectAddressService extends IService<AirProjectAddress> {

      IPage<AirProjectAddress> pageByCondition(AirProjectAddressDto dto)  ;
      List<String> selectAddressList(AirProjectAddressDto dto);
      int  getAdderessCount(Long projectId,String userAdderss);
      List<String> selectExistAddressList(Long projectId,List<String> addressList);

}
