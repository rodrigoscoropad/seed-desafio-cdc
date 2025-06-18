package rfs.enterprise.library.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rfs.enterprise.library.entity.Author

@Repository
interface AuthorRepository : JpaRepository<Author, Long>