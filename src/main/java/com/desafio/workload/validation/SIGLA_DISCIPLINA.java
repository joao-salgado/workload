package com.desafio.workload.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "([a-zA-Z]{2}\\d{2}[a-zA-Z]{1})?")
public @interface SIGLA_DISCIPLINA {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "A sigla deve seguir o padrão XX99X";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
