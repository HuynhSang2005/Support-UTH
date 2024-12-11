package vn.id.tozydev.uthsupport.backend.swagger;

import static java.lang.annotation.ElementType.TYPE;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SecurityRequirement(name = SwaggerConfig.SECURITY_SCHEME_NAME)
public @interface RequireAuth {}
