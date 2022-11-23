package com.branch.customer_integration.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.branch.customer_integration.constant.DateConstant.GITHUB_DATETIME_FORMAT;

@Slf4j
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    @Override
    public LocalDateTime deserialize(JsonParser jsonParser,
                                     DeserializationContext deserializationContext) throws IOException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(GITHUB_DATETIME_FORMAT);
        String dateTimeString = jsonParser.getText();
        try {
            return LocalDateTime.parse(dateTimeString, inputFormatter);
        } catch (DateTimeException e) {
            log.warn("Unable to parse \"{}\" as LocalDateTime for object {}",
                    dateTimeString,
                    jsonParser.getCurrentValue().getClass().getName());
            return LocalDateTime.MIN;
        }
    }
}
