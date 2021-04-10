package br.com.sign.controller

import br.com.sign.dto.ClientDTO
import br.com.sign.form.ClientForm
import br.com.sign.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["v1/client"])
class ClientController (
    val service: ClientService
){



    @GetMapping
    fun listClient() = this.service.getClients()

    @PostMapping
    fun createClient(@RequestBody @Valid form : ClientForm, uriBuilder: UriComponentsBuilder): ResponseEntity<ClientDTO> {
        val client = this.service.saveClient(form)

        val uri = uriBuilder.path("v1/client/{id}").buildAndExpand(client.id).toUri()

        return ResponseEntity.created(uri).body(client)
    }

}