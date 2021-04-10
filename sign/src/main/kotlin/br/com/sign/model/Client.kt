package br.com.sign.model

import br.com.sign.dto.ClientDTO
import javax.persistence.*

@Entity
@Table(name = "clientes")
data class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    val id: Long = 0,

    @Column(name = "nm_cliente")
    val name: String,
    @Column(name = "email_cliente")
    val email: String,
    @Column(name = "secret_cliente")
    val password: String
)

fun Client.toDTO() = ClientDTO(
        id = id,
        name = name,
        email = email
)
