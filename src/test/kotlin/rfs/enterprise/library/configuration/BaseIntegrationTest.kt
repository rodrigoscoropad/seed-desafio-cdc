package rfs.enterprise.library.configuration

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import rfs.enterprise.library.Application
import rfs.enterprise.library.TestcontainersConfiguration
import rfs.enterprise.library.configuration.container.PostgresContainerConfiguration

@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration::class)
abstract class BaseIntegrationTest {
    companion object {
        init {
            //initialize containers here
            PostgresContainerConfiguration.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            PostgresContainerConfiguration.configureProperties(registry)
        }
    }
}