package br.com.akj.api.service.login;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.repository.UsuarioRepository;
import br.com.akj.api.service.seguranca.GeracaoSenhaService;
import br.com.akj.api.validator.cadastro.usuario.CadastroUsuarioValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroUsuarioService {

    private final List<CadastroUsuarioValidator> cadastroUsuarioValidatorList;
    private final GeracaoSenhaService geracaoSenhaService;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioEntity realizar(final CadastroUsuarioRequest request, final UsuarioEntity usuario) {
        log.info("Cadastrando usuário para {}", request.login());

        cadastroUsuarioValidatorList.forEach(validator -> validator.validar(request));

        atribuirDadosUsuario(usuario, request);

        return usuarioRepository.save(usuario);
    }

    private void atribuirDadosUsuario(final UsuarioEntity usuario, final CadastroUsuarioRequest request) {
        final String senhaCodificada = geracaoSenhaService.gerar(request.senha());

        usuario.setLogin(request.login());
        usuario.setSenha(senhaCodificada);
        usuario.setNome(request.nome());
    }
}
