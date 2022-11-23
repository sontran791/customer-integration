package com.branch.customer_integration.exception;

import com.branch.customer_integration.dto.HttpResponse;
import com.branch.customer_integration.dto.UserDto;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.branch.customer_integration.constant.ErrorConstant.GITHUB_API_CURRENTLY_DOWN;
import static com.branch.customer_integration.constant.ErrorConstant.NO_USER_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ExceptionHandling {

    @ExceptionHandler(NoUserRepoFoundException.class)
    public ResponseEntity<HttpResponse> noUserRepoFoundException(NoUserRepoFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<HttpResponse> noUserFoundException(NoUserFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<HttpResponse> handleFeignStatusException(FeignException exception) {
        if (HttpStatus.NOT_FOUND.value() == exception.status()) {
            return createHttpResponse(HttpStatus.valueOf(exception.status()), NO_USER_FOUND);
        } else if (HttpStatus.INTERNAL_SERVER_ERROR.value() == exception.status()){
            return createHttpResponse(HttpStatus.valueOf(exception.status()), GITHUB_API_CURRENTLY_DOWN);
        }
        return createHttpResponse(HttpStatus.valueOf(exception.status()), exception.getMessage());
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message, new UserDto()), httpStatus);
    }
}
