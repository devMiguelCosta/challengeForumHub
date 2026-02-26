package oracleone.forumhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oracleone.forumhub.service.EnumTopicoStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private EnumTopicoStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Topico(String titulo, String mensagem, LocalDateTime dataAtual, EnumTopicoStatus status, Usuario usuario, Curso curso){
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataAtual;
        this.status = status;
        this.autor = usuario;
        this.curso = curso;
    }

    public void atualizar(@NotBlank String titulo, @NotBlank String mensagem,
                          @NotNull Usuario autor,@NotNull Curso curso, @NotNull EnumTopicoStatus status) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.status = status;

    }
}
