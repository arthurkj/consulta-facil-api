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
    CODE_0004("CONSULTAFACIL-0004"),
    CODE_0005("CONSULTAFACIL-0005"),
    CODE_0006("CONSULTAFACIL-0006"),
    CODE_0007("CONSULTAFACIL-0007"),
    CODE_0008("CONSULTAFACIL-0008"),
    CODE_0009("CONSULTAFACIL-0009"),
    CODE_0010("CONSULTAFACIL-00010"),
    CODE_0011("CONSULTAFACIL-00011"),
    CODE_0012("CONSULTAFACIL-00012");

    private final String code;
}
