package com.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker*/ //服务熔断配置

/**
 * 基本上所有的springcloud应用都需要有这个三个注解，springcloud提供了一新的注解，包含这个三个注解的功能
 *
 */
@SpringCloudApplication
public class Application {


    @Bean
    @LoadBalanced  //实现负载均衡的第二种方式 （利用LoadBalancerInterceptor拦截器实现，拦截所有的Rest的请求）
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
