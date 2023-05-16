package br.com.akj.api.exception;

import br.com.akj.api.errors.Error;

public class BusinessErrorException extends AbstractErrorException {

    public BusinessErrorException(final Error error, final String reason) {
        super(error.getHttpStatus(), reason, error.getCode());
    }
}
