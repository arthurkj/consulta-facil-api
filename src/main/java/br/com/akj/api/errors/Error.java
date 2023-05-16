package br.com.akj.api.errors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Error {

    ERRO_INTERNO("erro.interno", ErrorCode.CODE_0001.getCode(), INTERNAL_SERVER_ERROR);

    private final String messageKey;
    private final String code;
    private final HttpStatus httpStatus;
}
