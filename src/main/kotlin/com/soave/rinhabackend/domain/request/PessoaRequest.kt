package com.soave.rinhabackend.domain.request

import jakarta.validation.Validation
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.UUID

class PessoaRequest(

    private var id: UUID?,

    @field:NotBlank(message = "Apelido é obrigatório")
    @field:Size(max = 32, message = "Campo apelido deve ter no máximo 32 caracteres")
    private var apelido: String,

    @field:NotBlank(message = "Nome é obrigatório")
    @field:Size(max = 100, message = "Campo nome deve ter no máximo 100 caracteres")
    private var nome: String,

    @field:NotBlank(message = "Data de nascimento é obrigatória")
    private var nascimento: String,

    private var stack: List<String>?,
) {
    fun validate(): List<String> {
        val validator = Validation.buildDefaultValidatorFactory().validator
        return validator.validate(this).map { it.message }
    }
}
