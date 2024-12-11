package vn.id.tozydev.uthsupport.backend.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "UTH Support API", version = "1.0"))
@SecurityScheme(
    name = SwaggerConfig.SECURITY_SCHEME_NAME,
    type = SecuritySchemeType.HTTP,
    scheme = "Bearer",
    bearerFormat = "JWT")
public class SwaggerConfig {
  public static final String SECURITY_SCHEME_NAME = "UTHSupportAuth";
}
