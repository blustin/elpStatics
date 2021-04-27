

import network.cycan.cache.redis.inteface.RedisService;
import network.cycan.core.exception.RedisException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * ApplicationTests 类型: 测试类
 * </p>
 *
 * @author linjd
 * @since 2020/6/4 11:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= RedisTests.class)
@ComponentScan(basePackages = {"com.hyt.cache"})
public class RedisTests {
    @Autowired
    private RedisService redisService;
    @Test
    public void TestSetValue()  {
        try{
        redisService.setStrValue("ex_para_001","hello world", 60*60*1000);
        }catch (RedisException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void TestGetValue() {
        try{
            String strValue=redisService.getStrValue("ex_para_001");
            System.out.println(strValue);
        }catch (RedisException ex)
        {
            System.out.println(ex.getMessage());

        }


    }
}
