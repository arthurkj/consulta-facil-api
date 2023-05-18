package br.com.akj.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class PacienteEntity extends UsuarioEntity {

    private String cpf;
}