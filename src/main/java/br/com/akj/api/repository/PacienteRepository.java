package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.PacienteEntity;

public interface PacienteRepository extends CrudRepository<PacienteEntity, Long> {

    @Modifying
    @Query("UPDATE Paciente p SET dataExclusao = CURRENT_DATE WHERE p = :paciente")
    void delete(final PacienteEntity paciente);

    boolean existsByCpf(final String cpf);
}
