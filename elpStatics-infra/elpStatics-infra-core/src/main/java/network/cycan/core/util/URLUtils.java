package network.cycan.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林坚丁
 * @date 2020/5/12 15:45
 * @Description
 */
public class URLUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLUtils.class);
    /**获取url中?前面的路径（不含?）*/
    public static String getPage(String strURL) {
        String strPage = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }
        return strPage;
    }

    /**截取url中的?后面的参数部分（不含?）*/
    private static String getParamStr(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    /**将url中参数部分转换为Map返回*/
    public static Map<String, String> getParamMap(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = getParamStr(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) {
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                mapRequest.put(arrSplitEqual[0], "");
            }
        }
        return mapRequest;
    }

    /**带端口则返回true*/
    public static boolean hasPort(String url){
        if(StringUtils.isEmpty(url)){
            return false;
        }
        String tmp = url.toLowerCase().trim(); // 小写，去前后空格
        if(tmp.startsWith("http://")){
            return tmp.lastIndexOf(":")>4;
        }else{
            return tmp.lastIndexOf(":")>=0;
        }
    }

    /**不带端口返回true*/
    public static boolean hasNoPort(String url){
        return !hasPort(url);
    }

    /**带端口则返回true*/
    public static boolean hasPort(StringBuilder url){
        return url!=null && hasPort(url.toString());
    }

    /**不带端口返回true*/
    public static boolean hasNoPort(StringBuilder url){
        return url!=null && !hasPort(url);
    }

    /**获得访问URL的从context后面的字符串，以/开头。*/
    public static String getRequestUri(final HttpServletRequest request){
        try{
            StringBuilder uri = new StringBuilder(request.getRequestURI());
            if(uri!=null && uri.length()>0){
                uri.delete(0, request.getContextPath().length());
                if(uri.indexOf("?")>0){
                    uri.delete(uri.indexOf("?"), uri.length());
                }
                if(uri.indexOf(";")>0){
                    uri.delete(uri.indexOf(";"), uri.length());
                }
            }
            return uri.toString().trim();
        }
        catch(Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }
    /**
     * 连接两个路径
     *
     * @param rootPath
     * @param relativePath
     * @return
     */
    public static String getRealPath(String rootPath, String relativePath) {
        if (BooleanUtils.isBlank(rootPath)) {
            return relativePath;
        }

        String result = rootPath;
        if (!BooleanUtils.isBlank(relativePath)) {
            boolean isEnd = false;
            int rootPathLength = rootPath.length() - 1;
            if (rootPath.charAt(rootPathLength) == File.separatorChar) {
                isEnd = true;
            }

            boolean isBegin = false;
            if (relativePath.charAt(0) == File.separatorChar) {
                isBegin = true;
            }

            if (isEnd && isBegin) {
                rootPath = rootPath.substring(0, rootPathLength);
            }
            else if (!isEnd && !isBegin) {
                rootPath +=File.separatorChar;
            }
            result = rootPath + relativePath;
        }
        return result;
    }

}
