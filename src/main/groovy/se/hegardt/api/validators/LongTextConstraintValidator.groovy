package se.hegardt.api.validators

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import se.hegardt.api.enums.ConstraintIntLimits

class LongTextConstraintValidator implements ConstraintValidator<LongTextConstraint, String> {


    @Override
    boolean isValid(String value, ConstraintValidatorContext context) {
        return value ? value.size() <= ConstraintIntLimits.LONG_TEXT.value : true
    }
}
