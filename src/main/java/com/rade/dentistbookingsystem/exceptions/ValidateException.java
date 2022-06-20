package com.rade.dentistbookingsystem.exceptions;

import javax.xml.bind.ValidationException;

public class ValidateException extends ValidationException {
    public ValidateException(String message) {
        super(message);
    }
}
