package com.branch.customer_integration.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class UserDto {
    private String userName;
    private String displayName;
    private String avatar;
    private String geoLocation;
    private String email;
    private String url;
    private String createdAt;
    private RepoDto[] repos;
}
