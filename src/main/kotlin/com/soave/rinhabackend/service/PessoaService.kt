package com.soave.rinhabackend.service

import com.soave.rinhabackend.domain.entity.Pessoa
import com.soave.rinhabackend.exception.EntityAlreadyExistsException
import com.soave.rinhabackend.exception.NotFoundException
import com.soave.rinhabackend.repository.PessoaRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Service
class PessoaService @Autowired constructor(
    private val pessoaRepository: PessoaRepository,
) {

    fun createPessoa(pessoa: Pessoa): Pessoa {
        logger.debug { "Creating pessoa with ID [${pessoa.id}] and name [${pessoa.nome}]" }
        val pessoaCreated: Pessoa
        try {
            pessoaCreated = pessoaRepository.save(pessoa)
            return pessoaCreated
        } catch (e: DataIntegrityViolationException) {
            logger.error { "Error creating pessoa with ID [${pessoa.id}] and name [${pessoa.nome}]" }
            throw EntityAlreadyExistsException("Pessoa with ID [${pessoa.id}] already exists")
        } catch (e: Exception) {
            logger.error { "Error creating pessoa with ID [${pessoa.id}] and name [${pessoa.nome}]" }
            throw e
        }
    }

    fun getPessoaById(pessoaId: String): Pessoa {
        logger.debug { "Getting pessoa with ID [$pessoaId]" }
        return pessoaRepository
            .findById(UUID.fromString(pessoaId))
            .orElseThrow { NotFoundException("Pessoa with ID [$pessoaId] not found") }
    }

    fun getPessoaBySearchTerm(searchTerm: String): List<Pessoa> {
        logger.debug { "Getting pessoa with searchTerm [$searchTerm]" }
        return pessoaRepository.findAllBySearchTerm(searchTerm)
    }

    fun getTotalPessoas(): Int {
        logger.debug { "Getting total pessoas" }
        return pessoaRepository.count().toInt()
    }
}
