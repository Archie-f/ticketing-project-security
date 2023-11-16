package com.cydeo.mapper;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper mapper) {

        this.modelMapper = mapper;
    }

    //convert DTO to Entity
    public User convertToEntity(UserDTO dto){
        return modelMapper.map(dto,User.class);
    }

    //convert Entity to DTO
    public UserDTO convertToDto(User entity){
        return modelMapper.map(entity,UserDTO.class);
    }

}
