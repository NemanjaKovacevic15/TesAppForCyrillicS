package com.cyrillicsoftware.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cyrillicsoftware.model.User;
import com.cyrillicsoftware.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {

        if (source == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(source.getId());
        dto.setName(source.getName());

        return dto;
    }

    public List<UserDTO> convert(List<User> source) {
        List<UserDTO> converted = new ArrayList<>();

        source.forEach((user) -> {
            converted.add(convert(user));
        });

        return converted;
    }

}
