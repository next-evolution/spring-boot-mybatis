package com.example.repository;

import com.example.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountRepository {

    List<Account> list();

    boolean exists(String emailAddress);

    Account get(String emailAddress);

    int insert(Account account);

    int update(Account account);

}
