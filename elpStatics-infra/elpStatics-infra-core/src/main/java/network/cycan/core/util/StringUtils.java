package network.cycan.core.util;



import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import java.io.*;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 林坚丁
 * @date 2020/5/12 15:34
 * @Description
 */
public class StringUtils {
    private static final Log log = LogFactory.getLog(StringUtils.class);
    public static boolean isEmpty(String str){
        return null==str || str.trim().length()<=0;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    /**
     * 去除富文本中的html标签
     *
     * @param text
     * @return
     */
    public static String deleteHtmlTag(String text) {
        if (!StringUtils.isEmpty(text)) {
            return text.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
        }
        return "";
    }

    public static String subStringLeft(String source, int length) {
        if (StringUtils.isEmpty(source))
            return "";

        if (length <= 0)
            return "";

        if (length >= source.length())
            return source;

        return source.substring(0, length);
    }

    public static String subStringLeftRight(String source, int length) {
        if (StringUtils.isEmpty(source))
            return "";

        if (length <= 0)
            return "";

        if (length >= source.length())
            return source;

        int end = source.length();
        int start = end - length;

        return source.substring(start, end);
    }

    /**清空一个StringBuilder*/
    public static void clearSB(StringBuilder sb){
        if(sb!=null && sb.length()>0){
            sb.delete(0, sb.length()) ;
        }
    }

    /**清空一个StringBuffer*/
    public static void clearSB(StringBuffer sb){
        if(sb!=null && sb.length()>0){
            sb.delete(0, sb.length()) ;
        }
    }

    /** 替换换行符与空格 */
    public static String replaceHtml(String str){
        if(str!=null){
            return str.replaceAll("\r\n", "<br/>").replaceAll(" ", "&nbsp;");
        }
        return str;
    }

    /**读取文件内容*/
    public static String getFileContent(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "";
    }

    public static String removeHtmlTags(String str){
        if(StringUtils.isEmpty(str)){
            return "";
        }
        return str.replaceAll("<[^>]+>", "");
    }

    /**按byte截取字符串，中文等特殊字符不能截一半。*/
    public static String subStrBytes(String orignal, int length , String charset)   {
        if(StringUtils.isEmpty(orignal) || StringUtils.isEmpty(charset)){
            return orignal;
        }
        StringBuilder sb = new StringBuilder();
        try {
            int bytesLength = 0;//已经截取的字节数
            char[] charArray = orignal.toCharArray();
            char ch;//当前字符
            int currentCharBytesLength = 0;//当前字符的字节数
            for(int i=0;i<charArray.length;i++){
                ch = charArray[i];
                currentCharBytesLength = String.valueOf(ch).getBytes(charset).length;
                if(currentCharBytesLength>1){
                    bytesLength += 2;//该字符字节数大于1，在数据库占2个字节。
                }
                else{
                    bytesLength++;//该字符是一个字节，在数据库占1个字节。
                }
                if(bytesLength <= length){
                    sb.append(ch);
                }
                else{
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return sb.toString();
    }

    /**如果字节数大于1，是汉字*/
    public static boolean isChineseChar(char c , String charset) {
        try {
            return String.valueOf(c).getBytes(charset).length > 1;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return false;
    }

    /**将十六进制字符串4CFF120000DF转换为4C-FF-12-00-00-DF的格式，大写。*/
    public static String convertHex(String macAddress){
        if(StringUtils.isEmpty(macAddress)){
            return "";
        }
        String temp = "";
        try{
            if((macAddress.length()%2) != 0){
                macAddress = "0"+macAddress;
            }
            for(int i=0;i<macAddress.length();i++){
                if(i>0 && i%2==0){
                    temp = temp + "-";
                }
                temp = temp + macAddress.charAt(i);
            }
        }
        catch(Exception e){
            log.error(e.getMessage(),e);
        }
        return temp.toUpperCase();
    }

    /**将十六进制字符串前面补0，长度为length，返回大写*/
    public static String convertHex(String macAddress , int length){
        if(StringUtils.isEmpty(macAddress)){
            return "";
        }
        try{
            while(macAddress.length() < length){
                macAddress = "0"+macAddress;
            }
        }
        catch(Exception e){
            log.error(e.getMessage(),e);
        }
        return macAddress.toUpperCase();
    }

    public static String getClassAndMethod(String clazz){
        if(StringUtils.isEmpty(clazz) || !clazz.contains(".")){
            return "";
        }
        try{
            int index = clazz.lastIndexOf(".");//最后一个.
            String tmp = clazz.substring(0, index-1);//倒数第二个.
            index = tmp.lastIndexOf(".");
            return clazz.substring(index+1);//取类名.方法名的字符串，如“EmployeeDao.selectByPrimaryKey”
        }
        catch(Exception e){
            log.error("" , e);
        }
        return "";
    }

    /**
     * 把字符串的所有空白字符去掉（换行、tab、空格）
     * @param str
     * @return
     */
    public static String getStringNoBlank(String str) {
        if(str!=null && !"".equals(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        }else {
            return str;
        }
    }

    /**
     * 连续空白字符替换为一个空格
     * @param str
     * @return
     */
    public static String getStringOneBlank(String str) {
        if(!StringUtils.isEmpty(str)) {
            Pattern p = Pattern.compile("\\s+");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll(" ");
            return strNoBlank;
        }else {
            return str;
        }
    }

    /**
     * 取得本应用所在机器的ip
     * @return
     */
    public static String getCurrentIp(){
        String ip = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();//获得本机IP
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return ip;
    }

    /**
     * 首写字母变大写
     * @param src
     * @return
     */
    public static String firstToUpper(String src)
    {
        String first = src.substring(0,1);
        String upper = first.toUpperCase();
        return src.replaceFirst(first,upper);
    }

    /**
     * 首写字母变小写
     * @param src
     * @return
     */
    public static String firstToLower(String src)
    {
        String first = src.substring(0,1);
        String upper = first.toLowerCase();
        return src.replaceFirst(first,upper);
    }

    /**
     * 判断数组中的字符串是否全部为空。true全部为空，false至少有一个不为空。
     * @param str
     * @return
     */
    public static boolean isEmptyAll(String... str) {
        if(str==null || str.length<1){
            return true;
        }
        for(String one : str){
            if(!StringUtils.isEmpty(one)){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断数组中的字符串是否有一个为空。true有一个为空，false全部非空。
     * @param str
     * @return
     */
    public static boolean isEmptyOne(String... str) {
        if(str==null || str.length<1){
            return true;
        }
        for(String one : str){
            if(StringUtils.isEmpty(one)){
                return true;
            }
        }
        return false;
    }

    /**
     * 将字符串部分替换显示
     * @param source 源字符串
     * @param startPos 替换开始位置
     * @param length 替换长度
     * @param replaceString
     * @return
     */
    public static String replaceStringForShow(String source,int startPos,int length,String replaceString)
    {
        String tmp = "";
        String before = source.substring(0,startPos);
        String after = source.substring(startPos+length, source.length());
        for(int i=0;i<length;i++)
        {
            tmp += replaceString;
        }
        source = before + tmp + after;
        return source;
    }

    /**
     * 部分替换字符串
     * @param source 被替换字符串
     * @param startPos 开始位置
     * @param endPos 结束位置
     * @param other 替换字符串
     * @return
     */
    public static String replacePartOfStringWithOther(String source,int startPos,int endPos,String other)
    {
        String before = source.substring(0,startPos);
        String after = source.substring(endPos,source.length()-1);
        String newString = before + other + after;
        return newString;
    }

    /**
     * 截取字符串末尾的连续数字
     * @param src
     * @return
     */
    public static String getNumChar(String src){
        StringBuilder sb = new StringBuilder();
        if(src!=null && src.trim().length()>0){
            // 从字符串末尾开始查找
            for(int len = (src.length() - 1); len>=0 ; len--){
                char ch = src.charAt(len);
                if(Character.isDigit(ch)){
                    sb.insert(0, ch); // 在前面添加字符
                }
                else{
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**判断字符串是否相等(null、""视为相等)*/
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs1) && org.apache.commons.lang3.StringUtils.isBlank(cs2) ||
                cs1!=null && cs2!=null && cs1.toString().trim().equals(cs2.toString().trim()) ;
    }

    /**将异常的堆栈转换为字符串返回*/
    public static String getStackStrace(Throwable e) {
        try(    StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);  ){
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        } catch (Throwable eee) {
            log.error(eee.getMessage());
        }
        return null;
    }
    public static String subBefore(String string, String separator, boolean isLastSeparator) {
        if (!isEmpty(string) && separator != null) {
            String str = string.toString();
            String sep = separator.toString();
            if (sep.isEmpty()) {
                return "";
            } else {
                int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
                if (-1 == pos) {
                    return str;
                } else {
                    return 0 == pos ? "" : str.substring(0, pos);
                }
            }
        } else {
            return null == string ? null : string.toString();
        }
    }

    public static String subBefore(String string, char separator, boolean isLastSeparator) {
        if (isEmpty(string)) {
            return null == string ? null : string.toString();
        } else {
            String str = string.toString();
            int pos = isLastSeparator ? str.lastIndexOf(separator) : str.indexOf(separator);
            if (-1 == pos) {
                return str;
            } else {
                return 0 == pos ? "" : str.substring(0, pos);
            }
        }
    }

    public static String subAfter(String string, CharSequence separator, boolean isLastSeparator) {
        if (isEmpty(string)) {
            return null == string ? null : string.toString();
        } else if (separator == null) {
            return "";
        } else {
            String str = string.toString();
            String sep = separator.toString();
            int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
            return -1 != pos && string.length() - 1 != pos ? str.substring(pos + separator.length()) : "";
        }
    }

    public static String subAfter(String string, char separator, boolean isLastSeparator) {
        if (isEmpty(string)) {
            return null == string ? null : string.toString();
        } else {
            String str = string.toString();
            int pos = isLastSeparator ? str.lastIndexOf(separator) : str.indexOf(separator);
            return -1 == pos ? "" : str.substring(pos + 1);
        }
    }

    public static String subBetween(String str, CharSequence before, CharSequence after) {
        if (str != null && before != null && after != null) {
            String str2 = str.toString();
            String before2 = before.toString();
            String after2 = after.toString();
            int start = str2.indexOf(before2);
            if (start != -1) {
                int end = str2.indexOf(after2, start + before2.length());
                if (end != -1) {
                    return str2.substring(start + before2.length(), end);
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static String subBetween(String str, CharSequence beforeAndAfter) {
        return subBetween(str, beforeAndAfter, beforeAndAfter);
    }






    public static void main(String[] args){
        System.out.println("a.b.c".split("\\.")[0]);
    }

}
