package com.soave.rinhabackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.soave.rinhabackend.domain.request.PessoaRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

private const val BASE_ENDPOINT = "/pessoas"
private const val PESSOA_ID = "0678b0a4-2aed-4122-b02c-c704fa6a24a9"

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {

    var pessoaRequest = buildPessoaRequestWithStack()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when valid request then returns 201 HTTP status with resource create on header`() {
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(header().string("Location", "/pessoas/$PESSOA_ID"))
    }

    @Test
    fun `when stack is null then returns 201 HTTP status with resource create on header`() {
        pessoaRequest.stack = null
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(header().string("Location", "/pessoas/$PESSOA_ID"))
    }

    @Test
    fun `when pessoa with nome already exists then returns 422 HTTP status`() {
        // TODO -> mock should thrown exception EntityAlreadyExistsException
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.message").value("Pessoa with name ${pessoaRequest.nome} already exists"))
    }

    @Test
    fun `when pessoa with apelido already exists then returns 422 HTTP status`() {
        // TODO -> mock should thrown exception EntityAlreadyExistsException
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.message").value("Pessoa with apelido ${pessoaRequest.apelido} already exists"))
    }

    @Test
    fun `when apelido is empty then return 422 HTTP status`() {
        pessoaRequest.apelido = ""
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.cause").value("[Apelido é obrigatório]"))
    }

    @Test
    fun `when nome is empty then return 422 HTTP status`() {
        pessoaRequest.nome = ""
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.cause").value("[Nome é obrigatório]"))
    }

    @Test
    fun `when apelido has more then 32 characters then return 422 HTTP status`() {
        pessoaRequest.apelido = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 33 characters
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.cause").value("[Campo apelido deve ter no máximo 32 caracteres]"))
    }

    @Test
    fun `when nome has more then 100 characters then return 422 HTTP status`() {
        pessoaRequest.nome = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 101 characters
        mockMvc.perform(
            post(BASE_ENDPOINT)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaRequest)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.cause").value("[Campo nome deve ter no máximo 100 caracteres]"))
    }

    // TODO -> should validate nome type (only string allowed)
    // TODO -> should validate apelido type (only string allowed)
    // TODO -> should validate stack type (only string allowed)

    private fun buildPessoaRequestWithStack(): PessoaRequest {
        return PessoaRequest(
            UUID.fromString(PESSOA_ID),
            apelido = "amoriléia",
            nome = "Amora Mariléia",
            nascimento = "2021-09-01",
            stack = listOf("Java", "Kotlin", "Spring"),
        )
    }
}
