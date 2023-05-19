package br.com.akj.api.service.seguranca;

import static br.com.akj.api.errors.Error.USUARIO_NAO_ENCONTRADO;
import static java.util.Optional.ofNullable;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.akj.api.exception.AuthenticationErrorException;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AutenticacaoUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final MessageHelper messageHelper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.debug("Buscando usuário de login {} para autenticação", username);

        return ofNullable(usuarioRepository.findByLogin(username)).orElseThrow(
            () -> new AuthenticationErrorException(USUARIO_NAO_ENCONTRADO, messageHelper.get(USUARIO_NAO_ENCONTRADO)));
    }
}
