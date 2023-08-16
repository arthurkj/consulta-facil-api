package br.com.akj.api.service.endereco;


import static br.com.akj.api.builder.EnderecoEntityBuilder.buildEnderecoEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.akj.api.builder.EnderecoEntityBuilder;
import br.com.akj.api.dto.endereco.CadastroEnderecoRequest;
import br.com.akj.api.entity.EnderecoEntity;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.repository.EnderecoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroEnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoEntity realizar(@NotNull final CadastroEnderecoRequest request,
        @NotNull final UsuarioEntity usuario) {
        log.info("Cadastrando endereço do usuário {}", usuario.getId());

        return enderecoRepository.save(buildEnderecoEntity(request, usuario));
    }
}
