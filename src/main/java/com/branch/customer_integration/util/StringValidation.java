package com.branch.customer_integration.util;

public class InputValidation {

    private Boolean checkIfInputStringIsValid(String input) {
        return input.isEmpty() && input.isBlank();
    }
}
