package br.com.akj.api.validator.cadastro.usuario;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;

public interface CadastroUsuarioValidator {

    void validar(final CadastroUsuarioRequest request);
}
