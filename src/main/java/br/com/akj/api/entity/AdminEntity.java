package br.com.akj.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Admin")
@Table(name = "admins")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class AdminEntity extends UsuarioEntity {

}