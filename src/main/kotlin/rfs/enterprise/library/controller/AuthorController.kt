package rfs.enterprise.library.controller

import jakarta.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import rfs.enterprise.library.dto.AuthorRequest
import rfs.enterprise.library.repository.AuthorRepository

@Controller
class AuthorController(
    private val authorRepository: AuthorRepository,
) : AuthorApi {
    @Transactional
    override fun create(author: AuthorRequest): ResponseEntity<Nothing> {
        authorRepository.save(author.toModel())
        return ResponseEntity.ok().build()
    }
}