package com.branch.customer_integration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class HttpResponse {
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm-dd-yyyy hh:mm:ss", timezone = "America/Chicago")
    private Date timeStamp;
    private UserDto user;

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message, UserDto user) {
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
        this.timeStamp = new Date();
        this.user = user;
    }
}
