package br.com.akj.api.dto.medico;

import br.com.akj.api.dto.endereco.CadastroEnderecoRequest;
import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.enumeration.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroMedicoRequest(
    @NotBlank
    String crm,
    @NotNull
    Especialidade especialidade,
    @NotNull
    CadastroUsuarioRequest usuario,
    @NotNull
    CadastroEnderecoRequest endereco,
    @NotBlank
    String email,
    @NotBlank
    String telefone
) {

}
