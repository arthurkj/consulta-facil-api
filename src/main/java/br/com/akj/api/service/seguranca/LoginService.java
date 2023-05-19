package br.com.akj.api.service.seguranca;

import static br.com.akj.api.errors.Error.CREDENCIAIS_INVALIDAS;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.akj.api.dto.seguranca.LoginRequest;
import br.com.akj.api.dto.seguranca.LoginResponse;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.exception.AuthenticationErrorException;
import br.com.akj.api.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenAutenticacaoService tokenAutenticacaoService;
    private final MessageHelper messageHelper;

    public LoginResponse realizar(final LoginRequest request) {
        log.info("Realizando login do usuário {}", request.login());
        final Authentication authentication;

        try {
            authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.login(), request.senha()));

        } catch (final BadCredentialsException | InternalAuthenticationServiceException exception) {
            log.debug("Erro ao realizar login: {}", exception.getMessage());

            throw new AuthenticationErrorException(CREDENCIAIS_INVALIDAS, messageHelper.get(CREDENCIAIS_INVALIDAS));
        }

        final String tokenJWT = tokenAutenticacaoService.gerarToken((UsuarioEntity) authentication.getPrincipal());

        return new LoginResponse(tokenJWT);
    }
}
