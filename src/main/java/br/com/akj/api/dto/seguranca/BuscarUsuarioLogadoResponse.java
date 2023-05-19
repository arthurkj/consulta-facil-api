package br.com.akj.api.dto.seguranca;

import java.time.LocalDateTime;

import br.com.akj.api.enumeration.TipoUsuario;

public record BuscarUsuarioLogadoResponse(Long id, String login, String nome, TipoUsuario tipo, LocalDateTime dataCadastro) {

}
