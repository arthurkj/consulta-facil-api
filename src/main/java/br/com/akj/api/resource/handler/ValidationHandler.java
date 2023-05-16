package br.com.akj.api.resource.handler;

import static br.com.akj.api.errors.Error.ERRO_INTERNO;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.akj.api.errors.dto.ErrorDTO;
import br.com.akj.api.exception.AbstractErrorException;
import br.com.akj.api.exception.BusinessErrorException;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ValidationHandler {

    private static final String MENSAGEM_LOG_ERRO = "Erro na API";
    private final ObjectProvider<Tracer> tracerProvider;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(final Exception exception) {

        final String spanId = ofNullable(tracerProvider.getObject().currentSpan())
            .map(Span::context)
            .map(TraceContext::spanId)
            .orElse(EMPTY);

        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception, spanId);

        return new ResponseEntity<>(errorInfo, ERRO_INTERNO.getHttpStatus());
    }

    @ExceptionHandler(AbstractErrorException.class)
    public ResponseEntity<ErrorDTO> handleException(final AbstractErrorException exception) {

        final String spanId = ofNullable(exception.getSpanId())
            .filter(StringUtils::isNotEmpty)
            .orElseGet(() -> ofNullable(tracerProvider.getObject().currentSpan())
                .map(Span::context)
                .map(TraceContext::spanId)
                .orElse(EMPTY));

        log.error(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception, spanId);

        return new ResponseEntity<>(errorInfo, exception.getStatusCode());
    }

    @ExceptionHandler(BusinessErrorException.class)
    public ResponseEntity<ErrorDTO> handleException(final BusinessErrorException exception) {

        final String spanId = ofNullable(exception.getSpanId())
            .filter(StringUtils::isNotEmpty)
            .orElseGet(() -> ofNullable(tracerProvider.getObject().currentSpan())
                .map(Span::context)
                .map(TraceContext::spanId)
                .orElse(EMPTY));

        log.warn(MENSAGEM_LOG_ERRO, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception, spanId);

        return new ResponseEntity<>(errorInfo, exception.getStatusCode());
    }
}
