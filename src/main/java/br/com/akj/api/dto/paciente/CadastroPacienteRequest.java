package br.com.akj.api.dto.paciente;

import br.com.akj.api.dto.endereco.CadastroEnderecoRequest;
import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPacienteRequest(
    @NotBlank
    String cpf,
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
