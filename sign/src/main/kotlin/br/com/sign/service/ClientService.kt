package br.com.sign.service

import br.com.sign.form.ClientForm
import br.com.sign.form.toModel
import br.com.sign.model.toDTO
import br.com.sign.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
        private val clienteRepository: ClientRepository
) {
    fun getClients() = this.clienteRepository.findAll().map { it -> it.toDTO() }


    fun saveClient(form: ClientForm) =
        this.clienteRepository.save(form.toModel()).toDTO()



}


