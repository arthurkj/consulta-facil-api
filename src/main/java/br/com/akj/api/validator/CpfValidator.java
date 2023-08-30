package br.com.akj.api.validator;

import static br.com.akj.api.errors.Error.CPF_INVALIDO;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.stereotype.Component;

import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.caelum.stella.validation.CPFValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CpfValidator {

    private final MessageHelper messageHelper;
    private final CPFValidator stellaCpfvalidator;

    public void validar(final String cpf) {
        if (isBlank(cpf) || isFalse(stellaCpfvalidator.invalidMessagesFor(cpf).isEmpty())) {
            log.debug("CPF inválido.");
            throw new BusinessErrorException(CPF_INVALIDO, messageHelper.get(CPF_INVALIDO));
        }
    }
}
