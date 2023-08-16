package br.com.akj.api.dto.endereco;

import br.com.akj.api.enumeration.Uf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroEnderecoRequest(
    @NotBlank
    String logradouro,
    @NotBlank
    String numero,
    String complemento,
    @NotBlank
    String bairro,
    @NotBlank
    String cidade,
    @NotNull
    Uf uf,
    @NotNull
    String cep
) {

}
