package rfs.enterprise.library.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import rfs.enterprise.library.entity.Author
import java.time.ZonedDateTime


data class AuthorRequest(
    @field:NotBlank(message = "{name.notblank}")
    val name: String,
    @field:Email(regexp = ".+[@].+[\\\\.].+", message = "{email.invalid}")
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