package com.soave.rinhabackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.soave.rinhabackend.domain.request.PessoaRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import java.util.UUID

private const val BASE_ENDPOINT = "/pessoas"
private const val PESSOA_ID = "0678b0a4-2aed-4122-b02c-c704fa6a24a9"

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {

    private fun buildPessoaRequest(): PessoaRequest {
        return PessoaRequest(
            UUID.fromString(PESSOA_ID),
            apelido = "amoriléia",
            nome = "Amora Mariléia",
            nascimento = "2021-09-01",
            stack = listOf("Java", "Kotlin", "Spring"),
        )
    }
}
