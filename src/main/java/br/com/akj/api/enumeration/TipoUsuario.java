package br.com.akj.api.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TipoUsuario {

    ADMIN("ROLE_ADMIN"),
    ASSISTENTE("ROLE_ASSISTENTE"),
    MEDICO("ROLE_MEDICO"),
    PACIENTE("ROLE_PACIENTE");

    private final String role;
}
