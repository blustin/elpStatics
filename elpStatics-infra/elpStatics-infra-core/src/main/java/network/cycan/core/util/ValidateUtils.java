package network.cycan.core.util;



import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 林坚丁
 * @date 2020/5/9 11:29
 * @Description 常规验证处理工具类
 */
public abstract class ValidateUtils {
    // 整数
    private static final String V_INTEGER = "^-?[1-9]\\d*$";

    // 正整数
    private static final String V_Z_INDEX = "^[1-9]\\d*$";

    // 负整数
    private static final String V_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    // 数字
    private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";

    // 正数
    private static final String V_POSITIVE_NUMBER = "^[1-9]\\d*|0$";

    // 负数
    private static final String V_NEGATINE_NUMBER = "^-[1-9]\\d*|0$";

    // 浮点数
    private static final String V_FLOAT = "^([+-]?)\\d*\\.\\d+$";

    // 正浮点数
    private static final String V_POSTTIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

    // 负浮点数
    private static final String V_NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

    // 非负浮点数（正浮点数 + 0）
    private static final String V_UNPOSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

    // 非正浮点数（负浮点数 + 0）
    private static final String V_UN_NEGATIVE_FLOAT = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

    // 邮件
    private static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    // 颜色
    private static final String V_COLOR = "^[a-fA-F0-9]{6}$";

    // url
    private static final String V_URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    // 仅中文
    private static final String V_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    // 仅ACSII字符
    private static final String V_ASCII = "^[\\x00-\\xFF]+$";

    // 邮编
    private static final String V_ZIPCODE = "^\\d{6}$";

    // 手机
    private static final String V_MOBILE = "^(13|14|15|17|18)[0-9]{9}$";

    // ip地址
    private static final String V_IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    // 非空
    private static final String V_NOTEMPTY = "^\\S+$";

    // 图片
    private static final String V_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

    // 文档
    private static final String V_DOCUMENT = "(.*)\\.(xls|xlsx|pdf|doc|docx|pptx|ppt)$";

    // 压缩文件
    private static final String V_RAR = "(.*)\\.(rar|zip|7zip|tgz|gz|tg)$";

    // 日期
    private static final String V_DATE = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

    // QQ号码
    private static final String V_QQ_NUMBER = "^[1-9]*[1-9][0-9]*$";

    // 电话号码的函数(包括验证国内区号,国际区号,分机号)
    private static final String V_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    // 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
    private static final String V_USERNAME = "^\\w+$";

    // 字母
    private static final String V_LETTER = "^[A-Za-z]+$";

    // 大写字母
    private static final String V_LETTER_U = "^[A-Z]+$";

    // 小写字母
    private static final String V_LETTER_I = "^[a-z]+$";

    // 身份证
    private static final String V_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    // 数字和字母
    private static final String V_ABC_NUM = "^[0-9a-zA-Z]+$";

    // 验证密码长度(6-18位)
    private static final String V_PASSWORD_LENGTH = "^\\d{6,18}$";

    // 验证两位数
    private static final String V_TWO＿POINT = "^[0-9]+(.[0-9]{2})?$";

    // 验证一个月的31天
    private static final String V_31DAYS = "^((0?[1-9])|((1|2)[0-9])|30|31)$";

    // 是否是投资页面
    private static final String V_INVEST_URL_REG = "/invest/(loan|transfer|current|hplan)/\\w{32}";

    // 是否是体验标页面
    private static final String V_TASTE_URL_REG = "/taste/loan/\\w{32}";

    // 是否是安全中心页面
    private static final String V_SECURITY_URL_REG = "/secure/(security|security\\?v=\\d{1})";

    // 是否是自动投标设置页面
    private static final String V_AUTO_BID_REG = "/auto-bid";

    // 是否是债权转让页面
    private static final String V_TRANSFER_REG = "/assets/list";

    // 是否是问卷调查页面
    private static final String V_HELP_QNAIRE = "/help/qnaire";

    // 是否是体验标页面
    private static final String V_EVENT_URL_REG = "/event/\\w+";

    // 是否是活动页面
    private static final String V_ACT_REG = "/act/\\w+";

    // 是否是推荐奖励
    private static final String V_SPREAD = "/spread";

