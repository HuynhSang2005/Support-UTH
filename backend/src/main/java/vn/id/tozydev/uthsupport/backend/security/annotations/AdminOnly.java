package vn.id.tozydev.uthsupport.backend.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole(T(vn.id.tozydev.uthsupport.backend.models.enums.UserRole).STUDENT.name())")
public @interface AdminOnly {}