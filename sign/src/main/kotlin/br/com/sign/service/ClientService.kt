package br.com.sign.service

import br.com.sign.dto.ClientDTO
import br.com.sign.exceptions.ApplicationException
import br.com.sign.form.ClientForm
import br.com.sign.form.LoginForm
import br.com.sign.form.toModel
import br.com.sign.model.Client
import br.com.sign.model.toDTO
import br.com.sign.repository.ClientRepository
import br.com.sign.utils.encode
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ClientService(
        private val clienteRepository: ClientRepository
) {


    @Transactional
    fun saveClient(form: ClientForm): ClientDTO {
        val persistedUser = this.clienteRepository.findByEmail(form.email)
        if (!persistedUser.isPresent) throw ApplicationException("user.not.found")

        val user = persistedUser.get()
        return this.clienteRepository.save(
            form.toModel(user.id, user.password)
        ).toDTO()
    }


    @Transactional
    fun deleteClient(id: Long) {
        val clientOp =  this.clienteRepository.findById(id)
        if(clientOp.isPresent) this.clienteRepository.delete(clientOp.get())
        else throw ApplicationException("client.not.found")
    }

    @Transactional
    fun saveUser(form: LoginForm): ClientDTO {

        val found = this.clienteRepository.findByEmail(form.email)
        if (found.isPresent) throw ApplicationException("user.already.registered")

        val client = Client(
            email = form.email,
            password = encode(form.password)
        )

        return this.clienteRepository.save(client).toDTO()

    }


}


