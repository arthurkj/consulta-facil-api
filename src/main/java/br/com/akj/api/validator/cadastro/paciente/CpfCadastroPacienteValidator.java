package br.com.akj.api.validator.cadastro.paciente;

import static br.com.akj.api.errors.Error.CPF_EXISTENTE_CADASTRO_USUARIO;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.PacienteRepository;
import br.com.akj.api.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class CpfCadastroPacienteValidator implements CadastroPacienteValidator {

    private final PacienteRepository pacienteRepository;
    private final MessageHelper messageHelper;
    private final CpfValidator cpfValidator;

    @Override
    public void validar(final CadastroPacienteRequest request) {
        log.debug("Validando CPF para o paciente de login {}", request.usuario().login());

        cpfValidator.validar(request.cpf());

        if (pacienteRepository.existsByCpf(request.cpf())) {
            throw new BusinessErrorException(CPF_EXISTENTE_CADASTRO_USUARIO,
                messageHelper.get(CPF_EXISTENTE_CADASTRO_USUARIO));
        }
    }
}
