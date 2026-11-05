package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDecoratorTest {
    private Question pregunta;

    @BeforeEach
    void setUp() {
        pregunta = new QuestionDirector(new QuestionBuilder()).construirPregunta(
                "Contexto", "Enunciado", new String[]{"A", "B", "C", "D"}, 0, "Just.", "Clasificación");
    }

    @Test
    void laPreguntaBasicaTieneElPuntajeBase() {
        assertEquals(1.0, new BasicQuestion(pregunta, 1.0).getPuntaje(), 0.0001);
    }

    @Test
    void altaDificultadMultiplicaElPuntaje() {
        QuestionComponent q = new HighDifficultyQuestion(new BasicQuestion(pregunta, 2.0), 1.5);
        assertEquals(3.0, q.getPuntaje(), 0.0001);
        assertTrue(q.getDescripcion().contains("Dificultad alta"));
    }

    @Test
    void sePuedenApilarDecoradores() {
        QuestionComponent q = new FeedbackQuestion(
                new HighDifficultyQuestion(new BasicQuestion(pregunta, 1.0), 2.0), "Explicación técnica");
        assertEquals(2.0, q.getPuntaje(), 0.0001);
        assertTrue(q.getDescripcion().contains("Dificultad alta"));
        assertTrue(q.getDescripcion().contains("Explicación técnica"));
        assertEquals("Enunciado", q.getEnunciado());
    }
}
