package com.wj.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wj.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallback")  //服务降级作用在类上的写法
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /*@GetMapping("{id}")
    public String getUserById(@PathVariable Integer id) {
        String url = "http://localhost:8081/account/" + id;
        String account = restTemplate.getForObject(url,String.class);
        return account;
    }*/


    /*负载均衡实现
    * 第一种方式实现（简单方式）
    * */
    /*@Autowired
    private RibbonLoadBalancerClient ribbonLoadBalancerClient;*/

    /*负载均衡实现，第二种方式*/

    /**
     * 使用Eureka的方式来获取服务
     */
    /*第一种*/
    @Autowired
    private DiscoveryClient discoveryClient;   //注意：是spring下面的
//    /*@GetMapping("eureka/{id}")
//    public String getUserByIdFromEureka(@PathVariable Integer id) {
//        *//*//*/根据服务ID获取实例
//        List<ServiceInstance> instances = discoveryClient.getInstances("service");
//        //从实例中取出IP和端口
//        //随机，轮询，hash，（ID hash，最少访问）
//        ServiceInstance serviceInstance = instances.get(0);*//*
//
//        *//*通过负载均衡的方式获取(默认轮询)，简单方式*//*
////        ServiceInstance serviceInstance = ribbonLoadBalancerClient.choose("service");
//        *//*通过负载均衡的方式获取(默认轮询)，简单方式*//*
//
////        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/account/" + id;
//
//        //负载均衡第二种方式实现
//        String url = "http://service/account/" + id;
//
//        System.out.println(url);
//        String account = restTemplate.getForObject(url,String.class);
//        return account;
//    }*/


    /**
     * 模拟失败
     * @param id
     * @return
     */
    @GetMapping("eureka2/{id}")
//    @HystrixCommand(fallbackMethod = "getUserByIdFromEureka2Fallback")  //如果作用在了类上的服务降级，此处可以不指定失败方法，只需要开启降级即可
//    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2500")})
    //设置超时时间
    @HystrixCommand/*(commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "4500")})*/
    public String getUserByIdFromEureka2(@PathVariable Integer id) {

        String url = "http://service/account/" + id;
        System.out.println(url);
        String account = restTemplate.getForObject(url,String.class);
        return account;
    }

    /**
     * 失败方法，注意：失败方法的返回值和参数列表要和原方法保持完全一致,如果每一个请求都要写一个失败的降级方法，。。。。
     * 提供了在类上的注解，所有当前类的请求都可以使用同一个
     * @param id
     * @return
     */
    public String getUserByIdFromEureka2Fallback(Integer id) {
        return "服务器热闹";
    }


    /**
     * 注意，如果作用在类上，不可能保证每个请求的方法参数都一致，这里不写（通用）
     * @return
     */
    public String defaultFallback() {
        return "服务器热闹";
    }







}
