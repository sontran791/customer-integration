package com.branch.customer_integration.controller;

import com.branch.customer_integration.dto.Github.UserRepoResponseDto;
import com.branch.customer_integration.dto.Github.UserResponseDto;
import com.branch.customer_integration.dto.HttpResponse;
import com.branch.customer_integration.openFeign.GithubUsersFeign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import static com.branch.customer_integration.constant.DateConstant.GITHUB_DATETIME_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {
    @MockBean
    private GithubUsersFeign githubUsersFeign;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllUserRepos() {
        // Given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GITHUB_DATETIME_FORMAT, Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse("2011-01-25T18:44:36Z", formatter);

        UserResponseDto userResponseDto = UserResponseDto
                .builder()
                .id(583231)
                .login("octocat")
                .name("The Octocat")
                .location("San Francisco")
                .email(null)
                .htmlUrl("https://github.com/octocat")
                .createdAt(localDateTime)
                .build();

        UserRepoResponseDto userRepoResponseDto = UserRepoResponseDto
                .builder()
                .name("boysenberry-repo-1")
                .htmlUrl("https://github.com/octocat/boysenberry-repo-1")
                .owner(userResponseDto)
                .build();
        String url = "http://localhost:" + port + "/user/getAllUserRepos/octocat}";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        // When
        when(githubUsersFeign.findTheSpecificUserInfo(any())).thenReturn(userResponseDto);
        when(githubUsersFeign.findAllReposeForTheSpecificUser(any())).thenReturn(new UserRepoResponseDto[]{userRepoResponseDto});
        ResponseEntity<HttpResponse> response = restTemplate.exchange(
          uriBuilder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                HttpResponse.class
        );

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).getUser());
        assertNotNull(response.getBody().getUser().getRepos());
        assertEquals("USER FOUND!", response.getBody().getMessage());
    }

}
