package com.branch.customer_integration.controller;

import com.branch.customer_integration.dto.HttpResponse;
import com.branch.customer_integration.dto.UserDto;
import com.branch.customer_integration.exception.ExceptionHandling;
import com.branch.customer_integration.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping ("/user")
public class UserController extends ExceptionHandling {
    private UsersService usersService;

    @GetMapping("/getAllUserRepos/{username}")
    public ResponseEntity<HttpResponse> getAllUserRepos(@PathVariable String username) {
        if (username.isBlank()) {
            return response(HttpStatus.BAD_REQUEST, "Missing username parameter.", new UserDto());
        }
        
        return response(HttpStatus.OK, "User found!", usersService.getAllUserInfo(username));
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message, UserDto data) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toLowerCase(), message.toUpperCase(), data), httpStatus);
    }
}
