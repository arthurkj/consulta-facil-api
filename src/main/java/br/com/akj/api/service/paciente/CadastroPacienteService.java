package br.com.akj.api.service.paciente;

import static br.com.akj.api.builder.PacienteEntityBuilder.buildPacienteEntity;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;
import br.com.akj.api.entity.UsuarioEntity;
import br.com.akj.api.service.email.CadastroEmailService;
import br.com.akj.api.service.endereco.CadastroEnderecoService;
import br.com.akj.api.service.login.CadastroUsuarioService;
import br.com.akj.api.service.telefone.CadastroTelefoneService;
import br.com.akj.api.validator.cadastro.paciente.CadastroPacienteValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroPacienteService {

    private final CadastroUsuarioService cadastroUsuarioService;
    private final List<CadastroPacienteValidator> cadastroPacienteValidatorList;
    private final CadastroEnderecoService cadastroEnderecoService;
    private final CadastroEmailService cadastroEmailService;
    private final CadastroTelefoneService cadastroTelefoneService;

    @Transactional
    public Long realizar(final CadastroPacienteRequest request) {
        log.info("Realizando cadastro de paciente de CPF {}", request.cpf());

        cadastroPacienteValidatorList.forEach(validator -> validator.validar(request));

        final UsuarioEntity paciente = cadastroUsuarioService.realizar(request.usuario(), buildPacienteEntity(request));

        cadastroEnderecoService.realizar(request.endereco(), paciente);
        cadastroEmailService.realizar(request.email(), paciente);
        cadastroTelefoneService.realizar(request.telefone(), paciente);

        return paciente.getId();
    }
}
