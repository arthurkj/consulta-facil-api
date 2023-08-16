package br.com.akj.api.builder;

import br.com.akj.api.entity.EmailEntity;
import br.com.akj.api.entity.UsuarioEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailEntityBuilder {

    public static EmailEntity buildEmailEntity(final String email, final UsuarioEntity usuario) {
        return EmailEntity.builder()
            .email(email)
            .usuario(usuario)
            .build();
    }
}
