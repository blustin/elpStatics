package network.cycan.cache.redis.inteface;


import network.cycan.core.exception.RedisException;
import redis.clients.jedis.JedisPubSub;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RedisService 类型:
 * </p>
 *
 * @author linjd
 * @since 2020/6/4 17:29
 */
public interface RedisService {
    String NEWS = "JC_NEWS";

    /**
     * 推送消息
     *
     * @param channel
     * @param message
     */
    void publish(String channel, String message);

    /**
     * 订阅消息
     *
     * @param jedisPubSub
     * @param channels
     */
    void subScribe(JedisPubSub jedisPubSub, String... channels);

    /**
     * 将字符串永久性的存入redis,成功返回true，失败返回false 注意:key和value是string类型
     *
     * @param <T>
     * @param key
     * @param value
     * @return
     */
    boolean setStrValue(String key, String value) throws RedisException;

    /**
     * 注意:key和value是string类型，传入超时时间
     *
     * @param <T>
     * @param key
     * @param value
     * @return
     */
    boolean setStrValue(String key, String value, int seconds) throws RedisException;


    boolean hmset(String key, Map<String, String> value, Long seconds) throws RedisException;

    Map<String, String> hgetall(String key) throws RedisException;

    boolean hset(String key, String field, String value) throws RedisException;

    String hget(String key, String field) throws RedisException;

    /**
     * 取字符串
     *
     * @param key
     * @param value
     * @return
     */
    String getStrValue(String key) throws RedisException;

    /**
     * 给一个key设置过期时间,以秒来计算，比如10秒后key过期<br>
     * <p>
     * 只对调用 setStrValue方法有效
     *
     * @param value
     * @param seconds
     * @return boolean
     */
    boolean setStrKeyExpireTime(String key, int seconds);

    /**
     * 给一个key设置指定过期时间<br>
     * <p>
     * 只对调用 setStrValue方法有效
     *
     * @param key
     * @param seconds
     * @return boolean
     */
    boolean setStrKeyExpireAtTime(String key, long unixTime);

    /**
     * 如果key设置了过期时间，则可以获取key(字符串类型的)的存活时间
     *
     * @param key
     * @return
     */
    long getRemainingTimeByStrKey(String key);

    /**
     * 如果key设置了过期时间，则可以获取key的存活时间 每次set值的时候都会更ttl时间
     *
     * @return -1 没有设置过期时间或者已过期   -2不存在这个值
     */
    long getRemainingTimeByKey(String key);

    /**
     * 删除字符创方法值
     *
     * @param keys
     * @return Long
     */
    Long removeStr(String... keys);

    /**
     * 将对象永久性的存入redis,成功返回true，失败返回false 注意：必须是序列化的对象
     *
     * @param <T>
     * @param key
     * @param value
     * @return
     */
    <T extends Serializable> boolean setValue(String key, T value) throws RedisException;

    /**
     * 将对象存入redis，设置一个过期时间seconds,成功返回true，失败返回false 注意：必须是序列化的key和value
     *
     * @param <T>
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    <T extends Serializable> boolean setValue(String key, T value, int seconds) throws RedisException;

    /**
     * 通过key得到序列化后的对象 注意:key是String，和序列化的对象区分开来
     *
     * @param <T>
     * @param key
     * @return
     */
    <T extends Serializable> T getValue(String key) throws RedisException;

    /**
     * 判断key是否存在 存储的类型是对象字符串时适用
     *
     * @param key
     * @return boolean
     * @throws com.phoenix.redis.exception.RedisException
     */
    boolean exists(String key) throws RedisException;

    boolean existsStrKey(String key) throws RedisException;

    /**
     * 将一个key删除掉
     *
     * @param key
     * @return
     */
    long remove(String key) throws RedisException;

    /**
     * <p>
     * 原子递增的统计初始化
     * </p>
     * <p>
     * 只能调用一次，重复调用会导致数据异常
     * </p>
     *
     * @param key   递增的值的模块代号
     * @param field 递增的值
     * @param value 初始化的数字
     */
    void initIncreaseByKey(String key, String field, Long value);

    /**
     * 清零
     *
     * @param key   递增的值的模块代号
     * @param field 递增的值
     */
    void resetIncreaseByKey(String key, String field);

    /**
     * <p>
     * 原子递增,用于统计
     * </p>
     * <p>
     * 方法调用一次加一
     * </p>
     *
     * @param key   递增的值的模块代号
     * @param field 递增的值
     * @return Long 调用后当前的值
     */
    Long increaseByKey(String key, String field);

    /**
     * <p>
     * 统计减少
     * </p>
     *
     * @param key   递增的值的模块代号
     * @param field 递增的值
     * @param value 要减少的值
     */
    void delIncreaseByKey(String key, String field, Long value);

    /**
     * <p>
     * 得到统计数据
     * </p>
     *
     * @param key   递增的值的模块代号
     * @param field 递增的值
     */
    Long getIncreaseByKey(String key, String field);

    /**
     * 若key不存在则set, key与value都是String
     */
    boolean setIfNotExists(String key, String value, Integer expireSecs);

    /**
     * 设置过期时间：存在key并且设置成功则返回true
     */
    boolean expire(String key, int expireSecs);

    /**
     * 在list的左边添加元素，若list不存在则新建
     *
     * @param queueName  list名称
     * @param expireSecs 秒，失效时间，小于等于0则永不过期
     * @param value      元素值
     * @return
     */
    boolean lpush(String queueName, int expireSecs, String... value);

    /**
     * 在list的右边移出元素
     */
    String rpop(String queueName);

    /**
     * 取出list的某范围值，下标从0开始，-1是最后下标
     */
    List<String> lrange(String queueName, long start, long end);

    /**
     * 取出list的所有值
     */
    List<String> getAll(String queueName);

    /**
     * 清空list所有值
     */
    String clear(String queueName);
}
