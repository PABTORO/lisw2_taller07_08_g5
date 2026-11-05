package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionStateTest {
    private Question pregunta;

    @BeforeEach
    void setUp() {
        pregunta = new QuestionDirector(new QuestionBuilder()).construirPregunta(
                "Contexto", "Enunciado original", new String[]{"A", "B", "C", "D"}, 0,
                "Justificación", "Clasificación");
    }

    @Test
    void laPreguntaNaceEnBorrador() {
        assertEquals("Borrador", pregunta.getNombreEstado());
    }

    @Test
    void enBorradorSePuedeEditarElEnunciado() {
        pregunta.editarEnunciado("Enunciado nuevo");
        assertEquals("Enunciado nuevo", pregunta.getEnunciado());
    }

    @Test
    void flujoCompletoBorradorHastaAprobada() {
        pregunta.enviarARevision();
        assertEquals("Pendiente de Revisión", pregunta.getNombreEstado());
        pregunta.iniciarRevision();
        assertEquals("En Revisión", pregunta.getNombreEstado());
        pregunta.aprobar();
        assertEquals("Aprobada", pregunta.getNombreEstado());
        assertTrue(pregunta.esPublicable());
    }

    @Test
    void pendienteDeRevisionBloqueaLaEdicionDelAutor() {
        pregunta.enviarARevision();
        assertThrows(IllegalStateException.class, () -> pregunta.editarEnunciado("Otro"));
        assertEquals("Enunciado original", pregunta.getEnunciado());
    }

    @Test
    void noSePuedeAprobarDirectamenteDesdeBorrador() {
        assertThrows(IllegalStateException.class, () -> pregunta.aprobar());
    }

    @Test
    void solicitarCorreccionesDevuelveALaPreguntaABorrador() {
        pregunta.enviarARevision();
        pregunta.iniciarRevision();
        pregunta.solicitarCorrecciones();
        assertEquals("Borrador", pregunta.getNombreEstado());
        assertFalse(pregunta.esPublicable());
    }

    @Test
    void unaPreguntaAprobadaYaNoSePuedeEditar() {
        pregunta.enviarARevision();
        pregunta.iniciarRevision();
        pregunta.aprobar();
        assertThrows(IllegalStateException.class, () -> pregunta.editarEnunciado("Otro"));
    }
}
