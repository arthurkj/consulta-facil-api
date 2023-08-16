package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.MedicoEntity;

public interface MedicoRepository extends CrudRepository<MedicoEntity, Long> {

    @Modifying
    @Query("UPDATE Medico m SET dataExclusao = CURRENT_DATE WHERE m = :medico")
    void delete(final MedicoEntity medico);

    boolean existsByCrm(final String crm);
}
