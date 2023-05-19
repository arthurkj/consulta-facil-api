package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {

    @Modifying
    @Query("UPDATE Admin a SET dataExclusao = CURRENT_DATE WHERE a = :admin")
    void delete(final AdminEntity admin);
}
