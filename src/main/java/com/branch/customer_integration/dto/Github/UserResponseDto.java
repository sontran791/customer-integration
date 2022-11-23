package com.branch.customer_integration.dto.Github;

import com.branch.customer_integration.configuration.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.branch.customer_integration.constant.DateConstant.GITHUB_DATETIME_FORMAT;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String login;
    private Integer id;
    private String nodeId;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private Boolean siteAdmin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private Boolean hireable;
    private String bio;
    private String twitterUsername;
    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GITHUB_DATETIME_FORMAT, timezone = "America/Chicago")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GITHUB_DATETIME_FORMAT, timezone = "America/Chicago")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

}
