package network.cycan.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author 林坚丁
 * @date 2020/5/12 16:10
 * @Description 正则表达式工具类
 */
public class RegularUtils {
    /**
     * 检验参数是否匹配正则表达式
     *
     * @param str
     * @param regex
     * @return
     */
    public static boolean isMatch(String str, String regex) {
        return isMatch(str, regex, 0);
    }

    /**
     * 检验参数是否匹配正则表达式
     *
     * @param str
     * @param regex
     * @param flags
     * @return
     */
    public static boolean isMatch(String str, String regex, int flags) {
        if (str == null || regex == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 正则表达式
     */
    public interface Regular {

        /**
         * 英文
         */
        String ENGLISH = "[a-zA-Z]+";

        /**
         * 数字
         */
        String NUMBER = "[\\d]+";

        /**
         * 英文和数字
         */
        String ENGLISH_NUMBER = "[a-zA-Z\\d]+";

        /**
         * 浮点数
         */
        String DOUBLE = "^[\\d]\\d*(\\.\\d+)?$";

        /**
         * 邮箱
         */
        String EMAIL = "^[A-Za-z0-9]+([\\w]+)*@[A-Za-z0-9]+(\\.[A-Za-z]+)+$";

        /**
         * 手机
         */
        String MOBILE = "^((13[0-9])|(14[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";

        /**
         * 生日
         */
        String BIRTH="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

        /**
         * 身份证
         */
        String IDCARD="(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";

        /**
         * 用户名  数字 字母 字符_ 最少2种
         */
        String USERNAME="^[A-Za-z]+([\\w]+)*{6,20}$";
        /**
         * 基础用户名
         */
        String BASE_USERNAME="[\\w]{3,20}";

        /**
         * 密码 由数字+字母，数字+特殊字符，字母+特殊字符，数字+字母+特殊字符组合
         */
        String PASSWORD="^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$";

        /**
         * 判断url
         */
        String URL = "/.*/";

    }
    public static void main(String[] args) {
        System.out.println(isMatch("13j", Regular.BASE_USERNAME));
    }
}
