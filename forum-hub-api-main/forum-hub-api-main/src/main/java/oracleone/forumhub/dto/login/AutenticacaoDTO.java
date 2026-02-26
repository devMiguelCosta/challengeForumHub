package oracleone.forumhub.dto.login;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
        @NotBlank String email,
        @NotBlank String senha
) {
}
