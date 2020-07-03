package com.cyrillicsoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyrillicsoftware.model.Account;
import com.cyrillicsoftware.service.AccountService;
import com.cyrillicsoftware.converter.AccountDtoToAccount;
import com.cyrillicsoftware.converter.AccountToAccountDTO;
import com.cyrillicsoftware.dto.AccountDTO;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountToAccountDTO toDto;
    @Autowired
    AccountDtoToAccount toAccount;

//Get All Accounts
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<AccountDTO>> getAll() {

        List<Account> accounts = accountService.findAll();

        return new ResponseEntity<>(toDto.convert(accounts), HttpStatus.OK);
    }
//Get One Account	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<AccountDTO> getOne(@PathVariable Long id) {

        Account account = accountService.findOne(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(account), HttpStatus.OK);
    }
//Add Account	

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<AccountDTO> add(@RequestBody AccountDTO toSave) {

        Account saved = accountService.save(toAccount.convert(toSave));

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }
//Delete Account

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<AccountDTO> delete(@PathVariable Long id) {

        Account deleted = accountService.delete(id);

        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
    }
//Edit Account

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<AccountDTO> edit(@PathVariable Long id, @RequestBody AccountDTO toEdit) {

        if (!Objects.equals(id, toEdit.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account edited = accountService.save(toAccount.convert(toEdit));

        return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
    }

}
