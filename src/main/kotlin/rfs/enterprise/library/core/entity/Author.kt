package rfs.enterprise.library.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "author")
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long? = null,
    val name: String,
    val email: String,
    val description: String
)