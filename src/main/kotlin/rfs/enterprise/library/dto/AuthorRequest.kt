package rfs.enterprise.library.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import rfs.enterprise.library.entity.Author
import rfs.enterprise.library.validation.UniqueValue
import java.time.ZonedDateTime


data class AuthorRequest(
    @field:NotBlank(message = "{name.notblank}")
    val name: String,
    @field:Email(regexp = ".+[@].+[\\\\.].+", message = "{email.invalid}")
    @field:UniqueValue(message = "{email.duplicate}", fieldName = "email", domainClass = Author::class)
    val email: String,
    @field:Size(min = 3, max = 200, message = "{description.maxsize}")
    val description: String,
) {
    fun toModel() = Author(
        name = name,
        email = email,
        description = description,
        createdAt = ZonedDateTime.now(),
        updatedAt = ZonedDateTime.now()
    )
}