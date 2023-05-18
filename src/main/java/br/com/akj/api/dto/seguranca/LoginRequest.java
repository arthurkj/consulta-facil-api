package br.com.akj.api.dto.seguranca;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Login é obrigatório")
    String login,
    @NotBlank(message = "Senha é obrigatória")
    String senha) {

}
