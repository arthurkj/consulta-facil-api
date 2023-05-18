package br.com.akj.api.errors.dto;

import static br.com.akj.api.errors.Error.ERRO_INTERNO;
import static java.util.Collections.singletonList;

import java.time.LocalDateTime;
import java.util.List;

import br.com.akj.api.exception.AbstractErrorException;

public record ErrorDTO(LocalDateTime timestamp, String message, String code, List<ErrorMessage> errors) {

    private static final String ERRO_INTERNO_MESSAGE = "Erro interno.";

    public ErrorDTO(final Exception exception) {
        this(LocalDateTime.now(), ERRO_INTERNO_MESSAGE, ERRO_INTERNO.getCode(),
            singletonList(new ErrorMessage(exception.getMessage())));
    }

    public ErrorDTO(final AbstractErrorException exception) {
        this(LocalDateTime.now(), exception.getReason(), exception.getCode(), exception.getErrors());
    }
}
