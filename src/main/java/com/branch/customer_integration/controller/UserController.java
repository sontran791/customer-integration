package com.branch.customer_integration.controller;

import com.branch.customer_integration.dto.UserDto;
import com.branch.customer_integration.dto.Github.UserRepoResponseDto;
import com.branch.customer_integration.dto.Github.UserResponseDto;
import com.branch.customer_integration.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class TestController {
    private final UsersService usersService;

    @GetMapping("/user/get/{username}")
    public UserResponseDto getUserInfo(@PathVariable String username) {
        return usersService.getTheSpecificUser(username);
    }

    @GetMapping("/user/get/repos/{username}")
    public UserRepoResponseDto[] getUserRepos(@PathVariable String username) {
        return usersService.getAllReposForTheSpecificUser(username);
    }

    @GetMapping("/user/getAllUserRepos/{username}")
    public UserDto getAllUserRepos(@PathVariable String username) {
        return usersService.getAllUserInfo(username);
    }
}
