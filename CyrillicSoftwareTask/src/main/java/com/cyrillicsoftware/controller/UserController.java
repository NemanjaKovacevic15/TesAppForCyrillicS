package com.cyrillicsoftware.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyrillicsoftware.model.Account;
import com.cyrillicsoftware.model.Farm;
import com.cyrillicsoftware.model.User;
import com.cyrillicsoftware.service.AccountService;
import com.cyrillicsoftware.service.FarmService;
import com.cyrillicsoftware.service.UserService;
import com.cyrillicsoftware.converter.AccountToAccountDTO;
import com.cyrillicsoftware.converter.FarmToFarmDTO;
import com.cyrillicsoftware.converter.UserDtoToUser;
import com.cyrillicsoftware.converter.UserToUserDTO;
import com.cyrillicsoftware.dto.AccountDTO;
import com.cyrillicsoftware.dto.FarmDTO;
import com.cyrillicsoftware.dto.UserDTO;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserToUserDTO toDto;
    @Autowired
    private UserDtoToUser toUser;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountToAccountDTO accToDto;
    @Autowired
    private FarmService farmService;
    @Autowired
    private FarmToFarmDTO farmToDto;

    // Get all Users
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserDTO>> getAll() {

        List<User> users = userService.findAll();

        return new ResponseEntity<>(toDto.convert(users), HttpStatus.OK);
    }

    // Get one User
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getOne(@PathVariable Long id) {

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(user), HttpStatus.OK);
    }

    // Get authenticated User
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getLoggedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        User user = userService.findByName(name);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(user), HttpStatus.OK);
    }

    // Add User
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<UserDTO> add(@RequestBody UserDTO toSave) {

        User saved = userService.save(toUser.convert(toSave));

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }

    // Delete User
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<UserDTO> delete(@PathVariable Long id) {

        User deleted = userService.delete(id);

        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
    }

    // Edit User
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<UserDTO> edit(@PathVariable Long id, @RequestBody UserDTO toEdit) {

        if (!Objects.equals(id, toEdit.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User edited = userService.save(toUser.convert(toEdit));

        return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
    }
    //Find all accounts by User id

    @RequestMapping(value = "/{id}/accounts")
    ResponseEntity<List<AccountDTO>> findAccountByUserId(@PathVariable Long id) {

        List<Account> accounts = accountService.findByUserId(id);

        return new ResponseEntity<>(accToDto.convert(accounts), HttpStatus.OK);
    }

    //Find all Farms by User id
    @RequestMapping(value = "/{id}/farms")
    ResponseEntity<List<FarmDTO>> findFarmByUserId(@PathVariable Long id) {

        List<Farm> farms = new ArrayList<>();
        List<Account> accounts = accountService.findByUserId(id);

        for (Account account : accounts) {
            farms.addAll(farmService.findByAccountId(account.getId()));
        }

        return new ResponseEntity<>(farmToDto.convert(farms), HttpStatus.OK);
    }
}
