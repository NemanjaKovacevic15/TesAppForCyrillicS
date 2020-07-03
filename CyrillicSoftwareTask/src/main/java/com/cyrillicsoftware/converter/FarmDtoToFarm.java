package com.cyrillicsoftware.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cyrillicsoftware.model.Account;
import com.cyrillicsoftware.model.Farm;
import com.cyrillicsoftware.service.AccountService;
import com.cyrillicsoftware.service.FarmService;
import com.cyrillicsoftware.dto.FarmDTO;

@Component
public class FarmDtoToFarm implements Converter<FarmDTO, Farm> {

    @Autowired
    private FarmService farmService;

    @Autowired
    private AccountService accountService;

    @Override
    public Farm convert(FarmDTO dto) {
        Farm farm = null;

        Account account = accountService.findOne(dto.getAccountId());

        if (account != null) {
            //if ID is null, it's a new object to save
            if (dto.getId() == null) {
                farm = new Farm();
                //else it's an object from the DB	
            } else {
                farm = farmService.findOne(dto.getId());
                if (farm == null) {
                    throw new IllegalArgumentException("Can not convert non-existant entity.");
                }
            }
            farm.setId(dto.getId());
            farm.setName(dto.getName());
            farm.setAccount(account);

            return farm;
        } else {
            throw new IllegalStateException("Trying to attach to a non-existant entity.");
        }
    }

    public List<Farm> convert(List<FarmDTO> source) {
        List<Farm> converted = new ArrayList<>();

        source.forEach((dto) -> {
            converted.add(convert(dto));
        });

        return converted;

    }

}
