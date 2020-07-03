package com.cyrillicsoftware.service;

import java.util.List;

import com.cyrillicsoftware.model.Account;

public interface AccountService {

    Account findOne(Long id);

    List<Account> findAll();

    Account save(Account toSave);

    Account delete(Long id);

    List<Account> findByUserId(Long id);
}
