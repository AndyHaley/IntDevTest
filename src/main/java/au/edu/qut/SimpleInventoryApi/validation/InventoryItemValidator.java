package au.edu.qut.SimpleInventoryApi.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import au.edu.qut.SimpleInventoryApi.model.InventoryItem;

public class InventoryItemValidator implements Validator {

    private final Validator inventoryItemValidator;

    public InventoryItemValidator(Validator inventoryItemValidator) {
        if (inventoryItemValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is " +
                "required and must not be null.");
        }
        if (!inventoryItemValidator.supports(InventoryItem.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must " +
                "support the validation of [Manufacturer] instances.");
        }
        this.inventoryItemValidator = inventoryItemValidator;
    }

    /**
     * This Validator validates Manufacturer instances, and any subclasses of Manufacturer too
     */
    public boolean supports(Class clazz) {
        return InventoryItem.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "release_date", "field.required");  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer", "field.required");        
        InventoryItem inventoryItem = (InventoryItem) target;
        try {
            errors.pushNestedPath("inventoryItem");
            ValidationUtils.invokeValidator(this.inventoryItemValidator, inventoryItem.getId(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}  