package au.edu.qut.SimpleInventoryApi.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import au.edu.qut.SimpleInventoryApi.model.Manufacturer;

public class ManufacturerValidator implements Validator {

    private final Validator manufacturerValidator;

    public ManufacturerValidator(Validator manufacturerValidator) {
        if (manufacturerValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is " +
                "required and must not be null.");
        }
        if (!manufacturerValidator.supports(Manufacturer.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must " +
                "support the validation of [Manufacturer] instances.");
        }
        this.manufacturerValidator = manufacturerValidator;
    }

    /**
     * This Validator validates Manufacturer instances, and any subclasses of Manufacturer too
     */
    public boolean supports(Class clazz) {
        return Manufacturer.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "homePage", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required");        
        Manufacturer manufacturer = (Manufacturer) target;
        try {
            errors.pushNestedPath("manufacturer");
            ValidationUtils.invokeValidator(this.manufacturerValidator, manufacturer.getName(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}  