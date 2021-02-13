package br.com.alura.forum.resources;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.dto.TopicoFullDTO;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.form.TopicoFormUpdate;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicResource {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> all(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoFullDTO> show(@PathVariable Long id) {
        Topico topico = topicoRepository.getOne(id);
        return ResponseEntity.ok().body(new TopicoFullDTO(topico));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> store(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        if (form != null) {
            Topico topico = form.converter(cursoRepository);
            topicoRepository.save(topico);

            URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(uri).body(new TopicoDTO(topico));
        }
        return null;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoFullDTO> update(@PathVariable Long id, @RequestBody @Valid TopicoFormUpdate topico) {
        Topico topicoCarregado = topicoRepository.getOne(id);
        topicoCarregado.setTitulo(topico.getTitulo());
        topicoCarregado.setMensagem(topico.getMensagem());
        return ResponseEntity.ok().body(new TopicoFullDTO(topicoCarregado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
