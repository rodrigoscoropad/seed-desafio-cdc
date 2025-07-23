package rfs.enterprise.library.validation

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import rfs.enterprise.library.dto.AuthorRequest
import rfs.enterprise.library.repository.AuthorRepository

@Component
class DuplicateEmailValidator(
    private val authorRepository: AuthorRepository,
    private val messageSource: MessageSource
) : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return AuthorRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            return
        }

        val authorRequest = target as AuthorRequest
        val author = authorRepository.findByEmail(authorRequest.email)
        if (author != null) {
            errors.rejectValue(
                "email",
                "email.duplicate",
                messageSource.getMessage("error.validation", null, LocaleContextHolder.getLocale())
            )
        }
    }
}