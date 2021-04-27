package network.cycan.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页参数
 *
 * @param <T>
 * @author liuq
 * @since 2020-05-15
 */
public class PageParam<T> extends Page<T> {

	private static final long serialVersionUID = -1658166454440173166L;
	
	@ApiModelProperty(hidden = true)
    private String records;
	
	@ApiModelProperty(value = "column：排序字段；asc：true正序，false倒序")
	private List<OrderItem> orders = new ArrayList<>();
	
	@ApiModelProperty(hidden = true)
    private long total;
	
	@ApiModelProperty(value = "每页显示条数，默认 10")
    private long size;

	@ApiModelProperty(value = "当前页")
    private long current;
	
	/**
	 * <p>如果排序字段传的是对象的属性，使用此方法转成数据库表字段
	 * 
	 * <p>有@TableField注解的转成注解的value,没有的驼峰转下划线
	 * 
	 * @param clz 有排序字段对应属性的对象
	 */
	public void changeOrders(Class<?> clz) {
		for (OrderItem orderItem : getOrders()) {
			try {
				String column = orderItem.getColumn();
				Field field = getField(clz, column);
				TableField tableField = field.getAnnotation(TableField.class);
				if (tableField == null) {
					column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, column);
				} else {
					column = tableField.value();
				}
				orderItem.setColumn(column);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/**
	 * 递归获得类属性
	 * 
	 * @param clz 类
	 * @param column 属性名
	 * @return Field
	 * @throws Exception 异常
	 */
	public Field getField(Class<?> clz, String column) throws Exception {
		Field field;
		try {
			field = clz.getDeclaredField(column);
		} catch (NoSuchFieldException e) {
			Class<?> clzSuper = clz.getSuperclass();
			if (Object.class == clzSuper) {
				throw e;
			} else {
				field = getField(clzSuper, column);
			}
		}
		return field;
	}

}
