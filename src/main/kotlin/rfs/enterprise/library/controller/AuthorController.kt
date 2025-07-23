package rfs.enterprise.library.controller

import jakarta.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import rfs.enterprise.library.dto.AuthorRequest
import rfs.enterprise.library.repository.AuthorRepository
import rfs.enterprise.library.validation.DuplicateEmailValidator

@Controller
class AuthorController(
    private val authorRepository: AuthorRepository,
    private val duplicateEmailValidator: DuplicateEmailValidator
) : AuthorApi {

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(duplicateEmailValidator)
    }

    @Transactional
    override fun create(author: AuthorRequest): ResponseEntity<Nothing> {
        authorRepository.save(author.toModel())
        return ResponseEntity.ok().build()
    }
}