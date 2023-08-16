package br.com.akj.api.validator.cadastro.usuario;

import static br.com.akj.api.errors.Error.LOGIN_EXISTENTE_CADASTRO_USUARIO;

import org.springframework.stereotype.Component;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginUnicoCadastroUsuarioValidator implements CadastroUsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final MessageHelper messageHelper;

    public void validar(final CadastroUsuarioRequest request) {
        log.debug("Validando se login {} já está cadastrado", request.login());

        if (usuarioRepository.existsByLogin(request.login())) {
            throw new BusinessErrorException(LOGIN_EXISTENTE_CADASTRO_USUARIO,
                messageHelper.get(LOGIN_EXISTENTE_CADASTRO_USUARIO));
        }
    }
}
