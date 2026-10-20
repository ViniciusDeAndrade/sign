package br.com.sign.controller

import br.com.sign.constants.CLIENT_BASE_PATH
import br.com.sign.dto.ClientDTO
import br.com.sign.form.ClientForm
import br.com.sign.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = [CLIENT_BASE_PATH])
class ClientController (
    val service: ClientService
){


    @PostMapping
    fun createClient(@RequestBody @Valid form : ClientForm, uriBuilder: UriComponentsBuilder): ResponseEntity<ClientDTO> {
        val client = this.service.saveClient(form)

        val uri = uriBuilder.path("$CLIENT_BASE_PATH/{id}").buildAndExpand(client.id).toUri()

        return ResponseEntity.created(uri).body(client)
    }

    @DeleteMapping("{id}")
    fun deleteClient(@PathVariable("id") id: Long): ResponseEntity.BodyBuilder {

        this.service.deleteClient(id)

        return ResponseEntity.ok()
    }

}