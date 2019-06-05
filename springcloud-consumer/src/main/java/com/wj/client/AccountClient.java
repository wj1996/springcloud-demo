package com.wj.client;

import com.wj.pojo.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *Feign测试
 * 通过Feign进行REST访问，定义这个Hystrix对应的fallback会有些麻烦，需要写一个实现类实现这个接口，重写方法，在方法中处理
 * 在FeignClient中指定对应的实现类
 */
@FeignClient(value = "service",fallback = FallBackAccountClient.class)  //服务名称
public interface AccountClient {

    /**
     * 首先这是一个接口，Feign会通过动态代理，帮我们生成实现类。这点跟mybatis的mapper很像
     * @param id
     * @return
     */

    @GetMapping("account/{id}")
    Account queryById(@PathVariable Integer id);
}
