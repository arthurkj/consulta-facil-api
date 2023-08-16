package br.com.akj.api.builder;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.akj.api.entity.TelefoneEntity;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.fixture.Fixture;

class TelefoneEntityBuilderTest {

    @Test
    void buildTelefoneEntity_ok() {
        final String telefone = random(10);
        final UsuarioEntity usuario = Fixture.make(MedicoEntity.class);

        final TelefoneEntity result = TelefoneEntityBuilder.buildTelefoneEntity(telefone, usuario);

        assertNotNull(result);
        assertEquals(telefone, result.getTelefone());
        assertEquals(usuario, result.getUsuario());
    }
}