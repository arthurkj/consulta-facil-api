package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.TelefoneEntity;

public interface TelefoneRepository extends CrudRepository<TelefoneEntity, Long> {

    @Modifying
    @Query("UPDATE Telefone t SET dataExclusao = CURRENT_DATE WHERE t = :telefone")
    void delete(final TelefoneEntity telefone);
}
