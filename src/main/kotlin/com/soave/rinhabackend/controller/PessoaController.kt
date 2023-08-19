package com.soave.rinhabackend.controller

import com.soave.rinhabackend.domain.request.PessoaRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pessoas")
class PessoaController {

    @PostMapping
    fun createPessoa(
        @RequestBody pessoaRequest: PessoaRequest,
    ): ResponseEntity<PessoaRequest> {
        TODO()
    }

    @GetMapping("/{pessoa-id}")
    fun getPessoaById(
        @PathVariable("pessoa-id") pessoaId: String,
    ): ResponseEntity<PessoaRequest> {
        TODO()
    }

    @GetMapping
    fun getPessoasWithFilter(
        @RequestParam searchTerm: String,
    ): ResponseEntity<List<PessoaRequest>> {
        TODO()
    }

    @GetMapping("/contagem-pessoas")
    fun getTotalPessoas(): ResponseEntity<Int> {
        TODO()
    }
}
