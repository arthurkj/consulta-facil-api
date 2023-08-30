package br.com.akj.api.builder;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;
import br.com.akj.api.entity.PacienteEntity;
import br.com.akj.api.enumeration.TipoUsuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacienteEntityBuilder {

    public static PacienteEntity buildPacienteEntity(final CadastroPacienteRequest request) {
        return PacienteEntity.builder()
            .tipo(TipoUsuario.PACIENTE)
            .cpf(request.cpf())
            .build();
    }
}
