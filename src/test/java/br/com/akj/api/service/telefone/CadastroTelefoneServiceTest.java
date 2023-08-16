package br.com.akj.api.service.telefone;

import static br.com.akj.api.builder.TelefoneEntityBuilder.buildTelefoneEntity;
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

import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.entity.TelefoneEntity;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.repository.TelefoneRepository;

@ExtendWith(MockitoExtension.class)
class CadastroTelefoneServiceTest {

    @InjectMocks
    private CadastroTelefoneService service;

    @Mock
    private TelefoneRepository telefoneRepository;

    @Test
    void realizar_ok() {
        final String telefone = random(10);
        final MedicoEntity usuario = Fixture.make(MedicoEntity.class);

        final TelefoneEntity telefoneCriado = buildTelefoneEntity(telefone, usuario);

        when(telefoneRepository.save(telefoneCriado)).thenReturn(telefoneCriado);

        final TelefoneEntity result = service.realizar(telefone, usuario);

        assertNotNull(result);
        assertEquals(telefoneCriado, result);

        verify(telefoneRepository).save(telefoneCriado);
    }
}