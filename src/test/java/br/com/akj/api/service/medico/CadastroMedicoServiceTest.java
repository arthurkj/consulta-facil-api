package br.com.akj.api.service.medico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

import br.com.akj.api.dto.medico.CadastroMedicoRequest;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.service.email.CadastroEmailService;
import br.com.akj.api.service.endereco.CadastroEnderecoService;
import br.com.akj.api.service.login.CadastroUsuarioService;
import br.com.akj.api.service.telefone.CadastroTelefoneService;
import br.com.akj.api.validator.cadastro.medico.CadastroMedicoValidator;

@ExtendWith(MockitoExtension.class)
class CadastroMedicoServiceTest {

    @InjectMocks
    private CadastroMedicoService service;

    @Mock
    private CadastroUsuarioService cadastroUsuarioService;

    @Mock
    private List<CadastroMedicoValidator> cadastroMedicoValidatorList;

    @Mock
    private CadastroEnderecoService cadastroEnderecoService;

    @Mock
    private CadastroEmailService cadastroEmailService;

    @Mock
    private CadastroTelefoneService cadastroTelefoneService;

    @Captor
    private ArgumentCaptor<MedicoEntity> medicoCaptor;

    @Test
    void realizar_ok() {
        final CadastroMedicoRequest request = Fixture.make(CadastroMedicoRequest.class);

        final MedicoEntity medico = Fixture.make(MedicoEntity.class);

        when(cadastroUsuarioService.realizar(eq(request.usuario()), medicoCaptor.capture())).thenReturn(medico);

        final Long result = service.realizar(request);

        assertNotNull(result);
        assertEquals(medico.getId(), result);

        cadastroMedicoValidatorList.forEach(validator -> verify(validator).validar(request));
        verify(cadastroUsuarioService).realizar(request.usuario(), medicoCaptor.getValue());
        verify(cadastroEnderecoService).realizar(request.endereco(), medico);
        verify(cadastroEmailService).realizar(request.email(), medico);
        verify(cadastroTelefoneService).realizar(request.telefone(), medico);
    }
}