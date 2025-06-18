package rfs.enterprise.library.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import rfs.enterprise.library.configuration.BaseIntegrationTest
import rfs.enterprise.library.dto.AuthorRequest
import rfs.enterprise.library.repository.AuthorRepository


class AuthorControllerIntegrationTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Test
    fun `should create a new author with valid request`() {
        // Given
        val authorRequest = AuthorRequest(name = "John Doe", email = "john@gmail.com", description = "An author from the test suite")

        // When
        val response = restTemplate.postForEntity("/authors", authorRequest, Nothing::class.java)
        val savedAuthor = authorRepository.findAll().first()
        // Then
        assertThat(response.statusCode.is2xxSuccessful)
        with(savedAuthor) {
            assertThat(name).isEqualTo(authorRequest.name)
            assertThat(email).isEqualTo(authorRequest.email)
            assertThat(description).isEqualTo(authorRequest.description)
        }
    }

}