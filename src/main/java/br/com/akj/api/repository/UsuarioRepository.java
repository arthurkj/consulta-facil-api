package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.akj.api.entity.UsuarioEntity;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    @Modifying
    @Query("UPDATE Usuario u SET dataExclusao = CURRENT_DATE WHERE u = :usuario")
    void delete(final UsuarioEntity usuario);

    UserDetails findByLogin(final String login);

    boolean existsByLogin(final String login);
}
