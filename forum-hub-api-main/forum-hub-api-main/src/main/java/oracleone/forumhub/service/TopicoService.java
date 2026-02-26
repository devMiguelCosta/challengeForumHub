package oracleone.forumhub.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oracleone.forumhub.dto.topico.AtualizarTopicoDTO;
import oracleone.forumhub.dto.topico.CriarTopicoDTO;
import oracleone.forumhub.dto.topico.TopicoCriadoDTO;
import oracleone.forumhub.dto.topico.TopicoListagemDTO;
import oracleone.forumhub.entity.Curso;
import oracleone.forumhub.entity.Topico;
import oracleone.forumhub.entity.Usuario;
import oracleone.forumhub.repository.CursoRepository;
import oracleone.forumhub.repository.TopicoRepository;
import oracleone.forumhub.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;


    @Transactional
    public TopicoCriadoDTO postTopico(CriarTopicoDTO DTO) {

        //Checando se já existe um topico similar no banco de dados
        if (topicoRepository.existsByTituloAndMensagem(DTO.titulo(), DTO.mensagem())) {
            throw new ResponseStatusException(
                    CONFLICT,
                    "Já existe um tópico com o mesmo título e mensagem"
            );
        }

        Usuario usuario = usuarioRepository.findById(DTO.autorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Autor não encontrado"));

        Curso curso = cursoRepository.findById(DTO.cursoId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Curso não encontrado"));


        var dataAtual = LocalDateTime.now();
        var status = EnumTopicoStatus.NAO_RESPONDIDO;
        var  novoTopico = new Topico(DTO.titulo(), DTO.mensagem(), dataAtual, status, usuario, curso);

        Topico topico = topicoRepository.save(novoTopico);

        return new TopicoCriadoDTO(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }

    @Transactional
    public TopicoCriadoDTO getTopicoId(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tópico não encontrado"));
        return new TopicoCriadoDTO(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }

    @Transactional
    public Page<TopicoListagemDTO> getListagemTopico(Pageable paginas) {
        return topicoRepository.findAll(paginas).map(TopicoListagemDTO::new);
    }

    @Transactional
    public TopicoCriadoDTO putAtualizaTopico(Long id, @Valid AtualizarTopicoDTO dto) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tópico não encontrado"));

        if (topicoRepository.existsByTituloAndMensagemAndIdNot(dto.titulo(), dto.mensagem(), id)) {
            throw new ResponseStatusException(CONFLICT, "Já existe um tópico com o mesmo título e mensagem");
        }

        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Autor não encontrado"));

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Curso não encontrado"));

        topico.atualizar(dto.titulo(), dto.mensagem(), autor, curso, dto.status());

        return new TopicoCriadoDTO(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }

    @Transactional
    public void deleteTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Tópico não encontrado");
        }
        topicoRepository.deleteById(id);
    }
}
