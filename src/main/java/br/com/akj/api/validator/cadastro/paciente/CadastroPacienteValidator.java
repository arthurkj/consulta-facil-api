package br.com.akj.api.validator.cadastro.paciente;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;

public interface CadastroPacienteValidator {

    void validar(final CadastroPacienteRequest request);

}
