package br.com.akj.api.resource.handler;

import static br.com.akj.api.errors.Error.ERRO_INTERNO;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.akj.api.errors.dto.ErrorDTO;
import br.com.akj.api.exception.AbstractErrorException;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ValidationHandler extends ResponseEntityExceptionHandler {

    private static final String MENSAGEM_LOG_ERRO = "Erro na API";
    private final ObjectProvider<Tracer> tracerProvider;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(final Exception exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, ERRO_INTERNO.getHttpStatus());
    }

    @ExceptionHandler(AbstractErrorException.class)
    public ResponseEntity<ErrorDTO> handleException(final AbstractErrorException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, exception.getStatusCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleException(final AuthenticationException exception) {
        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
    }

   //TODO: adicionar validação das exceções de segurança e validação de request
}
