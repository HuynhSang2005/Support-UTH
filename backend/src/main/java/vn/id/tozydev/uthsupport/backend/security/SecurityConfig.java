package vn.id.tozydev.uthsupport.backend.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import vn.id.tozydev.uthsupport.backend.controllers.ApiPaths;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  @Value("${app.security.jwt.public-key}")
  private RSAPublicKey publicKey;

  @Value("${app.security.jwt.private-key}")
  private RSAPrivateKey privateKey;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .headers(
            header ->
                header.frameOptions(
                    HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // for H2 Console
        .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(
            oauth ->
                oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())));
    http.authorizeHttpRequests(
        request ->
            request //
                .requestMatchers(toH2Console())
                .permitAll()
                .requestMatchers(ApiPaths.AUTH + "/**")
                .permitAll()
                .requestMatchers("/swagger-ui*/**")
                .permitAll()
                .requestMatchers("/v3/api-docs*/**")
                .permitAll()
                .anyRequest()
                .authenticated());
    return http.build();
  }

  private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
    var authorityConverter = new JwtGrantedAuthoritiesConverter();
    authorityConverter.setAuthorityPrefix(UserRole.AUTHORITY_PREFIX);
    authorityConverter.setAuthoritiesClaimName(TokenService.ROLE_CLAIM);
    var converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(authorityConverter);
    return converter;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(publicKey).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    var jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    var jwtSet = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwtSet);
  }
}
