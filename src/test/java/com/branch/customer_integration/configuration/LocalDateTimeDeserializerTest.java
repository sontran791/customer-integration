package com.branch.customer_integration.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalDateTimeDeserializerTest {
    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    @InjectMocks
    private LocalDateTimeDeserializer localDateTimeDeserializer;

    @Test
     void deserialize() throws IOException {
        // Given
        LocalDateTime expected = LocalDateTime.of(2019, 3, 16, 12, 6, 0);
        when(jsonParser.getText()).thenReturn("2019-03-16T12:06:00Z");

        // When
        LocalDateTime actual = localDateTimeDeserializer.deserialize(jsonParser, deserializationContext);

        // Then
        assertEquals(expected, actual);
    }

    @Test
     void deserializeBadArgument() throws IOException {
        // Given
        String value = "I am a string";
        when(jsonParser.getText()).thenReturn(value);
        when(jsonParser.getCurrentValue()).thenReturn(value);

        // When
        LocalDateTime actual = localDateTimeDeserializer.deserialize(jsonParser, deserializationContext);

        // Then
        assertEquals(LocalDateTime.MIN, actual);
    }
}
