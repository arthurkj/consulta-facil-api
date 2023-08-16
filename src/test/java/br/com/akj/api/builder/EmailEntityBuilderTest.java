package br.com.akj.api.builder;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.akj.api.entity.EmailEntity;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.fixture.Fixture;

class EmailEntityBuilderTest {

    @Test
    void buildEmailEntity_ok() {
        final String email = random(10);
        final UsuarioEntity usuario = Fixture.make(MedicoEntity.class);

        final EmailEntity result = EmailEntityBuilder.buildEmailEntity(email, usuario);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(usuario, result.getUsuario());
    }
}