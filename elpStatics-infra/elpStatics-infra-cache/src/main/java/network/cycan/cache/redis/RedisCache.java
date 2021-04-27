package network.cycan.cache.redis;

import network.cycan.cache.redis.inteface.RedisService;
import network.cycan.core.exception.RedisException;
import network.cycan.core.util.ByteEncode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RedisCache 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/27 16:58
 */
@Service("redisService")
public class RedisCache implements RedisService {
    private Logger logger = LoggerFactory.getLogger(RedisCache.class);
    /** 消息推送*/
    @Override
    public void publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            jedis.publish(channel, message);
            if (logger.isDebugEnabled()) {
                logger.debug(" publish for channel :" + channel + " message " + message);
            }
        } catch (RedisException e) {
            logger.error(" publish RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
    }

    /**
     * 消息订阅
     */
    @Override
    public void subScribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            jedis.subscribe(jedisPubSub, channels);
            if (logger.isDebugEnabled()) {
                logger.debug(" subScribe for channel :" + channels);
            }
        } catch (RedisException e) {
            logger.error(" subScribe RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
    }

    @Override
    public boolean setStrValue(String key, String value) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return Boolean.FALSE;
            }
            jedis = RedisClient.getInstance().getJedis();
            String flag = jedis.set(key, value);
            if ("OK".equalsIgnoreCase(flag)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key);
                }
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue Exception:", e);
            throw new RedisException("  setStrValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean setStrValue(String key, String value, int seconds) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return Boolean.FALSE;
            }
            jedis = RedisClient.getInstance().getJedis();
            String flag = jedis.setex(key, seconds, value);
            if ("OK".equalsIgnoreCase(flag)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key);
                }
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue Exception:", e);
            throw new RedisException("  setStrValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean hmset(String key, Map<String, String> value, Long seconds) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return Boolean.FALSE;
            }
            jedis = RedisClient.getInstance().getJedis();
            String flag = jedis.hmset(key,value);
            if ("OK".equalsIgnoreCase(flag)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key);
                }
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue Exception:", e);
            throw new RedisException("  setStrValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public Map<String, String> hgetall(String key) throws RedisException {
        Map<String,String> result = null;
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return  result;
            }
            jedis = RedisClient.getInstance().getJedis();
            result = jedis.hgetAll(key);
            if (result != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" hgetall successful,key is : " + key);
                }
                return  result;
            }
        } catch (RedisException e) {
            logger.error(" hgetall Exception:", e);
            throw new RedisException("  hgetall Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return  result;
    }

    @Override
    public boolean hset(String key, String field, String value) throws RedisException{
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return Boolean.FALSE;
            }
            jedis = RedisClient.getInstance().getJedis();
            Long flag = jedis.hset(key, field, value);
            if (flag == 1L || flag == 0L) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key);
                }
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue Exception:", e);
            throw new RedisException("  setStrValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public String hget(String key, String field) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return null;
            }
            jedis = RedisClient.getInstance().getJedis();
            String value = jedis.hget(key, field);
            if (StringUtils.isNotBlank(value)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key);
                }
                return value;
            }
        } catch (RedisException e) {
            logger.error(" setValue Exception:", e);
            throw new RedisException("  setStrValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return null;
    }

    @Override
    public String getStrValue(String key) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return null;
            }
            jedis = RedisClient.getInstance().getJedis();
            return jedis.get(key);
        } catch (RedisException e) {
            logger.error(" getStrValue Exception:", e);
            throw new RedisException("  getStrValue Exception", e);
        } finally {
            this.releaseJedis(jedis); // 释放成功后置为null，防止重复使用、释放
        }
    }

    @Override
    public long getRemainingTimeByStrKey(String key) {
        long seconds = 0;
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            if (StringUtils.isNotBlank(key) && jedis.exists(key)) {
                seconds = jedis.ttl(key);
            }
        } catch (RedisException e) {
            logger.error(" getRemainingTimeByKey exception for key:" + key, e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return seconds;
    }

    @Override
    public long getRemainingTimeByKey(String key) {
        long seconds = 0;
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            byte[] byteKey = ByteEncode.encode(key);
            if (StringUtils.isNotBlank(key) && jedis.exists(byteKey)) {
                seconds = jedis.ttl(byteKey);
            }
        } catch (RedisException e) {
            logger.error(" getRemainingTimeByKey exception for key:" + key, e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return seconds;
    }

    @Override
    public boolean setStrKeyExpireTime(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            if (StringUtils.isNotBlank(key) && seconds > 0) {
                jedis.expire(key, seconds);
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setKeyExpireTime RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean setStrKeyExpireAtTime(String key, long unixTime) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            if (StringUtils.isNotBlank(key) && unixTime > 0) {
                jedis.expireAt(key, unixTime);
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setStrKeyExpireAtTime RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public Long removeStr(String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            return jedis.del(keys);
        } catch (RedisException e) {
            logger.error(" removeStr RedisException", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return null;
    }

    /**
     * 通过key得到对象
     *
     * @return <T extends Serializable> T
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getValue(String key) throws RedisException {
        Serializable decode ;
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return null;
            }
            jedis = RedisClient.getInstance().getJedis();
            byte[] byteKey = ByteEncode.encode(key);
            if (jedis.exists(byteKey)) {
                decode = ByteEncode.decode(jedis.get(byteKey));
            } else {
                return null;
            }
        } catch (RedisException e) {
            logger.error(" getValue exception for key:" + key, e);
            throw new RedisException("  getValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return (T) decode;
    }

    @Override
    public <T extends Serializable> boolean setValue(String key, T value) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return Boolean.FALSE;
            }
            jedis = RedisClient.getInstance().getJedis();
            byte[] byteKey = ByteEncode.encode(key);
            byte[] byteValue = ByteEncode.encode(value);
            String flag = jedis.set(byteKey, byteValue);
            if ("OK".equalsIgnoreCase(flag)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key);
                }
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue exception for key:" + key, e);
            throw new RedisException(" setValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    /**
     * 将对象存入redis，有一个过期时间seconds,成功返回true，失败返回false
     *
     * @return boolean
     */
    @Override
    public <T extends Serializable> boolean setValue(String key, T value, int seconds) throws RedisException {
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return Boolean.FALSE;
            }
            jedis = RedisClient.getInstance().getJedis();
            byte[] byteKey = ByteEncode.encode(key);
            byte[] byteValue = ByteEncode.encode(value);
            String flag = jedis.set(byteKey, byteValue);
            jedis.expire(byteKey, seconds);
            if ("OK".equalsIgnoreCase(flag)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" setValue successful,key is : " + key + " and timeout is " + seconds);
                }
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue exception for key:" + key, e);
            throw new RedisException("  setValue Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean exists(String key) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            byte[] byteKey = ByteEncode.encode(key);
            if (jedis.exists(byteKey)) {
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" exists exception for key:" + key, e);
            throw new RedisException(" exists Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean existsStrKey(String key) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            if (jedis.exists(key)) {
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" exists exception for key:" + key, e);
            throw new RedisException(" exists Exception", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public long remove(String key) {
        long number = 0;
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key)) {
                return number;
            }
            jedis = RedisClient.getInstance().getJedis();
            byte[] byteKey = ByteEncode.encode(key);
            number = jedis.del(byteKey);
        } catch (RedisException e) {
            logger.error(" remove exception for key:" + key, e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return number;
    }

    /**
     * <p>
     * 原子递增,用于统计
     * </p>
     * <p>
     * 方法调用一次加一
     * </p>
     *
     * @param key
     *            递增的值的模块代号
     * @param field
     *            递增的值
     */
    @Override
    public Long increaseByKey(String key, String field) {
        Jedis jedis = null;
        Long count = 0L;
        try {
            jedis = RedisClient.getInstance().getJedis();
            // 原子递增
            count = jedis.hincrBy(key, field, 1);
            jedis.hset(key, field, String.valueOf(count));
        } catch (RedisException e) {
            logger.error(" increaseByKey RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return count;
    }

    /**
     * <p>
     * 统计减少,用于清零
     * </p>
     *
     * @param key
     *            递增的值的模块代号
     * @param field
     *            递增的值
     * @param value
     *            要减少的值
     */
    @Override
    public void delIncreaseByKey(String key, String field, Long value) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            Long count = jedis.hincrBy(key, field, value > 0 ? 0L - value : value);
            jedis.hset(key, field, String.valueOf(count));
        } catch (RedisException e) {
            logger.error(" delIncreaseByKey RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
    }

    /**
     * <p>
     * 统计初始化
     * </p>
     *
     * @param key
     *            递增的值的模块代号
     * @param field
     *            递增的值
     * @param value
     *            要初始化的值
     */
    @Override
    public void initIncreaseByKey(String key, String field, Long value) {
        this.resetIncreaseByKey(key, field);
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            Long count = jedis.hincrBy(key, field, value > 0 ? value : 0L - value);
            jedis.hset(key, field, String.valueOf(count));
        } catch (RedisException e) {
            logger.error(" delIncreaseByKey RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
    }

    /**
     * 清零
     *
     */
    @Override
    public void resetIncreaseByKey(String key, String field) {
        Long curV = this.getIncreaseByKey(key, field);
        this.delIncreaseByKey(key, field, curV);
    }

    /**
     * <p>
     * 得到统计数据
     * </p>
     *
     * @param key
     *            递增的值的模块代号
     * @param field
     *            递增的值
     */
    @Override
    public Long getIncreaseByKey(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            if (jedis.hexists(key, field)) {
                try {
                    return Long.valueOf(jedis.hget(key, field));
                } catch (NumberFormatException e) {
                    logger.error(" getIncreaseByKey NumberFormatException:", e);
                    return -1L;
                }
            }
        } catch (RedisException e) {
            logger.error(" getIncreaseByKey RedisException:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return 0L;
    }

    @Override
    public boolean setIfNotExists(String key, String value, Integer expireSecs){
        Jedis jedis = null;
        try {
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                return Boolean.FALSE;
            }
            if(expireSecs==null || expireSecs<=0){
                // 默认10小时超时
                expireSecs = 36000;
            }
            jedis = RedisClient.getInstance().getJedis();
            // NX不存在才设置，EX超时单位是秒
            long flag = jedis.setnx(key, value);
            if(flag>0){
                jedis.expire(key,expireSecs);
            }
            if ( flag>0 ) {
                return Boolean.TRUE;
            }
        } catch (RedisException e) {
            logger.error(" setValue Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean expire(String key, int expireSecs){
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            Long result = jedis.expire(key, expireSecs);
            return null!=result && 1==result.intValue();
        } catch (RedisException e) {
            logger.error(" setExpire Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return false;
    }

    @Override
    public boolean lpush(String queueName, int expireSecs, String... value){
        Jedis jedis = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            Long results = jedis.lpush(queueName, value);
            if(expireSecs>0){
                jedis.expire(queueName, expireSecs);
            }
            return results!=null && results>0;
        } catch (RedisException e) {
            logger.error(" setExpire Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return false;
    }

    @Override
    public String rpop(String queueName){
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            value = jedis.rpop(queueName);
        } catch (RedisException e) {
            logger.error(" setExpire Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return value;
    }

    @Override
    public List<String> lrange(String queueName, long start, long end){
        Jedis jedis = null;
        List<String> value = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            value = jedis.lrange(queueName, start, end);
        } catch (RedisException e) {
            logger.error(" setExpire Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return value;
    }

    @Override
    public List<String> getAll(String queueName){
        Jedis jedis = null;
        List<String> value = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            value = jedis.lrange(queueName, 0, -1);
        } catch (RedisException e) {
            logger.error(" setExpire Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return value;
    }

    @Override
    public String clear(String queueName){
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisClient.getInstance().getJedis();
            value = jedis.ltrim(queueName, 1, 0);
        } catch (RedisException e) {
            logger.error(" setExpire Exception:", e);
        } finally {
            // 释放成功后置为null，防止重复使用、释放
            this.releaseJedis(jedis);
        }
        return value;
    }

    /**释放Jedis链接至池*/
    private void releaseJedis(Jedis jedis){
        if (jedis != null) {
            try {
                RedisClient.getInstance().releaseJedis(jedis);
                // 释放成功后返回Null，防止重复使用、释放
            } catch (RedisException e) {
                logger.error("releaseJedis Exception:", e);
            }
        }
    }

}
