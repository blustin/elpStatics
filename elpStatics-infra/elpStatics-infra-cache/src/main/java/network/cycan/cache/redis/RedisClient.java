package network.cycan.cache.redis;

import network.cycan.core.exception.RedisException;
import network.cycan.core.util.GeneralProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * <p>
 * RedisClient 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/27 16:56
 */
public class RedisClient {
    private static JedisPool jedisPool = null; // 只能初始化一次
    int dbNum = 0;
    private Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private String host;

    private String port;

    private String password;
    private int timeout = 10000;
    private GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
    private volatile boolean isRun = Boolean.FALSE;

    public static RedisClient getInstance() {
        return SingletonHolder.instance;
    }

    public Jedis getJedis() throws RedisException {
        try {
            this.start();
            return jedisPool.getResource();
        } catch (Exception e) {
            throw new RedisException(" getJedis Exception", e);
        }
    }

    public void releaseJedis(Jedis jedis) throws RedisException {
        try {
            // this.start();
            // jedisPool.returnResource(jedis);
            // 升级为2.7.2后 returnResource 废除
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            throw new RedisException(" releaseJedis Exception", e);
        }
    }

    void start() {
        if (!isRun) {
            this.init(GeneralProperties.getProperty("jedis_host", host),
                    GeneralProperties.getProperty("jedis_port", port),
                    GeneralProperties.getProperty("jedis_password", password));
            isRun = !isRun;
        }
    }

    // 初始化客户端
    public synchronized boolean init(String setHost, String setPort, String setPassword) {
        try {
            if (null != jedisPool) {
                return true;
            }
            this.logger.info("init RedisClient:host={} port={}", setHost, setPort);
            String maxActive = GeneralProperties.getProperty("jedis.maxActive", "1000");
            String maxIdle = GeneralProperties.getProperty("jedis.maxIdle", "1000");
            String minIdle = GeneralProperties.getProperty("jedis.minIdle", "10");
            String maxWait = GeneralProperties.getProperty("jedis.maxWait", "20000");
            String minEvictableIdleTimeMillis = GeneralProperties.getProperty("jedis.minEvictableIdleTimeMillis");
            String timeBetweenEvictionRunsMillis = GeneralProperties.getProperty("jedis.timeBetweenEvictionRunsMillis");
            poolConfig.setMaxTotal(Integer.parseInt(StringUtils.isBlank(maxActive) ? "1000" : maxActive.trim()));
            poolConfig.setMaxIdle(Integer.parseInt(StringUtils.isBlank(maxIdle) ? "1000" : maxIdle.trim()));
            poolConfig.setMinIdle(Integer.parseInt(StringUtils.isBlank(minIdle) ? "10" : minIdle.trim()));
            poolConfig.setMaxWaitMillis(Long.parseLong(StringUtils.isBlank(maxWait) ? "20000" : maxWait.trim()));
            if (StringUtils.isNotBlank(minEvictableIdleTimeMillis)) // 设定多长时间视为失效链接
                poolConfig.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis.trim()));
            if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)) // 设定每隔多长时间进行有效检查与上面参数同时使用
                poolConfig.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis.trim()));
            jedisPool = new JedisPool(poolConfig, setHost, Integer.parseInt(setPort.trim()), timeout, setPassword);
            return Boolean.TRUE;
        } catch (JedisConnectionException e) {
            logger.error(" Initialization JedisConnectionException :", e);
        } catch (NumberFormatException e) {
            logger.error(" Initialization NumberFormatException :", e);
        } catch (Exception e) {
            logger.error(" Initialization Exception :", e);
        }
        return Boolean.FALSE;
    }

    /**
     * 选择哪个Redis库，默认选择序号为0的库，从0开始
     *
     * @param
     * @return boolean
     */
    public boolean selectRedisDB(int dbNum) {
        Jedis jedis = null;
        try {
            if (dbNum < 0)
                return Boolean.FALSE;
            jedis = this.getJedis();
            this.dbNum = dbNum;
            String statusCode = jedis.select(dbNum);
            if (statusCode.equalsIgnoreCase("ok"))
                return Boolean.TRUE;
            if (logger.isDebugEnabled())
                logger.debug(" selectRedisDB " + dbNum + " ");
        } catch (RedisException e) {
            logger.error(" selectRedisDB RedisException:", e);
        } finally {
            if (jedis != null) {
                try {
                    this.releaseJedis(jedis);
                } catch (RedisException e) {
                    logger.error(" selectRedisDB returnResource RedisException:", e);
                }
            }
        }
        return Boolean.FALSE;
    }

    static class SingletonHolder {
        static RedisClient instance = new RedisClient();
    }
    public static void main(String[] args){
        System.out.println("Test==== ");
    }
}
