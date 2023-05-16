package br.com.akj.api.errors.dto;

public record ErrorMessage(String field, String message) {

    public ErrorMessage(String message) {
        this("reason", message);
    }
}
