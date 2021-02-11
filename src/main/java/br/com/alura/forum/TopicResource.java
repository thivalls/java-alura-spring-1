package br.com.alura.forum;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
//@RestController
public class TopicResource {

    @RequestMapping("/topicos")
    @ResponseBody
    public List<Topico> list() {
        Topico topico = new Topico("Duvida Laravel", "Erro livewire", new Curso("I still Love PHP", "Programação"));
        return Arrays.asList(topico, topico, topico);
    }
}
