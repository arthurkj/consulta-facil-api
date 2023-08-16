package br.com.akj.api.service.seguranca;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.exception.AuthenticationErrorException;
import br.com.akj.api.fixture.Fixture;
import br.com.akj.api.helper.MessageHelper;
import br.com.akj.api.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class AutenticacaoUsuarioServiceTest {

    @InjectMocks
    private AutenticacaoUsuarioService service;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MessageHelper messageHelper;

    @Test
    void loadUserByUsername_ok() {
        final String username = random(8);

        final UsuarioEntity usuario = Fixture.make(MedicoEntity.class);

        when(usuarioRepository.findByLogin(username)).thenReturn(usuario);

        final UserDetails result = service.loadUserByUsername(username);

        assertNotNull(result);
        assertEquals(usuario, result);

        verify(usuarioRepository).findByLogin(username);
    }

    @Test
    void loadUserByUsername_not_found() {
        final String username = random(8);

        when(usuarioRepository.findByLogin(username)).thenReturn(null);

        assertThrows(AuthenticationErrorException.class, () -> service.loadUserByUsername(username));

        verify(usuarioRepository).findByLogin(username);
    }
}