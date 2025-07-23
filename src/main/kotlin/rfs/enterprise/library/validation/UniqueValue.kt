package rfs.enterprise.library.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [UniqueValueValidator::class])
@Retention(AnnotationRetention.RUNTIME)
annotation class UniqueValue(
    val message: String = "{uniquevalue}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<Payload>> = [],
    val fieldName: String,
    val domainClass: KClass<*>
)