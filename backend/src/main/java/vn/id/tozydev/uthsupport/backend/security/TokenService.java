package vn.id.tozydev.uthsupport.backend.security;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import vn.id.tozydev.uthsupport.backend.models.entities.User;

@Service
public class TokenService {
  public static final String ROLE_CLAIM = "role";

  private final JwtEncoder jwtEncoder;

  @Value("${app.security.jwt.expiration-seconds}")
  private Long expirationSeconds;

  @Value("${app.security.jwt.issuer}")
  private String issuer;

  public TokenService(JwtEncoder jwtEncoder) {
    this.jwtEncoder = jwtEncoder;
  }

  public String create(User user) {
    var now = Instant.now();
    var claims =
        JwtClaimsSet.builder()
            .issuer(issuer)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expirationSeconds))
            .subject(user.getUsername())
            .claim(ROLE_CLAIM, user.getRole().name())
            .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
