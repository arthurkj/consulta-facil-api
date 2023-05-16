package br.com.akj.api.exception;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.akj.api.errors.dto.ErrorMessage;
import lombok.Getter;

@Getter
public abstract class AbstractErrorException extends ResponseStatusException {

    private final String code;
    private final String spanId;
    private final List<ErrorMessage> errors;

    protected AbstractErrorException(final HttpStatus status, final String reason, final String code) {
        super(status, reason);
        this.code = code;
        this.errors = new ArrayList<>();
        this.spanId = EMPTY;
    }

    protected AbstractErrorException(final HttpStatus status, final String reason, final String code,
        final List<ErrorMessage> errors, final String spanId) {
        super(status, reason);
        this.code = code;
        this.errors = errors;
        this.spanId = spanId;
    }

    protected AbstractErrorException(final HttpStatus status, final String reason, final String code,
        final List<ErrorMessage> errors) {
        super(status, reason);
        this.code = code;
        this.errors = errors;
        this.spanId = EMPTY;
    }
}
