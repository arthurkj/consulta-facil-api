package br.com.akj.api.builder;

import br.com.akj.api.dto.endereco.CadastroEnderecoRequest;
import br.com.akj.api.entity.EnderecoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnderecoEntityBuilder {

    public static EnderecoEntity buildEnderecoEntity(final CadastroEnderecoRequest request,
        final UsuarioEntity usuario) {
        return EnderecoEntity.builder()
            .logradouro(request.logradouro().toUpperCase())
            .bairro(request.bairro().toUpperCase())
            .numero(request.numero().toUpperCase())
            .complemento(request.complemento().toUpperCase())
            .cidade(request.cidade().toUpperCase())
            .uf(request.uf())
            .cep(request.cep())
            .usuario(usuario)
            .build();
    }
}
