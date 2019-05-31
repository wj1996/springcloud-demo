package com.wj.web;

import com.wj.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}")
    public Account getUserById(@PathVariable Integer id) {
        String url = "http://localhost:8081/account/" + id;
        Account account = restTemplate.getForObject(url,Account.class);
        return account;
    }

    /**
     * 使用Eureka的方式来获取服务
     */
    /*第一种*/
    @Autowired
    private DiscoveryClient discoveryClient;   //注意：是spring下面的
    @GetMapping("eureka/{id}")
    public Account getUserByIdFromEureka(@PathVariable Integer id) {
        //根据服务ID获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("service");
        //从实例中取出IP和端口
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/account/" + id;
        System.out.println(url);
        Account account = restTemplate.getForObject(url,Account.class);
        return account;
    }

}
