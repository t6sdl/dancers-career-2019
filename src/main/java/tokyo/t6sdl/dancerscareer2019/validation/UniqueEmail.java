package tokyo.t6sdl.dancerscareer2019.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueEmail {
	String message() default "{tokyo.t6sdl.dancerscareer2019.validation.UniqueEmail.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
