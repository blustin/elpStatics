package network.cycan.core.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author liuq
 * @date 2020/5/14 18:00
 * @Description 世界时间工具类
 */
public class UTCTimeUtils {
	
	public static Date getUTCTime() {
	    // 1、取得本地时间：
	    Calendar cal = Calendar.getInstance() ;
	    // 2、取得时间偏移量：
	    int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
	    // 3、取得夏令时差：
	    int dstOffset = cal.get(Calendar.DST_OFFSET);
	    // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
	    cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
	    return cal.getTime() ;
	}

}
