package oracleone.forumhub.dto.topico;

import oracleone.forumhub.service.EnumTopicoStatus;

import java.time.LocalDateTime;

public record TopicoCriadoDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        EnumTopicoStatus status,
        String autor,
        String curso
) {
}
