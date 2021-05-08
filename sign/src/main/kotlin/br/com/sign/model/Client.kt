package br.com.sign.model

import br.com.sign.dto.ClientDTO
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.ArrayList
import javax.persistence.*

@Entity
@Table(name = "clientes")
data class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    val id: Long = 0,

    @Column(name = "nm_cliente")
    val name: String? = "",

    @Column(name = "email_cliente", unique = true)
    val email: String,

    @Column(name = "secret_cliente")
    private val password: String,

    @Column(name = "reset_token")
    var resetToken: String = "",


    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "clients")
    private val address: Address? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "perfis")
    private val profiles: MutableList<Profiles> = ArrayList<Profiles>()
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            this.profiles

    override fun getPassword(): String = this.password
    override fun getUsername(): String = this.email

    override fun isEnabled(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun toString(): String {
        return "Client(name=$name, email='$email', resetToken='$resetToken', address=$address)"
    }
}

fun Client.toDTO() = ClientDTO(
        id = id,
        name = name ?: "",
        email = email
)
