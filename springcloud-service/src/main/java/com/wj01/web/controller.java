package com.wj01.web;

import com.wj01.pojo.Account;
import com.wj01.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class controller {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account queryUserById(@PathVariable Integer id) {
        System.out.println(accountService.queryAccountById(id));
        return accountService.queryAccountById(id);
    }
}
