package br.com.akj.api.errors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum ErrorCode {

    CODE_0001("CONSULTAFACIL-0001");

    private final String code;
}
