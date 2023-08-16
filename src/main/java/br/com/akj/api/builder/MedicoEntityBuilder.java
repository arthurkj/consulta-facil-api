package br.com.akj.api.builder;

import br.com.akj.api.dto.medico.CadastroMedicoRequest;
import br.com.akj.api.entity.MedicoEntity;
import br.com.akj.api.enumeration.TipoUsuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicoEntityBuilder {

    public static MedicoEntity buildMedicoEntity(final CadastroMedicoRequest request) {
        return MedicoEntity.builder()
            .tipo(TipoUsuario.MEDICO)
            .crm(request.crm())
            .especialidade(request.especialidade())
            .build();
    }
}
