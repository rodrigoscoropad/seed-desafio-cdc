package rfs.enterprise.library.validation

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class UniqueValueValidator : ConstraintValidator<UniqueValue, Any> {

    private lateinit var domainAttribute: String
    private lateinit var domainClass: KClass<*>

    @PersistenceContext
    private lateinit var manager: EntityManager


    override fun initialize(constraintAnnotation: UniqueValue?) {
        domainAttribute = constraintAnnotation!!.fieldName
        domainClass = constraintAnnotation.domainClass
    }

    override fun isValid(p0: Any?, p1: ConstraintValidatorContext?): Boolean {
        val results =
            manager.createQuery("select 1 from ${domainClass.simpleName!!.lowercase()} e where e.${domainAttribute} = :value")
                .setParameter("value", p0)
                .resultList
        assert(results.size <= 1) { "There are more than 1 $domainClass with the same value $domainAttribute" }

        return results.isEmpty()
    }
}