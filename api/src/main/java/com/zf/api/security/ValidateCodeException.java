package com.zf.api.security;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
