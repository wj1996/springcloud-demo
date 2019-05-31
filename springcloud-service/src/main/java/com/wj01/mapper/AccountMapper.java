package com.wj01.mapper;

import com.wj01.pojo.Account;
import tk.mybatis.mapper.common.Mapper;
public interface AccountMapper extends Mapper<Account> {

    public Account queryAccountById(Integer id);
}
