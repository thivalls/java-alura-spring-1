package br.com.alura.forum.resources;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.dto.TopicoFullDTO;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.form.TopicoFormUpdate;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicResource {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public Page<TopicoDTO> all(@RequestParam(required = false) String nomeCurso, @RequestParam int page, @RequestParam int size) {
        Pageable pagination = PageRequest.of(page,size);

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(pagination);
            return TopicoDTO.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, pagination);
            return TopicoDTO.converter(topicos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoFullDTO> show(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok().body(new TopicoFullDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> store(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        if (form != null) {
            Topico topico = form.converter(cursoRepository);
            topicoRepository.save(topico);

            URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(uri).body(new TopicoDTO(topico));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoFullDTO> update(@PathVariable Long id, @RequestBody @Valid TopicoFormUpdate topico) {
        Optional<Topico> topicoCarregado = topicoRepository.findById(id);
        if(topicoCarregado.isPresent()){
            topicoCarregado.get().setTitulo(topico.getTitulo());
            topicoCarregado.get().setMensagem(topico.getMensagem());
            return ResponseEntity.ok().body(new TopicoFullDTO(topicoCarregado.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
