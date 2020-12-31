package com.talents.orange.demo.mapper;

import com.talents.orange.demo.dto.request.UserDTO;
import com.talents.orange.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "dataNascimento", source = "dataNascimento" , dateFormat = "dd-MM-yyyy")
    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
