package br.com.akj.api.exception;

import br.com.akj.api.errors.Error;

public class AuthenticationErrorException extends AbstractErrorException {

    public AuthenticationErrorException(final Error error, final String reason) {
        super(error.getHttpStatus(), reason, error.getCode());
    }
}
