package oracleone.forumhub.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import oracleone.forumhub.service.EnumTopicoStatus;

public record AtualizarTopicoDTO(

        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull EnumTopicoStatus status,
        @NotNull Long autorId,
        @NotNull Long cursoId
) {
}
