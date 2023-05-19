package br.com.akj.api.resource.handler;

import static br.com.akj.api.errors.Error.AUTENTICACAO_OBRIGATORIA;
import static br.com.akj.api.errors.Error.ERRO_INTERNO;
import static br.com.akj.api.errors.Error.PARAMETROS_INVALIDOS;
import static br.com.akj.api.errors.Error.RECURSO_NAO_ENCONTRADO;
import static br.com.akj.api.errors.Error.USUARIO_SEM_PERMISSAO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.akj.api.errors.Error;
import br.com.akj.api.errors.dto.ErrorDTO;
import br.com.akj.api.errors.dto.ErrorMessage;
import br.com.akj.api.exception.AbstractErrorException;
import br.com.akj.api.exception.AuthenticationErrorException;
import br.com.akj.api.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
@Component
public class ValidationHandler {

    private static final String MENSAGEM_LOG_ERRO = "Erro na API";

    private final MessageHelper messageHelper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleErroInterno(final Exception exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, ERRO_INTERNO.getHttpStatus());
    }

    @ExceptionHandler(AbstractErrorException.class)
    public ResponseEntity<ErrorDTO> handleExcecaoGenerica(final AbstractErrorException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, exception.getStatusCode());
    }

    @ExceptionHandler(AuthenticationErrorException.class)
    public ResponseEntity<ErrorDTO> handleExcecoesAutenticacao(final AuthenticationErrorException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleUsuarioNaoAutenticado(final AuthenticationException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);
        final Error erroDesejado = AUTENTICACAO_OBRIGATORIA;

        final ErrorDTO errorInfo = new ErrorDTO(erroDesejado, messageHelper.get(erroDesejado));

        return new ResponseEntity<>(errorInfo, erroDesejado.getHttpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAcessoNegado(final AccessDeniedException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);
        final Error erroDesejado = USUARIO_SEM_PERMISSAO;

        final ErrorDTO errorInfo = new ErrorDTO(erroDesejado, messageHelper.get(erroDesejado));

        return new ResponseEntity<>(errorInfo, erroDesejado.getHttpStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDTO> handleRecursoNaoEncontrado(final NoHandlerFoundException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);
        final Error erroDesejado = RECURSO_NAO_ENCONTRADO;

        final ErrorDTO errorInfo = new ErrorDTO(erroDesejado, messageHelper.get(erroDesejado, exception.getMessage()));

        return new ResponseEntity<>(errorInfo, erroDesejado.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleParametrosInvalidos(final MethodArgumentNotValidException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);
        final Error erroDesejado = PARAMETROS_INVALIDOS;

        final List<ErrorMessage> parametrosComErro = exception.getFieldErrors().stream()
            .map(ErrorMessage::new).toList();

        final ErrorDTO errorInfo = new ErrorDTO(erroDesejado, messageHelper.get(erroDesejado, exception.getMessage()),
            parametrosComErro);

        return new ResponseEntity<>(errorInfo, erroDesejado.getHttpStatus());
    }
}
