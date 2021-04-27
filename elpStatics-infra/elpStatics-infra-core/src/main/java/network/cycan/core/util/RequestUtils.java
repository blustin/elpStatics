package network.cycan.core.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 林坚丁
 * @date 2020/5/12 16:07
 * @Description
 */
public  abstract class RequestUtils {
    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String unknown = "unknown";
        String result = null;
        String[] headers = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP"};
        for (String header : headers) {
            result = request.getHeader(header);
            if (!BooleanUtils.isBlank(result) && !unknown.equalsIgnoreCase(result)) {
                break;
            }
        }
        if (BooleanUtils.isBlank(result) || unknown.equalsIgnoreCase(result)) {
            result = request.getRemoteAddr();
        }
        if (!BooleanUtils.isBlank(result)) {
            int comma = result.indexOf(",");
            if (comma != -1) {
                result = result.substring(0, comma);
            }
        }
        return result;
    }

    /**
     * 获取参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        return getParameterMap(request, false);
    }

    /**
     * 获取参数
     *
     * @param request
     * @param isFilterBlank 若为true则过滤空参数
     * @return
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request, boolean isFilterBlank) {
        if (request == null) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> names = request.getParameterNames();
        String name = null;
        while (names.hasMoreElements()) {
            name = (String) names.nextElement();
            String value = request.getParameter(name);
            if (!isFilterBlank || (isFilterBlank && !BooleanUtils.isBlank(value))) {
                value = value == null ? "" : value;
                map.put(name, value);
            }
        }
        return map;
    }

    /**
     * 获取url和参数
     *
     * @param request
     * @return
     */


    /**
     * 获取头部参数
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();
        Enumeration enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement().toString();
            result.put(name, request.getHeader(name));
        }
        return result;
    }

    /**
     * 是否ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String ajaxId = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(ajaxId);
    }

    /**
     * 是否排除的链接
     *
     * @param request        请求对象
     * @param excludeUrlList 过滤的链接
     * @return 若排除则返回true
     */
    public static boolean isExcludeUrl(HttpServletRequest request, List<String> excludeUrlList) {
        boolean result = false;
        String url = request.getRequestURI();
        int size = ListUtils.getSize(excludeUrlList);
        for (int i = 0; i < size; i++) {
            result = RegularUtils.isMatch(url, excludeUrlList.get(i));
            if (result) {
                break;
            }
        }
        return result;
    }
}
