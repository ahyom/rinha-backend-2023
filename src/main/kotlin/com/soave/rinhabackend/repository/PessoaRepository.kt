package com.soave.rinhabackend.repository

import com.soave.rinhabackend.domain.entity.Pessoa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PessoaRepository : CrudRepository<Pessoa, UUID>