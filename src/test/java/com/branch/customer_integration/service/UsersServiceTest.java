package com.branch.customer_integration.service;

import com.branch.customer_integration.dto.Github.UserRepoResponseDto;
import com.branch.customer_integration.dto.Github.UserResponseDto;
import com.branch.customer_integration.dto.RepoDto;
import com.branch.customer_integration.dto.UserDto;
import com.branch.customer_integration.openFeign.GithubUsersFeign;
import com.branch.customer_integration.util.DateConversion;
import com.branch.customer_integration.util.StringValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.branch.customer_integration.constant.DateConstant.GITHUB_DATETIME_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @Mock
    private GithubUsersFeign feign;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private StringValidation stringValidation;
    @Mock
    private DateConversion dateConversion;
    @InjectMocks
    UsersService usersService;
    private UserResponseDto userResponseDto;
    private UserRepoResponseDto userRepoResponseDto;
    private UserDto userDto;

    @BeforeEach
    void setup() {
        userDto = UserDto
                .builder()
                .userName("octocat")
                .displayName("The Octocat")
                .avatar("https://avatars.githubusercontent.com/u/583231?v=4")
                .geoLocation("San Francisco")
                .email("email")
                .url("https://github.com/octocat")
                .createdAt("2011-01-25 18:44:36")
                .repos(new RepoDto[]{})
                .build();
        when(modelMapper.map(any(), any())).thenReturn(userDto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GITHUB_DATETIME_FORMAT, Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse("2011-01-25T18:44:36Z", formatter);
        userResponseDto = UserResponseDto
                .builder()
                .id(583231)
                .login("octocat")
                .name("The Octocat")
                .location("San Francisco")
                .email("email")
                .htmlUrl("https://github.com/octocat")
                .createdAt(localDateTime)
                .build();

        userRepoResponseDto = UserRepoResponseDto
                .builder()
                .name("boysenberry-repo-1")
                .htmlUrl("https://github.com/octocat/boysenberry-repo-1")
                .owner(userResponseDto)
                .build();
    }

    @Test
    void getAllUserInfo() {
        // given
        when(feign.findTheSpecificUserInfo(any())).thenReturn(userResponseDto);
        when(feign.findAllReposeForTheSpecificUser(any())).thenReturn(new UserRepoResponseDto[]{userRepoResponseDto});

        // when
        UserDto result =  usersService.getAllUserInfo("octocat");

        // then
        assertNotNull(result);
        assertEquals(userDto.getUserName(), result.getUserName());
    }
}
