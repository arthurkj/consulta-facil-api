package br.com.akj.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record CadastroUsuarioRequest(
    @NotBlank
    String login,
    @NotBlank
    String senha,
    @NotBlank
    String confirmacaoSenha,
    @NotBlank
    String nome
) {

}
