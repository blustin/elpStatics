package network.cycan.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

/**
 * @author 林坚丁
 * @date 2020/5/10 17:36
 * @Description 日期工具类
 */
public class DateUtils {
    protected static Log logger = LogFactory.getLog(DateUtils.class);
    /**一天的秒数*/
    public final static int DAY_SECONDS = 24 * 60 * 60; // 一天的秒数
    public final static long daytimes = 24 * 60 * 60 * 1000;
    public final static long hourtimes = 1 * 60 * 60 * 1000;
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";

    public static Date parseAuto(String source) {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        SimpleDateFormat dfsFull = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat cfs = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isBlank(source))
            return null;
        try {
            if (source.indexOf('-') > 0) {// 以"-"分隔
                if (source.indexOf(':') > 0) {
                    return datetimeFormat.parse(source);
                } else {
                    return dateFormat.parse(source);
                }
            }
            if (source.indexOf('/') > 0) {// 以"/"分隔
                if (source.indexOf(':') > 0) {
                    return dfsFull.parse(source);
                } else {
                    return dfs.parse(source);
                }
            }
            try {
                return cfs.parse(source);
            } catch (Exception e) {
                return df.parse(source);
            }
        } catch (Exception e) {
            logger.error("格式化异常", e);
            return null;
        }
    }

    /**
     * 获得当前时间
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(today());
    }

    /**
     * 获得当前日期时间
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDatetime() {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return datetimeFormat.format(today());
    }

    /**
     * 获得当前日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return String
     */
    public static String currentDate() {
        return new SimpleDateFormat(YYYY_MM_DD).format(today());
    }

    /**
     * 获得当前月
     * <p>
     * 日期格式yyyy-MM
     *
     * @return String
     */
    public static String currentMonth() {
        return new SimpleDateFormat("yyyy-MM").format(today());
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return Date
     */
    public static Date today() {
        return new Date();
    }

    /**
     * 获取格式化之后的当前日期 日期格式yyyy-MM-dd
     */
    public static Date todayFormat() {
        try {
            return parseDate(currentDate());
        } catch (Exception e) {
            logger.error("格式化异常", e);
            return new Date();
        }
    }

    public static Date yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static Date yesterdayFormat() {
        try {
            return parseDate(yesterdayStr());
        } catch (ParseException e) {
            throw new RuntimeException("日期格式化错误",e);
        }
    }

    public static String yesterdayStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        return dateFormat.format(yesterday());
    }

    /**
     * 格式化时间
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        return dateFormat.format(date);
    }

    public static String formatDateForFootball(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(MM_DD_HH_MM_SS);
        return dateFormat.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy
     *
     * @return
     */
    public static String formatYear(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyyMMdd
     *
     * @return
     */
    public static String df(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyyMMddHHmm
     *
     * @return
     */
    public static String cf(Date date) {
        SimpleDateFormat cf = new SimpleDateFormat("yyyyMMddHHmm");
        return cf.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyyMMDDhhmmss
     *
     * @return
     */
    public static String cfs(Date date) {
        SimpleDateFormat cfs = new SimpleDateFormat("yyyyMMddHHmmss");
        return cfs.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyyMMdd HH:mm
     *
     * @return
     */
    public static String dt(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return dt.format(date);
    }

    /**
     * 格式化日期时间
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDatetime(Date date) {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return datetimeFormat.format(date);
    }

    /**
     * 格式化日期时间
     *
     * @param date
     * @param pattern
     *            格式化模式，详见{@link SimpleDateFormat}构造器
     *            <code>SimpleDateFormat(String pattern)</code>
     * @return
     */
    public static String formatDatetime(Date date, String pattern) {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(pattern);
        return datetimeFormat.format(date);
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前秒数
     *
     * @return
     */
    public static long second() {
        return millis() / 1000;
    }

    public static int year(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int month(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * 判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * 判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate
     *            日期范围开始
     * @param endDate
     *            日期范围结束
     * @param src
     *            需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.getTime() == src.getTime() || endDate.getTime() == src.getTime()
                || (beginDate.before(src) && endDate.after(src));
    }

    /**
     * 获取指定日期的月份
     *
     * @param date
     * @return
     */
    public static int MonthOfDate(Date date) {
        Calendar c = calendar();
        if (date == null) {
            date = new Date(); // 为空时当前时间计算
        }
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期的日期
     *
     * @param date
     * @return
     */
    public static int DayOfDate(Date date) {
        Calendar c = calendar();
        if (date == null) {
            date = new Date(); // 为空时当前时间计算
        }
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期当月最后一天 date为null时为当前月
     *
     * @param date
     * @return Date
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }

    /**
     * 判断日期是否是当月最后一天
     *
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        return c.getTime().compareTo(date) == 0;
    }

    /**
     * 获取指定日期当月第一天 date为null时为当前月
     *
     * @param date
     * @return Date
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));
        return c.getTime();
    }

    public static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat(YYYY_MM_DD).parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseYear(String date) throws ParseException {
        return new SimpleDateFormat("yyyy").parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     * <p>
     * 时间格式 HH:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.util.Date类型
     *
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern) throws ParseException {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * 时间转换（date转string）
     *
     * @param date
     *            需要转换的date
     * @param format
     *            格式化类型 eg.yyyy-MM-dd
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        if (date != null)
            return simpledateformat.format(date);
        else
            return "";
    }

    /**
     * 时间转换（string转date）
     *
     * @param str
     *            需要转换的data，string格式
     * @param format
     *            格式化类型 eg.yyyy-MM-dd
     */
    public static Date stringToDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(Boolean.FALSE);
        Date date = sdf.parse(str);
        return date;
    }

    /**
     * 获得当前日期的第几周
     *
     * @param date
     *            需要转换的date
     */
    public static int getWeekOfDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|w");
        String fmt = sdf.format(date);
        return new Integer(StringUtils.substringAfter(fmt, "|"));
    }

    /**
     * 以自定义方式验证是否为合法日期格式
     *
     * @param str
     *            需要转换的data，string格式
     * @param format
     *            格式化类型 eg.yyyy-MM-dd
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(Boolean.FALSE);
        try {
            sdf.parse(str);
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error("格式化异常", e);
            return Boolean.FALSE;
        }
    }

    /**
     * 按天数计算时间
     *
     * @param date
     *            传入时间
     * @param day
     *            需要计算的天数
     * @return 计算后的时间
     */
    public static Date getCalcDay(Date date, int day) {
        Calendar c = calendar();
        if (date == null) {
            date = new Date(); // 为空时当前时间计算
        }
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     * 在日历的天数上减少day天
     */
    public static Date getBeforeDay(int day) throws ParseException {
        Calendar c = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        Date date = dateFormat.parse(currentDate());// 初始日期
        c.setTime(date);// 设置日历时间
        c.add(Calendar.DATE, -day); // 在日历的天数上减少day天
        return c.getTime();
    }
    /**
     * 在日历的天数上加多day天
     */
    public static Date getAfterDay(int day)  {
        Calendar c = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        Date date = null;// 初始日期
        try {
            date = dateFormat.parse(currentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);// 设置日历时间
        c.add(Calendar.DATE, day); // 在日历的天数上减少day天
        return c.getTime();
    }


    /**
     * 在日历的月份上增加month个月
     */
    public static Date getBeforeMonth(int month) throws ParseException {
        Calendar c = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        Date date = dateFormat.parse(currentDate());// 初始日期
        c.setTime(date);// 设置日历时间
        c.add(Calendar.MONTH, month); // 在日历的月份上增加month个月
        return c.getTime();
    }

    /**
     * 在指定时间上增加月个数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonths(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    /**
     * 时间转日期
     *
     * @param date
     * @return
     */
    public static Date timeToDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date addDay(int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(today());
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }




    public static Date addHour(int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(today());
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    public static Date addMinute(int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(today());
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * 返回指定小时的时间
     *
     * @param hour
     * @return
     */
    public static Date getDateByHour(int hour) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 在指定时间上增加小时数
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    /**
     * 在指定时间上增加天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }

    public static Date addDay(Date date, int calendarField, int amount) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Timestamp addTimestamp(Timestamp date, int calendarField, int amount) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(calendarField, amount);
        return new Timestamp(c.getTimeInMillis());
    }

    public static Timestamp addDay(Timestamp date, int amount) {
        return addTimestamp(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Timestamp dateTimeTodate(Timestamp src) {
        Calendar c = calendar();
        c.setTimeInMillis(src.getTime());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());

    }

    /**
     * 在指定时间上增加年份
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(Calendar.YEAR, amount);
        return c.getTime();
    }

    /**
     * 计算两个时间差精确到（xxx天xx小时xx分xx秒）
     *
     * @param
     *            , data2
     * @return
     * @throws ParseException
     *
     */
    public static String getDateSubtract(Date date1, Date date2) throws ParseException {
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date now = datetimeFormat.parse(formatDatetime(date1));
        Date date = datetimeFormat.parse(formatDatetime(date2));
        long l;
        if (d1 > d2) {
            l = now.getTime() - date.getTime();

        } else {
            l = date.getTime() - now.getTime();
        }
        long day = l / (daytimes);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String dayday = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
        return dayday;
    }

    /**
     * 计算两个时间差，返回第一个不为0的单位 （如相差 0天1时2分3秒 返回1小时）
     *
     * @param
     *            ， end结束时间
     * @return
     * @throws ParseException
     *
     */
    public static String getDateSubtractMaxUnit(Date start, Date end) {
        long l;
        l = end.getTime() - start.getTime();
        if (l < 0) {
            return "刚刚";
        }
        long day = l / (daytimes);
        if (day > 0) {
            return day + "天";
        }
        long hour = (l / (60 * 60 * 1000) - day * 24);
        if (hour > 0) {
            return hour + "小时";
        }
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        if (min > 0) {
            return min + "分钟";
        }
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (s > 0) {
            return min + "秒";
        }
        return "刚刚";
    }

    /**
     * 计算两个时间相差天数
     *
     * @param date1
     * @param date2
     * @return long
     */
    public static long getDateSubDays(Date date1, Date date2) {
        try {
            date1 = getDateByFormat(date1);
            date2 = getDateByFormat(date2);
        } catch (Exception e) {
            logger.error("格式化异常", e);
            return 0L;
        }
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        long diff = 0L;
        if (d1 == d2) {
            return diff;
        } else if (d1 > d2) {
            diff = d1 - d2;
        } else {
            diff = d2 - d1;
        }
        return diff / (daytimes);
    }

    /**
     * 计算两个时间相差天数(date1-date2)带正负符号
     *
     * @param date1
     * @param date2
     * @return long
     */
    public static long getDateSubDaysSigned(Date date1, Date date2) {
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        long diff = d1 - d2;
        return diff / (daytimes);
    }

    /**
     * 计算两个时间相差月数
     *
     * @param date1
     * @param date2
     * @return long
     */
    public static int getDateSubMonths(Date date1, Date date2) {
        int result = 0;
        Calendar c1 = calendar();
        Calendar c2 = calendar();
        if (date2.after(date1)) {
            c1.setTime(date1);
            c2.setTime(date2);
        } else {
            c1.setTime(date2);
            c2.setTime(date1);
        }
        result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12
                + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 计算在债券转让的两个时间相差月数
     *
     * @param date1
     * @param date2
     * @return long
     */
    public static int getDebtDateSubMonths(Date date1, Date date2) {
        int result = 0;
        Calendar c1 = calendar();
        Calendar c2 = calendar();
        if (date2.after(date1)) {
            c1.setTime(date1);
            c2.setTime(date2);
        } else {
            c1.setTime(date2);
            c2.setTime(date1);
        }
        result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        // 如果C2的天数< c1的天数 就需要减少一个月  例如  12月1号 和 11月25号
        if (c2.get(Calendar.DAY_OF_MONTH) < c1.get(Calendar.DAY_OF_MONTH)) {
            result--;
        }

        return result < 0 ? 0 : Math.abs(result);
    }

    /**
     * 计算两个时间相差年数
     *
     * @param date1
     * @param date2
     * @return long
     */
    public static int getDateSubYear(Date date1, Date date2) {
        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        if (date2.after(date1)) {
            c1.setTime(date1);
            c2.setTime(date2);
        } else {
            c1.setTime(date2);
            c2.setTime(date1);
        }
        result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 按指定格式获得日期
     *
     * @param sourceDate
     * @param format
     * @return
     */
    public static Date getDateByFormat(Date sourceDate, String format) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(format);
        String dateString = f.format(sourceDate);
        Date date = f.parse(dateString);
        return date;
    }

    /**
     * 格式化日期为 yyyy-MM-dd
     *
     * @param sourceDate
     * @return Date
     * @throws ParseException
     */
    public static Date getDateByFormat(Date sourceDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        String dateString = dateFormat.format(sourceDate);
        Date date = parseDate(dateString);
        return date;
    }

    /**
     * 按指定格式获得日期
     *
     * @param sourceDateStr
     * @param format
     * @return
     */
    public static Date getDateByFormat(String sourceDateStr, String format) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = f.parse(sourceDateStr);
        return date;
    }

    /**
     * 判断是否超时
     *
     * @param startDate
     *            开始时间
     * @param intervalTime
     *            间隔时间，以毫秒为单位
     * @return
     * @throws Exception
     */
    public static boolean isTimeOut(Date startDate, long intervalTime) {
        boolean ret = false;
        Date currentDate = new Date();
        long minuteDif = currentDate.getTime() - startDate.getTime();
        if (minuteDif >= intervalTime) {
            ret = true;
        }
        return ret;
    }

    /**
     * 判断是否是在还款时间内还款 还款时间是指一天内可进行还款操作的时间 现规定可还款时间段为04:00:00--23:30:00
     *
     * @return
     * @throws Exception
     */
    public static boolean isRepaymentTime() throws Exception {

        boolean ret = false;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        String startDateStr = dateFormat.format(currentDate).concat(" 04:00:00");
        String ednDateStr = dateFormat.format(currentDate).concat(" 23:30:00");
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date startDate = datetimeFormat.parse(startDateStr);
        Date endDate = datetimeFormat.parse(ednDateStr);
        if (currentDate.getTime() > startDate.getTime() && currentDate.getTime() < endDate.getTime()) {
            ret = true;
        }
        return ret;
    }

    /***
     * 判断该字符串是否能转换成日期类型(用户查询条件的check)
     *
     * @param sourceStr
     * @return
     */
    public static boolean isIllegalDate(String sourceStr) {
        boolean ret = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        if (!(StringUtils.isBlank(sourceStr)) && !(StringUtils.isEmpty(sourceStr))) {
            try {
                Date date = dateFormat.parse(sourceStr);
                if (date != null)
                    return Boolean.TRUE;
            } catch (Exception e) {
                logger.error("格式化异常", e);
                return Boolean.FALSE;
            }
        }
        return ret;
    }

    /**
     * 获得指定日期的所在星期的星期一
     *
     * @param date
     * @return Date
     */
    public static Date getMondayByDate(Date date) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(Calendar.DATE, -(getNumByDate(date) - 1));
        return c.getTime();
    }

    /**
     * 获得指定日期的所在星期的星期日
     *
     * @param date
     * @return Date
     */
    public static Date getSundayByDate(Date date) {
        Calendar c = calendar();
        c.setTime(date);
        c.add(Calendar.DATE, 7 - getNumByDate(date));
        return c.getTime();
    }

    /**
     * 获得当前日期为这个星期的第几天 如：1 ： 星期一 、 7 ： 星期日
     *
     * @param date
     * @return int
     */
    public static int getNumByDate(Date date) {
        Calendar c = calendar();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - 1;
        return i != 0 ? i : 7;
    }

    /**
     * 获取日期距离今天的天数
     *
     * @param date
     * @return
     */
    public static int getDaysBetweenToday(Date date) {
        Calendar calendar = calendar();
        calendar.setTime(date);
        long betweenDays = (calendar.getTimeInMillis() - calendar().getTimeInMillis()) / (24 * 3600 * 1000);
        return Integer.parseInt(String.valueOf(betweenDays));
    }
    //获取
    public static int getDiffDays(Date oneDate, Date twoDate) {
        Long betweenDays = Math.abs(oneDate.getTime() - twoDate.getTime()) / (24 * 3600 * 1000);
        return betweenDays.intValue();
    }

    //获取UNIX
    public static Long getDiffSecond(Date oneDate,Date twoDate) {
        long betweenSecond = (oneDate.getTime() - twoDate.getTime()) / 1000;
        return betweenSecond;
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isTheSameDay(Date date1, Date date2) {
        Calendar c1 = calendar();
        c1.setTime(date1);
        Calendar c2 = calendar();
        c2.setTime(date2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 判断日期是否在这之前，只比较日期
     *
     * @param date
     * @return
     */
    public static boolean isBeforeToday(Date date) throws ParseException {
        if (date == null) {
            return Boolean.FALSE;
        }
        Date today = parseDate(currentDate());
        if (today.getTime() > date.getTime()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy/MM/dd
     *
     * @return
     */
    public static String formatDateYMD(Date date) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy/MM/dd");
        return dfs.format(date);
    }

    /**
     * 格式化日期 中文单位
     * <p>
     * 日期格式yyyy年MM月dd日
     *
     * @return
     */
    public static String formatDateYMDCn(Date date) {
        SimpleDateFormat dfsCn = new SimpleDateFormat("yyyy年MM月dd日");
        return dfsCn.format(date);
    }

    /**
     * 格式化日期 中文单位
     * <p>
     * 日期格式yyyy年MM月dd日
     *
     * @return
     */
    public static String formatDateTimeYMDCn(Date date) {
        SimpleDateFormat dfsCn = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        return dfsCn.format(date);
    }

    /**
     * 今日剩余毫秒数，即到明日00:00：00时的毫秒数
     */
    public static long todayRemainMills() {
        Date now = today();
        Date tomorrow = addDay(todayFormat(), 1);
        return tomorrow.getTime() - now.getTime();
    }

    /**
     * 返回较大的时间
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Date max(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return date1.after(date2) ? date2 : date1;

    }

    /**
     * 返回较小的时间
     *
     * @param date1
     * @param date2
     */
    public static Date min(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return date1.after(date2) ? date1 : date2;

    }

    /**
     *
     * 返回明天
     */
    public static Date tomorrow() {
        Calendar calendar = calendar();
        calendar.add(Calendar.DATE,1);
        return calendar.getTime();
    }

    /**
     * 返回明天日期
     *
     */
    public static Date tomorrowDate() {
        try {
            return getDateByFormat( tomorrow());
        } catch (ParseException e) {
            logger.error("format date error",e);
            return null;
        }
    }

    /**取得seconds秒后的日期，正数为后，负数前*/
    public static Date nextDateSec(long seconds){
        Date now = new Date();
        now.setTime(now.getTime() + seconds * 1000);
        return now;
    }

    /**取得minutes分钟后的日期，正数为后，负数前*/
    public static Date nextDateMin(long minutes){
        return nextDateSec( minutes * 60 );
    }

    /**取得hours小时后的日期，正数为后，负数前*/
    public static Date nextDateHour(long hours){
        return nextDateMin( hours * 60 );
    }

    /**取得days天后的日期，正数为后，负数前*/
    public static Date nextDateDay(long days){
        return nextDateHour( days * 24 );
    }

    /**取得T+n天后的最后一秒的日期对象*/
    public static Date nextDateDayEnd(long days){
        try {
            String today = currentDate() + " 23:59:59"; // 今天最后一秒
            Date dateTimeEnd = parseDatetime(today);
            dateTimeEnd.setTime( dateTimeEnd.getTime() + days * 24 * 60 * 60 * 1000 );
            return dateTimeEnd;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定的日期，精确到分秒
     * @param hour
     * @param minute
     * @param second
     * @param
     * @param args
     * @return
     */
    public static Date getNeedTime(int hour,int minute,int second,Date date ,int ...args){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        if(args.length==1){
            calendar.set(Calendar.MILLISECOND,args[0]);
        }
        return calendar.getTime();
    }

    /**
     * 格式化时间
     * <p>
     * 时间格式HHmmss
     *
     * @return
     */
    public static String formatTimes(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        return timeFormat.format(date);
    }

    /**
     * 判断当前日期的day是否为奇数
     * @return
     */
    public static boolean isOddDate(){
        boolean flag = true;
        String dateToString = dateToString(new Date(), YYYY_MM_DD);
        int dd = NumberUtils.toInt(dateToString.substring(8));
        if(dd % 2 == 0){
            flag = false;
        }
        return flag;
    }


    /**
     * 判断当前日期的day是否是3的倍数
     * @return
     */
    public static boolean is3Multiple(){
        boolean flag = false;
        String dateToString = dateToString(new Date(), YYYY_MM_DD);
        int dd = NumberUtils.toInt(dateToString.substring(8));
        if(dd % 3 == 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 舍去时间搓后3位
     * @return
     */
    public static int convertDateTamsp(long time){
        int times=(int) (time/1000);
        return times;
    }

    /**
     * 获取时间的当天开始时间
     * @param date
     * @return
     */
    public static Date getBeginDate(Date date) {
        String dateToString = dateToString(date, YYYY_MM_DD);
        Date timeDate;
        try {
            timeDate = parseDatetime(dateToString + " 00:00:00");
        } catch (ParseException e) {
            return date;
        }
        return timeDate;
    }

    /**
     * 获取时间的当天结束时间
     * @param date
     * @return
     */
    public static Date getEndDate(Date date) {
        String dateToString = dateToString(date, YYYY_MM_DD);
        Date timeDate;
        try {
            timeDate = parseDatetime(dateToString + " 23:59:59");
        } catch (ParseException e) {
            return date;
        }
        return timeDate;
    }

    /**
     * 返回当前时间
     * @return
     */
    public static String getNowTime(String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String time=sdf.format(new Date());
        return time;
    }

    public static Date stampToDate2(String format){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(format);
        Date date = new Date(lt);
        String res = sdf.format(date);
        return date;
    }


    public static void main(String[] args) throws ParseException {
//		Date date = parseDate("2018-12-29");
//		System.out.println(isHoliday(date));
        Date d = makeDatetime("2019-1-1",17,10,5);
        System.out.println(d);
        System.out.println(isCurrentMonth(d));
        System.out.println(preMonthLastDay(d));
    }

    public static Date weekAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    /**
     * 校验是否当前月份
     *
     */
    public static boolean isCurrentMonth(Date date) {
        if(date == null) {
            return false;
        }
        Date today = today();
        return (today.getYear() == date.getYear() && today.getMonth() == date.getMonth());
    }

    /***
     * 当前日期的上一个月的最后一天
     * @param date
     * @return
     */
    public static Date preMonthLastDay(Date date) {
        if(date == null) {
            date = todayFormat();
        }
        Date sameDayOfPreMonth = addMonths(date,-1);
        return getLastDayOfMonth(sameDayOfPreMonth);
    }

    /**
     * 自定义时间
     * @param date 形如 yyyy-MM-dd 为null表示当前日期
     * @param hour int
     * @param minute int
     * @param second int
     * @return
     */
    public static Date makeDatetime(String date, int hour, int minute, int second) throws ParseException{
        if(date == null || "".equals(date)) {
            date = currentDate();
        }
        Date d = parseDate(date);
        if(hour > 23 || hour < 0) {
            hour = 0;
        }
        if(minute > 60 || minute < 0) {
            minute = 0;
        }
        if(second > 60 || second < 0) {
            second = 0;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR,hour);
        c.add(Calendar.MINUTE,minute);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
