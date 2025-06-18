package rfs.enterprise.library.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rfs.enterprise.library.core.entity.Author

@Repository
interface AuthorRepository : JpaRepository<Author, Long>