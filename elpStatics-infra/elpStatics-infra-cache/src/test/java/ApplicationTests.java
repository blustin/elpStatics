


import network.cycan.core.util.GeneralProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest(classes= GeneralProperties.class)
public class ApplicationTests {
    @Test
    public void TestGeneralProperties() {

        String userName=GeneralProperties.getProperty("username","canding");
        System.out.println(userName);
        String userName1=GeneralProperties.getProperty("username1","canding");
        System.out.println(userName1);
    }
}
