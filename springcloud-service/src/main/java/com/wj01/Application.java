package com.wj01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wj01.mapper")
@EnableDiscoveryClient
//@EnableEurekaClient  //不要用这个，用这个后只能使用Eureka，后期如果用别的注册中心，这里还要改，用上面那个
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
