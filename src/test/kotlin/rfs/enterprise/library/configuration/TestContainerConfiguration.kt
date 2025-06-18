package rfs.enterprise.library.configuration

import org.springframework.test.context.DynamicPropertyRegistry

interface TestContainerConfiguration<T> {
    fun start()
    fun configureProperties(registry: DynamicPropertyRegistry)
    fun getContainer(): T
}