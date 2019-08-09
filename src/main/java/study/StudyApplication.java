package study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;


//@EnableDiscoveryClient
//@EnableCaching
//@EnableFeignClients
@SpringBootApplication(scanBasePackages = "study")
@MapperScan("study.mapper")
public class StudyApplication {

    public static void main(String[] args){

        SpringApplication.run(StudyApplication.class, args);

    }
}