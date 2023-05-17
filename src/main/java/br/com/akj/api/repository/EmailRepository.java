package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.EmailEntity;

public interface EmailRepository extends CrudRepository<EmailEntity, Long> {

    @Modifying
    @Query("UPDATE Email e SET dataExclusao = CURRENT_DATE WHERE e = :email")
    void delete(final EmailEntity email);
}
