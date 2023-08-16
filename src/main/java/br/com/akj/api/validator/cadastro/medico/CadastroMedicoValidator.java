package br.com.akj.api.validator.cadastro.medico;

import br.com.akj.api.dto.medico.CadastroMedicoRequest;

public interface CadastroMedicoValidator {

    void validar(final CadastroMedicoRequest request);
}
