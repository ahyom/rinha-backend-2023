package com.soave.rinhabackend.domain.entity

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import java.time.LocalDate
import java.util.UUID

@Entity(name = "tb_pessoa")
class Pessoa(
    @Id
    var id: UUID,

    @Column(name = "apelido", nullable = false, unique = true)
    var apelido: String,

    @Column(name = "nome", nullable = false, unique = true)
    var nome: String,

    @Column(name = "nascimento", nullable = false)
    var nascimento: LocalDate,

    @ElementCollection
    @CollectionTable(name = "tb_stack", joinColumns = [JoinColumn(name = "pessoa_id")])
    @Column(nullable = true)
    var stack: List<String>?,
)
