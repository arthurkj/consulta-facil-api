package br.com.akj.api.validator.cadastro.medico;

import static br.com.akj.api.errors.Error.CRM_EXISTENTE_CADASTRO_MEDICO;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.akj.api.dto.medico.CadastroMedicoRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class CrmUnicoCadastroMedicoValidator implements CadastroMedicoValidator {

    private final MedicoRepository medicoRepository;
    private final MessageHelper messageHelper;

    public void validar(final CadastroMedicoRequest request) {
        log.info("Validando cadastro de médico de CRM {}", request.crm());

        if (medicoRepository.existsByCrm(request.crm())) {
            throw new BusinessErrorException(CRM_EXISTENTE_CADASTRO_MEDICO,
                messageHelper.get(CRM_EXISTENTE_CADASTRO_MEDICO));
        }
    }
}
