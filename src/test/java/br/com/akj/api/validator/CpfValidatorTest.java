package br.com.akj.api.validator;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.caelum.stella.SimpleValidationMessage;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

@ExtendWith(MockitoExtension.class)
class CpfValidatorTest {

    @InjectMocks
    private CpfValidator validator;

    @Mock
    private MessageHelper messageHelper;

    @Mock
    private CPFValidator stellaCpfvalidator;

    @Test
    void valida_ok() {
        final String cpf = randomNumeric(11);

        when(stellaCpfvalidator.invalidMessagesFor(cpf)).thenReturn(emptyList());

        validator.validar(cpf);

        verify(stellaCpfvalidator).invalidMessagesFor(cpf);
    }

    @Test
    void valida_em_branco() {
        final String cpf = "";

        assertThrows(BusinessErrorException.class, () -> validator.validar(cpf));

        verifyNoInteractions(stellaCpfvalidator);
    }

    @Test
    void valida_cpf_invalido() {
        final String cpf = randomNumeric(11);

        final ValidationMessage validationMessage = new SimpleValidationMessage("");

        when(stellaCpfvalidator.invalidMessagesFor(cpf)).thenReturn(singletonList(validationMessage));

        assertThrows(BusinessErrorException.class, () -> validator.validar(cpf));

        verify(stellaCpfvalidator).invalidMessagesFor(cpf);
    }
}