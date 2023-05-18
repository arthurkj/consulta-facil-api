package br.com.akj.api.entity;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.akj.api.enumeration.TipoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Where(clause = "data_exclusao IS NULL")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public abstract class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    private String nome;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    private LocalDateTime dataExclusao;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority role = new SimpleGrantedAuthority(this.tipo.getRole());
        return Collections.singletonList(role);
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isNull(dataExclusao);
    }
}