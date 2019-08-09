package study.confige;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import study.dto.UserInfo;

import java.util.Date;

@Component
@Aspect
public class AopTest {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AopTest.class);

    @Pointcut("execution(* study.service.UserService.findUserInfo(..))")
    public void findUserInfo(){}


    @Before("findUserInfo()")
    public void  befor(){
        logger.info("开始登陆时间："+new Date());
    }
    @AfterReturning( value="findUserInfo()",returning="UserInfo")
    public void  after(JoinPoint joinPoint,Object UserInfo){
        Object[] obj= joinPoint.getArgs();

        UserInfo user=(UserInfo)obj[0];
        if (UserInfo!=null)
        {
            logger.info(user.getName()+"登录成功");
        }else{
            logger.info("登录失败");
        }

        logger.info("结束登陆时间："+new Date());
    }
}

