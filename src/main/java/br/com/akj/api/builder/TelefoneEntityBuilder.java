package br.com.akj.api.builder;

import br.com.akj.api.entity.TelefoneEntity;
import br.com.akj.api.entity.UsuarioEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelefoneEntityBuilder {

    public static TelefoneEntity buildTelefoneEntity(final String telefone, final UsuarioEntity usuario) {
        return TelefoneEntity.builder()
            .telefone(telefone)
            .usuario(usuario)
            .build();
    }
}
