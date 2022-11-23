package com.branch.customer_integration.configuration;

import com.branch.customer_integration.dto.UserDto;
import com.branch.customer_integration.dto.UserRepoResponseDto;
import com.branch.customer_integration.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(UserResponseDto.class, UserDto.class)
                .addMapping(UserResponseDto::getLogin, UserDto::setUserName)
                .addMapping(UserResponseDto::getName, UserDto::setDisplayName)
                .addMapping(UserResponseDto::getLocation, UserDto::setGeoLocation)
                .addMapping(UserResponseDto::getEmail, UserDto::setEmail)
                .addMapping(UserResponseDto::getHtmlUrl, UserDto::setUrl)
                .addMapping(UserResponseDto::getCreatedAt, UserDto::setCreatedAt);

        return modelMapper;
    }
}
