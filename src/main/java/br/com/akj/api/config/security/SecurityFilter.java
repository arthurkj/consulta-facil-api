package br.com.akj.api.config.security;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.akj.api.service.seguranca.AutenticacaoUsuarioService;
import br.com.akj.api.service.seguranca.TokenAutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    private final TokenAutenticacaoService tokenAutenticacaoService;
    private final AutenticacaoUsuarioService autenticacaoUsuarioService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
        final FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            final String login = tokenAutenticacaoService.extrairUsuario(tokenJWT);
            final UserDetails usuario = autenticacaoUsuarioService.loadUserByUsername(login);

            final Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(final HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader != null) {
            return authorizationHeader.replace(AUTHORIZATION_HEADER_PREFIX, EMPTY);
        }

        return null;
    }
}
