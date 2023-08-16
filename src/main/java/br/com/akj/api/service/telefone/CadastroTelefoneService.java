package br.com.akj.api.service.telefone;


import static br.com.akj.api.builder.TelefoneEntityBuilder.buildTelefoneEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.akj.api.builder.TelefoneEntityBuilder;
import br.com.akj.api.entity.TelefoneEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.repository.TelefoneRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroTelefoneService {

    private final TelefoneRepository telefoneRepository;

    @Transactional
    public TelefoneEntity realizar(@NotNull final String telefone, @NotNull final UsuarioEntity usuario) {
        log.info("Cadastrando telefone para usuário {}", usuario.getId());

        return telefoneRepository.save(buildTelefoneEntity(telefone, usuario));
    }
}
