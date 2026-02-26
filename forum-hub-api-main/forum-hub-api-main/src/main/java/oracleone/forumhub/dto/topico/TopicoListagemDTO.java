package oracleone.forumhub.dto.topico;

import oracleone.forumhub.entity.Topico;
import oracleone.forumhub.service.EnumTopicoStatus;

import java.time.LocalDateTime;

public record TopicoListagemDTO(
        Long id,
        String titulo,
        LocalDateTime dataCriacao,
        EnumTopicoStatus status,
        String autor,
        String curso
) {
    public TopicoListagemDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
