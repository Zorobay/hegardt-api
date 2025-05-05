package se.hegardt.api.validators

import jakarta.validation.Constraint
import jakarta.validation.Payload

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Constraint(validatedBy = LongTextConstraintValidator)
@Target([ElementType.FIELD, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
@interface LongTextConstraint {
    String message() default "Length of text exceeds ? characters." as String

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []
}