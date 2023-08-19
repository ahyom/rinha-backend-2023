package com.soave.rinhabackend.controller

import com.soave.rinhabackend.domain.mapper.PessoaMapper
import com.soave.rinhabackend.domain.request.PessoaRequest
import com.soave.rinhabackend.service.PessoaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pessoas")
class PessoaController @Autowired constructor(
    private var pessoaService: PessoaService,
    private var pessoaMapper: PessoaMapper,
) {

    @PostMapping
    fun createPessoa(
        @RequestBody pessoaRequest: PessoaRequest,
    ): ResponseEntity<PessoaRequest> {
        val pessoa = pessoaMapper.toEntity(pessoaRequest)

        pessoaService.createPessoa(pessoa)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Location", "/pessoas/${pessoa.id}")
            .body(pessoaMapper.toDomain(pessoa))
    }

    @GetMapping("/{pessoa-id}")
    fun getPessoaById(
        @PathVariable("pessoa-id") pessoaId: String,
    ): ResponseEntity<PessoaRequest> {
        val pessoa = pessoaService.getPessoaById(pessoaId)
        return ResponseEntity.status(HttpStatus.OK).body(pessoaMapper.toDomain(pessoa))
    }

    @GetMapping
    fun getPessoasWithFilter(
        @RequestParam searchTerm: String,
    ): ResponseEntity<List<PessoaRequest>> {
        val pessoas = pessoaService.getPessoaBySearchTerm(searchTerm)
        val pessoasList = pessoas.map { pessoaMapper.toDomain(it) }
        return ResponseEntity.status(HttpStatus.OK).body(pessoasList)
    }

    @GetMapping("/contagem-pessoas")
    fun getTotalPessoas(): ResponseEntity<Int> {
        TODO()
    }
}
