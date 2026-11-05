package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBuilderTest {
    private IQuestionBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new QuestionBuilder();
    }

    private IQuestionBuilder baseConOpciones(boolean... correctas) {
        builder.conContexto("Contexto").conEnunciado("Enunciado")
               .conJustificacion("Justificación").conClasificacion("Clasificación");
        for (int i = 0; i < correctas.length; i++) {
            builder.agregarOpcion("Opción " + i, correctas[i]);
        }
        return builder;
    }

    @Test
    void buildConCuatroOpcionesYUnaCorrectaCreaLaPregunta() {
        Question q = baseConOpciones(false, true, false, false).build();
        assertNotNull(q);
        assertEquals(4, q.getOpciones().size());
        assertEquals("Enunciado", q.getEnunciado());
    }

    @Test
    void buildConTresOpcionesLanzaExcepcion() {
        IQuestionBuilder b = baseConOpciones(true, false, false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void buildConCincoOpcionesLanzaExcepcion() {
        IQuestionBuilder b = baseConOpciones(true, false, false, false, false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void buildSinRespuestaCorrectaLanzaExcepcion() {
        IQuestionBuilder b = baseConOpciones(false, false, false, false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void buildConDosRespuestasCorrectasLanzaExcepcion() {
        IQuestionBuilder b = baseConOpciones(true, true, false, false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void elBuilderQuedaLimpioDespuesDeBuild() {
        baseConOpciones(true, false, false, false).build();
        // si no se hubiera reiniciado, esto tendría 5 opciones y fallaría
        Question otra = baseConOpciones(false, true, false, false).build();
        assertEquals(4, otra.getOpciones().size());
    }
}
