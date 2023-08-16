package br.com.akj.api.builder;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.akj.api.dto.endereco.CadastroEnderecoRequest;
import br.com.akj.api.entity.EmailEntity;
import br.com.akj.api.entity.EnderecoEntity;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.fixture.Fixture;

class EnderecoEntityBuilderTest {

    @Test
    void buildEnderecoEntity() {
        final CadastroEnderecoRequest request = Fixture.make(CadastroEnderecoRequest.class);
        final UsuarioEntity usuario = Fixture.make(MedicoEntity.class);

        final EnderecoEntity result = EnderecoEntityBuilder.buildEnderecoEntity(request, usuario);

        assertNotNull(result);
        assertEquals(request.logradouro().toUpperCase(), result.getLogradouro());
        assertEquals(request.bairro().toUpperCase(), result.getBairro());
        assertEquals(request.numero().toUpperCase(), result.getNumero());
        assertEquals(request.complemento().toUpperCase(), result.getComplemento());
        assertEquals(request.cidade().toUpperCase(), result.getCidade());
        assertEquals(request.uf(), result.getUf());
        assertEquals(request.cep(), result.getCep());
        assertEquals(usuario, result.getUsuario());
    }
}