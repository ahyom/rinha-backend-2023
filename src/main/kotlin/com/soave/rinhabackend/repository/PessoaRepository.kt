package com.soave.rinhabackend.repository

import com.soave.rinhabackend.domain.entity.Pessoa
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PessoaRepository : CrudRepository<Pessoa, UUID> {

    @Query(
        nativeQuery = true,
        value = """
            SELECT DISTINCT p.* 
              FROM tb_pessoa p
            LEFT JOIN tb_stack s ON p.id = s.pessoa_id
             WHERE p.apelido ILIKE CONCAT('%', :searchTerm, '%') 
                OR p.nome    ILIKE CONCAT('%', :searchTerm, '%') 
                OR s.stack   ILIKE CONCAT('%', :searchTerm, '%')
            LIMIT 50
        """,
    )
    fun findAllBySearchTerm(searchTerm: String): List<Pessoa>
}
