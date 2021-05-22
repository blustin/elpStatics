package network.cycan.elpStatics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import network.cycan.elpStatics.model.dto.AirProjectDto;
import network.cycan.elpStatics.model.entity.AirProject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 空头项目名称 服务类
 * </p>
 *
 * @author blustin
 * @since 2021-05-21
 */
public interface IAirProjectService extends IService<AirProject> {
    IPage<AirProject> pageByCondition(AirProjectDto dto)  ;
}
