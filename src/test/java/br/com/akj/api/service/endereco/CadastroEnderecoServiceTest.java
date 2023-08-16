package br.com.akj.api.service.endereco;

import static br.com.akj.api.builder.EnderecoEntityBuilder.buildEnderecoEntity;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.endereco.CadastroEnderecoRequest;
import br.com.akj.api.entity.EnderecoEntity;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.repository.EnderecoRepository;

@ExtendWith(MockitoExtension.class)
class CadastroEnderecoServiceTest {

    @InjectMocks
    private CadastroEnderecoService service;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    void realizar_ok() {
        final CadastroEnderecoRequest endereco = Fixture.make(CadastroEnderecoRequest.class);
        final MedicoEntity usuario = Fixture.make(MedicoEntity.class);

        final EnderecoEntity enderecoCriado = buildEnderecoEntity(endereco, usuario);

        when(enderecoRepository.save(enderecoCriado)).thenReturn(enderecoCriado);

        final EnderecoEntity result = service.realizar(endereco, usuario);

        assertNotNull(result);
        assertEquals(enderecoCriado, result);

        verify(enderecoRepository).save(enderecoCriado);
    }
}