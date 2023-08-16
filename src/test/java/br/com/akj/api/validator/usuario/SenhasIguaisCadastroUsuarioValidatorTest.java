package br.com.akj.api.validator.usuario;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.validator.cadastro.usuario.SenhasIguaisCadastroUsuarioValidator;

@ExtendWith(MockitoExtension.class)
class SenhasIguaisCadastroUsuarioValidatorTest {

    @InjectMocks
    private SenhasIguaisCadastroUsuarioValidator validator;

    @Mock
    private MessageHelper messageHelper;

    @Test
    void validar_ok() {
        final String senha = random(10);
        final CadastroUsuarioRequest request = new CadastroUsuarioRequest(random(10), senha, senha, random(10));

        validator.validar(request);
    }

    @Test
    void validar_invalid_password_not_equals() {
        final String senha = random(10);
        final CadastroUsuarioRequest request = new CadastroUsuarioRequest(random(10), senha, senha + 1, random(10));

        assertThrows(BusinessErrorException.class, () -> validator.validar(request));
    }
}