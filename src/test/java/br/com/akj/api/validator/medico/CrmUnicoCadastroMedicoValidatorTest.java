package br.com.akj.api.validator.medico;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.medico.CadastroMedicoRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.MedicoRepository;
import br.com.akj.api.validator.cadastro.medico.CrmUnicoCadastroMedicoValidator;

@ExtendWith(MockitoExtension.class)
class CrmUnicoCadastroMedicoValidatorTest {

    @InjectMocks
    private CrmUnicoCadastroMedicoValidator validator;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private MessageHelper messageHelper;

    @Test
    void validar_ok() {
        final CadastroMedicoRequest request = Fixture.make(CadastroMedicoRequest.class);

        when(medicoRepository.existsByCrm(request.crm())).thenReturn(false);

        validator.validar(request);

        verify(medicoRepository).existsByCrm(request.crm());
    }

    @Test
    void validar_invalid_crm_already_registered() {
        final CadastroMedicoRequest request = Fixture.make(CadastroMedicoRequest.class);

        when(medicoRepository.existsByCrm(request.crm())).thenReturn(true);

        assertThrows(BusinessErrorException.class, () -> validator.validar(request));

        verify(medicoRepository).existsByCrm(request.crm());
    }
}