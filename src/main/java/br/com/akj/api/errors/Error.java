package br.com.akj.api.errors;

import static br.com.akj.api.errors.ErrorCode.CODE_0001;
import static br.com.akj.api.errors.ErrorCode.CODE_0002;
import static br.com.akj.api.errors.ErrorCode.CODE_0003;
import static br.com.akj.api.errors.ErrorCode.CODE_0004;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Error {

    ERRO_INTERNO("erro.interno", CODE_0001.getCode(), INTERNAL_SERVER_ERROR),
    ERRO_AO_GERAR_TOKEN_AUTENTICACAO("erro.gerar.token.autenticacao", CODE_0002.getCode(), INTERNAL_SERVER_ERROR),
    TOKEN_INVALIDO("token.invalido", CODE_0003.getCode(), UNAUTHORIZED),
    CREDENCIAIS_INVALIDAS("credenciais.invalidas", CODE_0004.getCode(), UNAUTHORIZED);

    private final String messageKey;
    private final String code;
    private final HttpStatus httpStatus;
}
