package br.com.akj.api.errors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum ErrorCode {

    CODE_0001("CONSULTAFACIL-0001"),
    CODE_0002("CONSULTAFACIL-0002"),
    CODE_0003("CONSULTAFACIL-0003"),
    CODE_0004("CONSULTAFACIL-0004");

    private final String code;
}
