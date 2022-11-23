package com.branch.customer_integration.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRepoResponseDto {
//    private Integer id;
//    private String nodeId;
    private String name;
//    private String fullName;
    private UserResponseDto owner;
//    private Boolean private;
    private String htmlUrl;
//    private String description;
//    private Boolean fork;
//    private String url;
//    private String archiveUrl;
//    private String assigneesUrl;
//    private String blobsUrl;
//    private String branchesUrl;
//    private String collaboratorsUrl;
//    private String commentsUrl;
//    private String commitsUrl;
//    private String compareUrl;
//    private String contentsUrl;
//    private String contributorsUrl;
//    private String deploymentsUrl;
//    private String downloadsUrl;
//    private String eventsUrl;
//    private String forksUrl;
//    private String gitCommitsUrl;
//    private String gitRefsUrl;
//    private String gitTagsUrl;
//    private String gitUrl;
//    private String issueCommentUrl;
//    private String issueEventsUrl;
//    private String issuesUrl;
//    private String keysUrl;
//    private String labelsUrl;
//    private String languagesUrl;
//    private String mergesUrl;
//    private String milestonesUrl;
//    private String notificationsUrl;
//    private String pullsUrl;
//    private String releasesUrl;
//    private String sshUrl;
//    private String stargazersUrl;
//    private String statusesUrl;
}
