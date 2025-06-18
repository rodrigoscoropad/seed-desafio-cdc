package rfs.enterprise.library.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import rfs.enterprise.library.dto.AuthorRequest

@RequestMapping("/authors")
interface AuthorApi {
    @PostMapping
    fun create(@Valid @RequestBody author: AuthorRequest) : ResponseEntity<Nothing>
}