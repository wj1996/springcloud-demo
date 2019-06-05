package com.wj.client;

import com.wj.pojo.Account;
import org.springframework.stereotype.Component;

//注意：此处要将这个类交给spring管理
@Component
public class FallBackAccountClient implements AccountClient {

    @Override
    public Account queryById(Integer id) {
        Account account = new Account();
        account.setName("不存在");
        return account;
    }
}