    /**
     * 验证是不是整数
     *
     * @param value 要验证的字符串 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isInteger(String value) {
        return match(V_INTEGER, value);
    }

    /**
     * 验证是不是正整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isZIndex(String value) {
        return match(V_Z_INDEX, value);
    }

    /**
     * 验证是不是负整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNegativeInteger(String value) {
        return match(V_NEGATIVE_INTEGER, value);
    }

    /**
     * 验证是不是数字
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNumber(String value) {
        return match(V_NUMBER, value);
    }

    /**
     * 验证是不是正数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPositiveNumber(String value) {
        return match(V_POSITIVE_NUMBER, value);
    }

    /**
     * 验证是不是负数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNegatineNumber(String value) {
        return match(V_NEGATINE_NUMBER, value);
    }

    /**
     * 验证一个月的31天
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean is31Days(String value) {
        return match(V_31DAYS, value);
    }

    /**
     * 验证是不是ASCII
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isASCII(String value) {
        return match(V_ASCII, value);
    }

    /**
     * 验证是不是中文
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isChinese(String value) {
        return match(V_CHINESE, value);
    }

    /**
     * 验证是不是颜色
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isColor(String value) {
        return match(V_COLOR, value);
    }

    /**
     * 验证是不是日期
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isDate(String value) {
        return match(V_DATE, value);
    }

    /**
     * 验证是不是邮箱地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isEmail(String value) {
        return match(V_EMAIL, value);
    }

    /**
     * 验证是不是浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isFloat(String value) {
        return match(V_FLOAT, value);
    }

    /**
     * 验证是不是正确的身份证号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIDcard(String value) {
        return match(V_IDCARD, value);
    }

    /**
     * 验证是不是正确的IP地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIP4(String value) {
        return match(V_IP4, value);
    }

    /**
     * 验证是不是字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLetter(String value) {
        return match(V_LETTER, value);
    }

    /**
     * 验证是不是小写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLetterI(String value) {
        return match(V_LETTER_I, value);
    }

    /**
     * 验证是不是大写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLetterU(String value) {
        return match(V_LETTER_U, value);
    }

    /**
     * 验证是不是手机号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isMobile(String value) {
        return match(V_MOBILE, value);
    }

    /**
     * 验证是不是负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNegativeFloat(String value) {
        return match(V_NEGATIVE_FLOAT, value);
    }

    /**
     * 验证非空
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNotempty(String value) {
        return match(V_NOTEMPTY, value);
    }

    /**
     * 验证密码的长度(6~18位)
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNumberLength(String value) {
        return match(V_PASSWORD_LENGTH, value);
    }

    /**
     * 是否只有数字和英文
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
//    public static boolean isNumAbc(String value) {
//        if (StringUtils.isBlank(value))
//            return Boolean.FALSE;
//        return match(V_ABC_NUM, value);
//    }

    /**
     * 验证图片
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPicture(String value) {
        return match(V_PICTURE, value);
    }

    /**
     * 验证文档
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isDocument(String value) {
        return match(V_DOCUMENT, value);
    }

    /**
     * 验证正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPosttiveFloat(String value) {
        return match(V_POSTTIVE_FLOAT, value);
    }

    /**
     * 验证QQ号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isQQnumber(String value) {
        return match(V_QQ_NUMBER, value);
    }

    /**
     * 验证压缩文件
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isRar(String value) {
        return match(V_RAR, value);
    }

    /**
     * 验证电话
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isTel(String value) {
        return match(V_TEL, value);
    }

    /**
     * 验证两位小数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isTwoPoint(String value) {
        return match(V_TWO＿POINT, value);
    }

    /**
     * 验证非正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUnNegativeFloat(String value) {
        return match(V_UN_NEGATIVE_FLOAT, value);
    }

    /**
     * 验证非负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUnpositiveFloat(String value) {
        return match(V_UNPOSITIVE_FLOAT, value);
    }

    /**
     * 验证URL
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUrl(String value) {
        return match(V_URL, value);
    }

    /**
     * 验证用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUserName(String value) {
        return match(V_USERNAME, value);
    }

    /**
     * 验证邮编
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isZipcode(String value) {
        return match(V_ZIPCODE, value);
    }

    /**
     * 自定义验证
     *
     * @param regex 验证正则
     * @param str   要验证的字符串
     * @return
     */
    public static boolean isCustom(String regex, String str) {
        return match(regex, str);
    }

    /**
     * 是否是投资页面
     */
    public static boolean isInvestUrl(String str) {
        return match(V_INVEST_URL_REG, str);
    }

    /**
     * 是否是体验标页面
     */
    public static boolean isTasteUrl(String str) {
        return match(V_TASTE_URL_REG, str);
    }

    /**
     * 是否是安全中心
     */
    public static boolean isSecurityUrl(String str) {
        return match(V_SECURITY_URL_REG, str);
    }

    /**
     * 是否是安全中心
     */
    public static boolean isAutoBidUrl(String str) {
        return V_AUTO_BID_REG.equals(str);
    }

    /**
     * 是否推荐奖励
     */
    public static boolean isSpread(String str) {
        return V_SPREAD.equals(str);
    }

    /**
     * 是否是安全中心
     */
    public static boolean isTransferUrl(String str) {
        return V_TRANSFER_REG.equals(str);
    }

    /**
     * 是否是问卷调查
     */
    public static boolean isHelpQnaire(String str) {
        return V_HELP_QNAIRE.equals(str);
    }

    /**
     * 是否是活动页面
     */
    public static boolean isEventUrl(String str) {
        return match(V_EVENT_URL_REG, str);
    }

    /**
     * 是否是分享活动页面
     */
    public static boolean isActMainUrl(String str) {
        return match(V_ACT_REG, str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 格式化文件大小
     *
     * @param size
     * @return
     */
    public static String getDataSize(Double size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            float kbsize = (float) (size / 1024f);
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = (float) (size / 1024f / 1024f);
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = (float) (size / 1024f / 1024f / 1024f);
            return formater.format(gbsize) + "GB";
        }
        return null;
    }

//    public static boolean isQrcodeUrl(String callbackUrl) {
//        if (StringUtils.isNotBlank(callbackUrl)) {
//            return V_HELP_QNAIRE.equals(callbackUrl);
//        }
//
//        return false;
//
//    }
}
