package br.com.alura.forum.repositories;

import br.com.alura.forum.modelo.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ActiveProfiles("test")
class CursoRepositoryTests {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void deveEncontrarCursoPorNome() {
        String cursoNome = "HTML 5";
        Curso newCurso = new Curso();
        newCurso.setNome("HTML 5");
        newCurso.setCategoria("Programacao");
        em.persist(newCurso);

        Curso curso = cursoRepository.findByNome(cursoNome);
        Assertions.assertNotNull(curso);
        Assertions.assertEquals("HTML 5", curso.getNome());
    }

    @Test
    void naoDeveEncontrarCursoPorNome() {
        String cursoNome = "HTML";
        Curso curso = cursoRepository.findByNome(cursoNome);
        Assertions.assertNull(curso);
    }
}