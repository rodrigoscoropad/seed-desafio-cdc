package rfs.enterprise.library.configuration.container

import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import rfs.enterprise.library.configuration.TestContainerConfiguration

object PostgresContainerConfiguration : TestContainerConfiguration<PostgreSQLContainer<*>> {

    private val container = PostgreSQLContainer("postgres:latest")
        .withExposedPorts(5432)
        .withDatabaseName("library")
        .withUsername("root")
        .withPassword("root")
        .withReuse(true)
        .withLabel("reuse.UUID", "aaabe49b-bd02-4f78-ad5b-97627ca084a6")
        .waitingFor(Wait.forListeningPort())

    override fun start() {
        container.start()
    }

    override fun configureProperties(registry: DynamicPropertyRegistry) = with(registry) {
        add("spring.datasource.url") { container.jdbcUrl }
        add("spring.datasource.username") { container.username }
        add("spring.datasource.password") { container.password }
    }

    override fun getContainer(): PostgreSQLContainer<*> {
        return container
    }
}