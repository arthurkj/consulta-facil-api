package br.com.akj.api.service.email;

import static br.com.akj.api.builder.EmailEntityBuilder.buildEmailEntity;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.entity.EmailEntity;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.repository.EmailRepository;

@ExtendWith(MockitoExtension.class)
class CadastroEmailServiceTest {

    @InjectMocks
    private CadastroEmailService service;

    @Mock
    private EmailRepository emailRepository;

    @Test
    void realizar_ok() {
        final String email = random(10);
        final MedicoEntity usuario = Fixture.make(MedicoEntity.class);

        final EmailEntity emailCriado = buildEmailEntity(email, usuario);

        when(emailRepository.save(emailCriado)).thenReturn(emailCriado);

        final EmailEntity result = service.realizar(email, usuario);

        assertNotNull(result);
        assertEquals(emailCriado, result);

        verify(emailRepository).save(emailCriado);
    }
}