package br.com.alura.forum.form;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;

public class TopicoForm {
    private String titulo;
    private String mensagem;
    private String cursoNome;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(cursoNome);
        System.out.println(">>>" + curso);
        return new Topico(titulo, mensagem, curso);
    }

    @Override
    public String toString() {
        return "TopicoForm{" +
                "titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", cursoNome='" + cursoNome + '\'' +
                '}';
    }
}
