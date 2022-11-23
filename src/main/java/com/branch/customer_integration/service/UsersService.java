package com.branch.customer_integration.service;

import com.branch.customer_integration.dto.Github.UserRepoResponseDto;
import com.branch.customer_integration.dto.Github.UserResponseDto;
import com.branch.customer_integration.dto.RepoDto;
import com.branch.customer_integration.dto.UserDto;
import com.branch.customer_integration.openFeign.GithubUsersFeign;
import com.branch.customer_integration.util.DateConversion;
import com.branch.customer_integration.util.StringValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@AllArgsConstructor
public class UsersService {
    private GithubUsersFeign githubUsersFeign;
    private ModelMapper modelMapper;
    private StringValidation stringValidation;
    private DateConversion dateConversion;

    public UserDto getAllUserInfo(String username) {
        UserDto userDto = new UserDto();
        log.trace("Calling GithubUsersFeign.findTheSpecificUserInfo");
        UserResponseDto userResponseDto = githubUsersFeign.findTheSpecificUserInfo(username);

        if (userResponseDto != null) {
            log.debug("Found user info {}", userResponseDto);
            // Map UserResponse to our UserDTO
            userDto = modelMapper.map(userResponseDto, UserDto.class);
            // Remove the separator T in created date
            userDto.setCreatedAt(dateConversion.removeSeparatorT(userDto.getCreatedAt()));

            log.trace("Calling GithubUsersFeign.findAllReposeForTheSpecificUser");
            int loginId = userResponseDto.getId();
            UserRepoResponseDto[] repoList = githubUsersFeign.findAllReposeForTheSpecificUser(username);
            log.debug("Found user repo {}", (Object) repoList);
            RepoDto[] repos = Arrays.stream(repoList)
                    // double-check to make sure correct user login id for each repo
                    .filter(userRepoResponseDto -> userRepoResponseDto.getOwner().getId() == loginId)
                    // build repo arrays
                    .map(userRepoResponseDto -> RepoDto
                            .builder()
                            .name(Boolean.FALSE.equals(stringValidation.checkIfInputStringIsValid(userRepoResponseDto.getName())) ? userRepoResponseDto.getName() : null)
                            .url(Boolean.FALSE.equals(stringValidation.checkIfInputStringIsValid(userRepoResponseDto.getHtmlUrl())) ? userRepoResponseDto.getHtmlUrl() : null)
                            .build()).toArray(RepoDto[]::new);
            userDto.setRepos(repos);
        }

        return userDto;
    }
}
