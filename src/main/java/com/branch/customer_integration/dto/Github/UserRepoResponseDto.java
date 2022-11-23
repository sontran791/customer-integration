package com.branch.customer_integration.dto.Github;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRepoResponseDto {
    private String name;
    private UserResponseDto owner;
    private String htmlUrl;

}
