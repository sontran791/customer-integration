package com.branch.customer_integration.openFeign;

import com.branch.customer_integration.dto.Github.UserRepoResponseDto;
import com.branch.customer_integration.dto.Github.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GithubUsersFeign {
    @GetMapping("/users/{username}")
    UserResponseDto findTheSpecificUserInfo(@PathVariable String username);

    @GetMapping("/users/{username}/repos")
    UserRepoResponseDto[] findAllReposeForTheSpecificUser(@PathVariable String username);
}
