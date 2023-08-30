package br.com.akj.api.validator.cadastro.paciente;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.PacienteRepository;
import br.com.akj.api.validator.CpfValidator;

@ExtendWith(MockitoExtension.class)
class CpfCadastroPacienteValidatorTest {

    @InjectMocks
    private CpfCadastroPacienteValidator validator;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MessageHelper messageHelper;

    @Mock
    private CpfValidator cpfValidator;

    @Test
    void validar_ok() {
        final CadastroPacienteRequest request = Fixture.make(CadastroPacienteRequest.class);

        when(pacienteRepository.existsByCpf(request.cpf())).thenReturn(false);

        validator.validar(request);

        verify(cpfValidator).validar(request.cpf());
        verify(pacienteRepository).existsByCpf(request.cpf());
    }

    @Test
    void validar_cpf_existente() {
        final CadastroPacienteRequest request = Fixture.make(CadastroPacienteRequest.class);

        when(pacienteRepository.existsByCpf(request.cpf())).thenReturn(true);

        assertThrows(BusinessErrorException.class, () -> validator.validar(request));

        verify(cpfValidator).validar(request.cpf());
        verify(pacienteRepository).existsByCpf(request.cpf());
    }
}