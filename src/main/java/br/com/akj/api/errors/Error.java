package br.com.akj.api.errors;

import static br.com.akj.api.errors.ErrorCode.CODE_0001;
import static br.com.akj.api.errors.ErrorCode.CODE_0002;
import static br.com.akj.api.errors.ErrorCode.CODE_0003;
import static br.com.akj.api.errors.ErrorCode.CODE_0004;
import static br.com.akj.api.errors.ErrorCode.CODE_0005;
import static br.com.akj.api.errors.ErrorCode.CODE_0006;
import static br.com.akj.api.errors.ErrorCode.CODE_0007;
import static br.com.akj.api.errors.ErrorCode.CODE_0008;
import static br.com.akj.api.errors.ErrorCode.CODE_0009;
import static br.com.akj.api.errors.ErrorCode.CODE_0010;
import static br.com.akj.api.errors.ErrorCode.CODE_0011;
import static br.com.akj.api.errors.ErrorCode.CODE_0012;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
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
    CREDENCIAIS_INVALIDAS("credenciais.invalidas", CODE_0004.getCode(), UNAUTHORIZED),
    AUTENTICACAO_OBRIGATORIA("autenticacao.obrigatoria", CODE_0005.getCode(), UNAUTHORIZED),
    USUARIO_SEM_PERMISSAO("usuario.sem.permissao", CODE_0006.getCode(), FORBIDDEN),
    RECURSO_NAO_ENCONTRADO("recurso.nao.encontrado", CODE_0007.getCode(), NOT_FOUND),
    USUARIO_NAO_ENCONTRADO("usuario.nao.encontrado", CODE_0008.getCode(), NOT_FOUND),
    PARAMETROS_INVALIDOS("parametros.invalidos", CODE_0009.getCode(), BAD_REQUEST),
    CRM_EXISTENTE_CADASTRO_MEDICO("crm.existente.cadastro.medico", CODE_0010.getCode(), BAD_REQUEST),
    LOGIN_EXISTENTE_CADASTRO_USUARIO("login.existente.cadastro.usuario", CODE_0011.getCode(), BAD_REQUEST),
    SENHA_DIFERENTES_CADASTRO_USUARIO("senhas.diferentes.cadastro.usuario", CODE_0012.getCode(), BAD_REQUEST);

    private final String messageKey;
    private final String code;
    private final HttpStatus httpStatus;
}
