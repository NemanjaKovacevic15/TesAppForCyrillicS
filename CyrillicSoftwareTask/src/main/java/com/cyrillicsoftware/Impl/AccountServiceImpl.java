package com.cyrillicsoftware.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyrillicsoftware.model.Account;
import com.cyrillicsoftware.repository.AccountRepository;
import com.cyrillicsoftware.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findOne(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account toSave) {
        return accountRepository.save(toSave);
    }

    @Override
    public Account delete(Long id) {
        Account toDelete = accountRepository.getOne(id);
        accountRepository.delete(toDelete);
        return toDelete;
    }

    @Override
    public List<Account> findByUserId(Long id) {
        return accountRepository.findByUserId(id);
    }

}
