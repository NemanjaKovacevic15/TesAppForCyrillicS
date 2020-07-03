package com.cyrillicsoftware.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cyrillicsoftware.model.Account;
import com.cyrillicsoftware.dto.AccountDTO;

@Component
public class AccountToAccountDTO implements Converter<Account, AccountDTO> {

    @Override
    public AccountDTO convert(Account source) {

        if (source == null) {
            return null;
        }

        AccountDTO dto = new AccountDTO();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setCustomerId(source.getCustomer().getId());
        dto.setCustomerName(source.getCustomer().getName());
        dto.setUserId(source.getUser().getId());
        dto.setUserName(source.getUser().getName());

        return dto;
    }

    public List<AccountDTO> convert(List<Account> source) {
        List<AccountDTO> converted = new ArrayList<>();

        source.forEach((account) -> {
            converted.add(convert(account));
        });

        return converted;
    }
}
