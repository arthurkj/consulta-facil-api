package br.com.akj.api.service.seguranca;

import static br.com.akj.api.errors.Error.ERRO_AO_GERAR_TOKEN_AUTENTICACAO;
import static br.com.akj.api.errors.Error.TOKEN_INVALIDO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.exception.AuthenticationErrorException;
import br.com.akj.api.exception.InternalErrorException;
import br.com.akj.api.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenAutenticacaoService {

    private static final int HORAS_DURACAO_TOKEN = 15;
    private static final String OFFSET_FUSO_HORARIO = "-03:00";
    private static final String SECRET_KEY_JWT = "12345";
    private static final String ISSUER_JWT = "API ConsultaFacil";

    private final MessageHelper messageHelper;

    public String gerarToken(final UsuarioEntity usuario) {
        log.debug("Gerando token para o usuário de ID {}", usuario.getId());

        try {
            Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY_JWT);

            return JWT.create()
                .withIssuer(ISSUER_JWT)
                .withSubject(usuario.getLogin())
                .withExpiresAt(gerarDataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception) {
            log.error("Erro ao gerar token para o usuário de ID {}", usuario.getId());

            throw new InternalErrorException(ERRO_AO_GERAR_TOKEN_AUTENTICACAO,
                messageHelper.get(ERRO_AO_GERAR_TOKEN_AUTENTICACAO));
        }
    }

    public String extrairUsuario(final String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY_JWT);

            return JWT.require(algoritmo)
                .withIssuer(ISSUER_JWT)
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new AuthenticationErrorException(TOKEN_INVALIDO, messageHelper.get(TOKEN_INVALIDO));
        }
    }

    private Instant gerarDataExpiracao() {
        //ajustar
        return LocalDateTime.now().plusMinutes(HORAS_DURACAO_TOKEN).toInstant(ZoneOffset.of(OFFSET_FUSO_HORARIO));
    }
}
