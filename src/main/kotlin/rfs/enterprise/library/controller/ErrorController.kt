package rfs.enterprise.library.controller

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import rfs.enterprise.library.dto.ErrorDetail
import rfs.enterprise.library.dto.ErrorResponse

@RestControllerAdvice
class ErrorController(private val messageSource: MessageSource) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(
        exception: MethodArgumentNotValidException
    ): ErrorResponse {
        val fieldErrors = exception.bindingResult.fieldErrors
        return buildValidationErrors(fieldErrors)
    }

    private fun buildValidationErrors(fieldErrors: List<FieldError>): ErrorResponse {
        val errors =
            fieldErrors.map { ErrorDetail(it.field, messageSource.getMessage(it, LocaleContextHolder.getLocale())) }
        return ErrorResponse(
            message = messageSource.getMessage("error.validation", null, LocaleContextHolder.getLocale()),
            errors = errors
        )
    }
}