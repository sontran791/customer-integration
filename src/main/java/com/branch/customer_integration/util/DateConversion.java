package com.branch.customer_integration.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.branch.customer_integration.constant.DateConstant.USER_DATETIME_FORMAT;

@Component
public class DateConversion {
    public String removeSeparatorT(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse(dateInput, formatter);
        return DateTimeFormatter.ofPattern(USER_DATETIME_FORMAT).format(localDateTime);

    }
}
