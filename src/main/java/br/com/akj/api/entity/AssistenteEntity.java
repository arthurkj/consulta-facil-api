package br.com.akj.api.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Assistente")
@Table(name = "assistentes")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class AssistenteEntity extends UsuarioEntity {

    @OneToMany
    @JoinTable(
        name = "assistente_medico",
        joinColumns = @JoinColumn(name = "assistente_id"),
        inverseJoinColumns = @JoinColumn(name = "medico_id")
    )
    private List<MedicoEntity> medicos;

}