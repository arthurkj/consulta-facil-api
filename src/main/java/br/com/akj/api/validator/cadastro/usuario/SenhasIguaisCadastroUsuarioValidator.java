package br.com.akj.api.validator.cadastro.usuario;

import static br.com.akj.api.errors.Error.SENHA_DIFERENTES_CADASTRO_USUARIO;
import static java.lang.Boolean.FALSE;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.exception.BusinessErrorException;
import br.com.akj.api.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(2)
public class SenhasIguaisCadastroUsuarioValidator implements CadastroUsuarioValidator {

    private final MessageHelper messageHelper;

    public void validar(final CadastroUsuarioRequest request) {
        log.debug("Validando senhas iguais para cadastro de usuário {}", request.login());

        if (FALSE.equals(request.senha().equals(request.confirmacaoSenha()))) {
            throw new BusinessErrorException(SENHA_DIFERENTES_CADASTRO_USUARIO,
                messageHelper.get(SENHA_DIFERENTES_CADASTRO_USUARIO));
        }
    }
}
