package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Product;

public class ProductValidator implements Validator {
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"field.required.product.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"field.required.description");
		Product product = (Product) target;
		/*if (product.getPages() == 0) {
			errors.rejectValue("pages", "field.required.pages");
		}*/
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}
}