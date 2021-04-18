package br.com.sign.model

import javax.persistence.*

@Entity
@Table(name = "enderecos")
data class Address (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enredeco")
    val id: Long = 0,

    @Column(name = "rua")
    val street: String,

    @Column(name = "numero")
    val number: String = "with no number",

    @Column(name = "bairro")
    val neighborhood: String,

    @Column(name = "cidade")
    val city: String,

    @Column(name = "estado")
    val state: String,

    @OneToMany(mappedBy = "address")
    val clients: List<Client>? = null
)
