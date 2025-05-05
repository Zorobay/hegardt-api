package se.hegardt.api.entities

import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import se.hegardt.api.enums.ConstraintIntLimits
import spock.lang.Specification
import spock.lang.Unroll

class LifeEventSpec extends Specification {

    private static ValidatorFactory validatorFactory
    private static Validator validator

    def setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    @Unroll
    def 'notes constraint'() {
        given:
            LifeEvent lifeEvent = new LifeEvent(notes: notes)
        when:
            Set<ConstraintViolation<LifeEvent>> violations = validator.validate(lifeEvent)
        then:
            violations.size() == expectedSize
        where:
            notes                                           | expectedSize
            ''                                              | 0
            'a' * 10                                        | 0
            'a' * ConstraintIntLimits.LONG_TEXT.value       | 0
            'a' * (ConstraintIntLimits.LONG_TEXT.value + 1) | 1
    }
}
