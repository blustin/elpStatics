package network.cycan.core.base;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import network.cycan.core.util.UUIDUtils;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author 林坚丁
 * @date 2020/5/10 11:53
 * @Description
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
	
    protected Log logger = LogFactory.getLog(getClass());
    
    /**
     * 是否继承 BaseEntity
     * @param clz clz
     * @return 是否继承
     */
    public boolean isExtendsBaseEntity(Class<?> clz) {
    	if (clz == Object.class) {
    		return false;
		} else {
			Class<?> clzSuper = clz.getSuperclass();
			if (clzSuper == BaseEntity.class) {
				return true;
			} else {
				return isExtendsBaseEntity(clzSuper);
			}
		}
    }
    











}

