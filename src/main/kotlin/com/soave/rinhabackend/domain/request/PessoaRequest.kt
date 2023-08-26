package com.soave.rinhabackend.domain.request

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.Validation
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.util.UUID

class PessoaRequest(

    var id: UUID?,

    @field:NotBlank(message = "Apelido é obrigatório")
    @field:Size(max = 32, message = "Campo apelido deve ter no máximo 32 caracteres")
    var apelido: String,

    @field:NotBlank(message = "Nome é obrigatório")
    @field:Size(max = 100, message = "Campo nome deve ter no máximo 100 caracteres")
    var nome: String,

    @field:NotBlank(message = "Data de nascimento é obrigatória")
    var nascimento: String,

    var stack: List<String>?,
) {
    fun validate(): List<String> {
        val validator = Validation.buildDefaultValidatorFactory().validator
        return validator.validate(this).map { it.message }
    }
}
