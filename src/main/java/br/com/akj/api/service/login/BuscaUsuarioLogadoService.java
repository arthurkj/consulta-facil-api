package br.com.akj.api.service.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.akj.api.dto.seguranca.BuscarUsuarioLogadoResponse;
import br.com.akj.api.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BuscaUsuarioLogadoService {

    public BuscarUsuarioLogadoResponse buscar() {
        log.debug("Buscando dados do usuário logado");

        final UsuarioEntity usuario = (UsuarioEntity) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();

        return new BuscarUsuarioLogadoResponse(usuario.getId(), usuario.getLogin(), usuario.getNome(),
            usuario.getTipo(), usuario.getDataCadastro());
    }
}
