package vn.id.tozydev.uthsupport.backend.models.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
  private String accessToken;
}
