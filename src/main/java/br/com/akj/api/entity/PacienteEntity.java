package br.com.akj.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@SuperBuilder
@NoArgsConstructor
public class PacienteEntity extends UsuarioEntity {

    private String cpf;
}