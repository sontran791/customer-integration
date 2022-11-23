package com.branch.customer_integration.util;

import org.springframework.stereotype.Component;

@Component
public class StringValidation {

    public Boolean checkIfInputStringIsValid(String input) {
        return input.isEmpty() && input.isBlank();
    }
}
