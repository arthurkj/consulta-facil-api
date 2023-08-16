package br.com.akj.api.service.login;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.akj.api.dto.usuario.CadastroUsuarioRequest;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.repository.UsuarioRepository;
import br.com.akj.api.service.seguranca.GeracaoSenhaService;
import br.com.akj.api.validator.cadastro.usuario.CadastroUsuarioValidator;

@ExtendWith(MockitoExtension.class)
class CadastroUsuarioServiceTest {

    @InjectMocks
    private CadastroUsuarioService service;

    @Mock
    private List<CadastroUsuarioValidator> cadastroUsuarioValidatorList;

    @Mock
    private GeracaoSenhaService geracaoSenhaService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void realizar_ok() {
        final UsuarioEntity usuario = Fixture.make(MedicoEntity.class);
        final CadastroUsuarioRequest request = Fixture.make(CadastroUsuarioRequest.class);

        final String senhaCodificada = random(5).concat(request.senha());

        when(geracaoSenhaService.gerar(request.senha())).thenReturn(senhaCodificada);

        service.realizar(request, usuario);

        cadastroUsuarioValidatorList.forEach(validator -> verify(validator).validar(request));
        verify(geracaoSenhaService).gerar(request.senha());
        verify(usuarioRepository).save(usuario);
    }
}