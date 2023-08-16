package br.com.akj.api.service.medico;

import static br.com.akj.api.builder.MedicoEntityBuilder.buildMedicoEntity;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.akj.api.dto.medico.CadastroMedicoRequest;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.service.email.CadastroEmailService;
import br.com.akj.api.service.endereco.CadastroEnderecoService;
import br.com.akj.api.service.login.CadastroUsuarioService;
import br.com.akj.api.service.telefone.CadastroTelefoneService;
import br.com.akj.api.validator.cadastro.medico.CadastroMedicoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroMedicoService {

    private final CadastroUsuarioService cadastroUsuarioService;
    private final List<CadastroMedicoValidator> cadastroMedicoValidatorList;
    private final CadastroEnderecoService cadastroEnderecoService;
    private final CadastroEmailService cadastroEmailService;
    private final CadastroTelefoneService cadastroTelefoneService;

    @Transactional
    public Long realizar(final CadastroMedicoRequest request) {
        log.info("Realizando cadastro de médico de CRM {}", request.crm());

        cadastroMedicoValidatorList.forEach(validator -> validator.validar(request));

        final UsuarioEntity medico = cadastroUsuarioService.realizar(request.usuario(), buildMedicoEntity(request));

        cadastroEnderecoService.realizar(request.endereco(), medico);
        cadastroEmailService.realizar(request.email(), medico);
        cadastroTelefoneService.realizar(request.telefone(), medico);

        return medico.getId();
    }
}
