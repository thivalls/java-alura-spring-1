package br.com.alura.forum.dto;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

//    public TopicoDTO() {
//
//    }

    public TopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//    public void setMensagem(String mensagem) {
//        this.mensagem = mensagem;
//    }
//
//    public void setDataCriacao(LocalDateTime dataCriacao) {
//        this.dataCriacao = dataCriacao;
//    }

    public static Page<TopicoDTO> converter(Page<Topico> topicos) {
        return topicos.map(TopicoDTO::new);
    }

//    public static List<TopicoDTO> converterClassic(List<Topico> topicos) {
//        ArrayList<TopicoDTO> response = new ArrayList<>();
//        for (Topico tdto : topicos) {
//            TopicoDTO newDTO = new TopicoDTO();
//            newDTO.setId(tdto.getId());
//            newDTO.setTitulo(tdto.getTitulo());
//            newDTO.setMensagem(tdto.getMensagem());
//            newDTO.setDataCriacao(tdto.getDataCriacao());
//            response.add(newDTO);
//        }
//        return response;
//    }
}
