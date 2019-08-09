package study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import study.util.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtils;

    @Test
    public void test5(){
        redisUtils.set("123", "hello world");
        String string= redisUtils.get("123").toString();
        System.out.println(string);
    }
}
