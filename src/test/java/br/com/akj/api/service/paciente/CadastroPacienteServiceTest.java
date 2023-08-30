package br.com.akj.api.service.paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;
import br.com.akj.api.entity.PacienteEntity;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.service.email.CadastroEmailService;
import br.com.akj.api.service.endereco.CadastroEnderecoService;
import br.com.akj.api.service.login.CadastroUsuarioService;
import br.com.akj.api.service.telefone.CadastroTelefoneService;
import br.com.akj.api.validator.cadastro.paciente.CadastroPacienteValidator;

@ExtendWith(MockitoExtension.class)
class CadastroPacienteServiceTest {

    @InjectMocks
    private CadastroPacienteService service;

    @Mock
    private CadastroUsuarioService cadastroUsuarioService;

    @Mock
    private List<CadastroPacienteValidator> cadastroPacienteValidatorList;

    @Mock
    private CadastroEnderecoService cadastroEnderecoService;

    @Mock
    private CadastroEmailService cadastroEmailService;

    @Mock
    private CadastroTelefoneService cadastroTelefoneService;

    @Captor
    private ArgumentCaptor<PacienteEntity> pacienteCaptor;

    @Test
    void realizar_ok() {
        final CadastroPacienteRequest request = Fixture.make(CadastroPacienteRequest.class);

        final PacienteEntity paciente = Fixture.make(PacienteEntity.class);

        when(cadastroUsuarioService.realizar(eq(request.usuario()), pacienteCaptor.capture())).thenReturn(paciente);

        final Long result = service.realizar(request);

        assertNotNull(result);
        assertEquals(paciente.getId(), result);

        cadastroPacienteValidatorList.forEach(validator -> verify(validator).validar(request));
        verify(cadastroUsuarioService).realizar(request.usuario(), pacienteCaptor.getValue());
        verify(cadastroEnderecoService).realizar(request.endereco(), paciente);
        verify(cadastroEmailService).realizar(request.email(), paciente);
        verify(cadastroTelefoneService).realizar(request.telefone(), paciente);
    }
}