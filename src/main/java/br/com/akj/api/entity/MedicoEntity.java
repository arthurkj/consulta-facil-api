package br.com.akj.api.entity;

import br.com.akj.api.enumeration.Especialidade;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Medico")
@Table(name = "medicos")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class MedicoEntity extends UsuarioEntity {

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

}