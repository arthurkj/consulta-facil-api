package br.com.akj.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.akj.api.entity.EnderecoEntity;

public interface EnderecoRepository extends CrudRepository<EnderecoEntity, Long> {

    @Modifying
    @Query("UPDATE Endereco e SET dataExclusao = CURRENT_DATE WHERE e = :endereco")
    void delete(final EnderecoEntity endereco);
}
