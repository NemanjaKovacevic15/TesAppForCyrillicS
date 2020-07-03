package com.cyrillicsoftware.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cyrillicsoftware.model.User;
import com.cyrillicsoftware.service.UserService;
import com.cyrillicsoftware.dto.UserDTO;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(UserDTO dto) {
        User user = null;

        //if ID is null, it's a new object to save
        if (dto.getId() == null) {
            user = new User();
            //else it's an object from the DB	
        } else {
            user = userService.findOne(dto.getId());
            if (user == null) {
                throw new IllegalArgumentException("Can not convert non-existant entity.");
            }
        }

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());

        return user;
    }

    public List<User> convert(List<UserDTO> source) {
        List<User> converted = new ArrayList<>();

        source.forEach((dto) -> {
            converted.add(convert(dto));
        });

        return converted;
    }

}
