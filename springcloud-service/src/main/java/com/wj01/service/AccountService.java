package com.wj01.service;

import com.wj01.mapper.AccountMapper;
import com.wj01.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account queryAccountById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
