package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.AssistenteEntity;

public interface AssistenteRepository extends CrudRepository<AssistenteEntity, Long> {

    @Modifying
    @Query("UPDATE Assistente a SET dataExclusao = CURRENT_DATE WHERE a = :assistente")
    void delete(final AssistenteEntity assistente);
}
