package br.com.alura.forum.resources;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicResource {

    @RequestMapping("/topicos")
    public List<TopicoDTO> list() {
        Topico topico = new Topico("Dúvida Spring", "Erro DevTool Instalation", new Curso("Spring 1", "Programação"));
        return TopicoDTO.converter(Arrays.asList(topico));
    }
}
