package network.cycan.core.constant;

/**
 * @author 林坚丁
 * @date 2020/5/12 16:02
 * @Description
 */
public interface CommonConstant {
    /**
     * 路径分隔符
     */
    char PATH_SEPARATOR = '/';

    /**
     * 以逗号或分号分隔
     */
    String COMMA_SEMICOLON = "\\s*,\\s*|\\s*;\\s*";

    /**
     * utf-8编码
     */
    String UTF_8 = "utf-8";

    /**
     * gbk编码
     */
    String GBK = "gbk";

    /**
     * resources目录
     */
    String RESOURCES = "resources";

    /**
     * contextPath目录
     */
    String CONTEXT_PATH = "contextPath";

    /**
     * version版本号
     */
    String VERSION = "version";

    /**
     * id
     */
    String ID = "id";

    /**
     * ?
     */
    String QUESTION = "?";

    /**
     * &
     */
    String AND = "&";

    /**
     * =
     */
    String EQUALS = "=";

    /**
     * 1
     */
    int ONE = 1;

    /**
     * 2
     */
    int TWO = 2;

    /**
     * 0
     */
    int ZERO = 0;

    /**
     * -1
     */
    int NEGATIVE_ONE = -1;

    /**
     * success
     */
    String SUCCESS = "success";

    /**
     * result
     */
    String RESULT = "result";

    /**
     * message
     */
    String MESSAGE = "message";

    /**
     * code
     */
    String CODE = "code";

    /**
     * 分页查询数据
     */
    String DATAS = "datas";

    /**
     * failure
     */
    String FAILURE = "failure";

    /**
     * 发生异常时要跳转的页面名字和从request中获取异常信息的name
     */
    String EXCEPTION = "exception";

    /**
     * 用户的ip地址
     */
    String IP = "ip";

    /**
     * 记录方法的开始时间，单位毫秒
     */
    String BEGIN_TIME = "beginTime";

    /**
     * 保存用户标志
     */
    String USERID = "userId";

    /**
     * 机构id
     */
    String MECHANISM_ID = "mechanismId";


    /**
     * 部门id
     */
    String ORGANIZATION_ID = "organizationId";

    /**
     * 用户登录ticket
     */
    String TICKET = "ticket";

    /**
     * 根ID，-1
     */
    long ROOT_ID = 0;

    /**
     * 上下级编码时的根编码
     */
    String ROOT_CODE = "10000000";

    /**
     * url
     */
    String URL = "url";

    /**
     * 系统容量capacity
     */
    String CAPACITY = "capacity";

    /**
     * 丢弃请求的间隔，单位毫秒
     */
    String ABANDON_INTERVAL = "abandon.interval";

    /**
     * 文本text/plain
     */
    String TEXT_PLAIN = "text/plain";

    /**
     * 短信验证码位数
     */
    String SMS_CODE_DIGIT = "sms.code.digit";

    /**
     * 短信密码验证码位数
     */
    String SMS_PWD_DIGIT = "sms.pwd.digit";

    /**
     * 短信验证码保存时间
     */
    String SMS_CODE_EXPIRETIME = "sms.code.expiretime";

    /**
     * 短信密码保存时间
     */
    String SMS_PWD_EXPIRETIME = "sms.pwd.expiretime";

    /**
     * 用户信息保存时间
     */
    String UBS_USER_EXPIRETIME = "ubs.user.expiretime";

    /**
     * 随机数保存时间
     */
    String UBS_RANDOM_EXPIRETIME = "ubs.random.expiretime";

    /**
     * 字体
     */
    String COMMON_SERVICE_FONT = "font";

    /**
     * 图片验证码
     */
    String COMMON_SERVICE_CAPTCHA = "captcha";

    /**
     * 随机数
     */
    String UUID = "uuid";

    /**
     * 图片验证码保存时间
     */
    String CAPTCHA_EXPIRE_TIME = "captcha.expire.time";

    /**
     * 手机号
     */
    String MOBILE_USERNAME = "username";

    /**
     * cookie域
     */
    String COOKIE_DOMAIN = "/";

    /**
     * 加密值
     */
    String SIGN = "sign";




    /**
     * des加密
     */
    String DES_KEY = "des.key";

    /**
     * Jsonp请求格式参数
     */
    String CALLBACK = "callback";





    /**
     * post请求
     */
    String POSTMETHOD = "post";

    /**
     * get请求
     */
    String GSTMETHOD = "get";



    /**
     * 用户登录的链接
     */
    String LOGIN_URL = "login.url";

    /**
     * cookie加密值
     */
    String COOKIE_SIGN = "cookie.sign";

    /**
     * cookie的时间
     */
    String COOKIE_TIME = "cookie.time";


    /**
     * 授权资源
     */
    String AUTHORIZE_MAP = "authorizeMap";



    /**
     * 数量
     */
    String NUMBER = "number";


    /**
     * 是否强制校验TGT
     */
    String FORCE_VALIDATE_TGT = "force.validate.tgt";

    /**
     * 是否强制校验AJAX
     */
    String FORCE_VALIDATE_AJAX = "force.validate.ajax";



}
