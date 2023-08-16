package br.com.akj.api.service.email;


import static br.com.akj.api.builder.EmailEntityBuilder.buildEmailEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.akj.api.entity.EmailEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.repository.EmailRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroEmailService {

    private final EmailRepository emailRepository;

    @Transactional
    public EmailEntity realizar(@NotNull final String email, @NotNull final UsuarioEntity usuario) {
        log.info("Cadastrando email para usuário {}", usuario.getId());

        return emailRepository.save(buildEmailEntity(email, usuario));
    }
}
