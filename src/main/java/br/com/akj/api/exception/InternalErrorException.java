package br.com.akj.api.exception;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import br.com.akj.api.errors.Error;


public class InternalErrorException extends AbstractErrorException {

    public InternalErrorException(final Error errorCode, final String reason) {
        super(INTERNAL_SERVER_ERROR, reason, errorCode.getCode());
    }
}