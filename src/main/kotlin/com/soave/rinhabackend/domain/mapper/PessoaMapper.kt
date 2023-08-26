package com.soave.rinhabackend.domain.mapper

import com.soave.rinhabackend.domain.entity.Pessoa
import com.soave.rinhabackend.domain.request.PessoaRequest
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Objects.isNull
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Component
class PessoaMapper : Mapper<PessoaRequest, Pessoa> {
    override fun toEntity(domain: PessoaRequest): Pessoa {
        domain.validate().let {
            if (it.isNotEmpty()) {
                throw IllegalArgumentException(it.toString())
            }
        }

        if (isNull(domain.id)) {
            domain.id = UUID.randomUUID()
        }
        return Pessoa(
            id = domain.id!!,
            apelido = domain.apelido,
            nome = domain.nome,
            nascimento = dateStringToLocalDate(domain.nascimento),
            stack = domain.stack,
        )
    }

    override fun toDomain(entity: Pessoa): PessoaRequest {
        return PessoaRequest(
            id = entity.id,
            apelido = entity.apelido,
            nome = entity.nome,
            nascimento = entity.nascimento.toString(),
            stack = entity.stack,
        )
    }

    private fun dateStringToLocalDate(date: String): LocalDate {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        } catch (e: Exception) {
            throw IllegalArgumentException("Data [$date] eh invalida")
        }
    }
}
