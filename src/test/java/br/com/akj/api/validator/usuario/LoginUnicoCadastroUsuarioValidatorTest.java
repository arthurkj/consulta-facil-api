package br.com.akj.api.validator.usuario;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.UsuarioRepository;
import br.com.akj.api.validator.cadastro.usuario.LoginUnicoCadastroUsuarioValidator;

@ExtendWith(MockitoExtension.class)
class LoginUnicoCadastroUsuarioValidatorTest {

    @InjectMocks
    private LoginUnicoCadastroUsuarioValidator validator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MessageHelper messageHelper;

    @Test
    void validar_ok() {
        final CadastroUsuarioRequest request = Fixture.make(CadastroUsuarioRequest.class);

        when(usuarioRepository.existsByLogin(request.login())).thenReturn(false);

        validator.validar(request);

        verify(usuarioRepository).existsByLogin(request.login());
    }

    @Test
    void validar_invalid_login_already_registered() {
        final String senha = randomNumeric(10);
        final CadastroUsuarioRequest request = new CadastroUsuarioRequest(random(10), senha, senha, random(10));

        when(usuarioRepository.existsByLogin(request.login())).thenReturn(true);

        assertThrows(BusinessErrorException.class, () -> validator.validar(request));

        verify(usuarioRepository).existsByLogin(request.login());
    }
}