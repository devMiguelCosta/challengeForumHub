package oracleone.forumhub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oracleone.forumhub.dto.topico.AtualizarTopicoDTO;
import oracleone.forumhub.dto.topico.CriarTopicoDTO;
import oracleone.forumhub.dto.topico.TopicoCriadoDTO;
import oracleone.forumhub.dto.topico.TopicoListagemDTO;
import oracleone.forumhub.service.TopicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;


    @PostMapping
    public ResponseEntity<TopicoCriadoDTO> postCriarTopico(@RequestBody @Valid CriarTopicoDTO criarTopicoDTO,
                                                           UriComponentsBuilder uriBuilder) {
        TopicoCriadoDTO topicoCriadoDTO = topicoService.postTopico(criarTopicoDTO);

        URI location = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(topicoCriadoDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(topicoCriadoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoCriadoDTO> getTopicoId(@PathVariable Long id) {
        var topico = topicoService.getTopicoId(id);
        return ResponseEntity.ok().body(topico);

    }

    @GetMapping
    public ResponseEntity<Page<TopicoListagemDTO>> getListagemTopico(@PageableDefault(size = 3) Pageable paginas){
        var topicoPaginas = topicoService.getListagemTopico(paginas);
        return ResponseEntity.ok(topicoPaginas);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicoCriadoDTO> putAtualizaTopico(@PathVariable Long id,
                                                             @RequestBody @Valid AtualizarTopicoDTO dto){
        TopicoCriadoDTO topicoAtualizado = topicoService.putAtualizaTopico(id, dto);
        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id){
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }
}
