package com.wj01.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "my")
@Data
public class Account {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private Double money;


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
