package br.com.akj.api.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Especialidade {

    CARDIOLOGIA,
    DERMATOLOGIA,
    GINECOLOGIA,
    ORTOPEDIA
}
