package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.templatemethod.CaseStudyEvaluator;
import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.ResultadoEvaluacion;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionEvaluatorTest {
    private Question pregunta;

    @BeforeEach
    void setUp() {
        // la opción correcta es la B (índice 1)
        pregunta = new QuestionDirector(new QuestionBuilder()).construirPregunta(
                "Contexto", "Enunciado", new String[]{"Op1", "Op2", "Op3", "Op4"}, 1,
                "Porque B es la correcta", "Clasificación");
    }

    @Test
    void seleccionMultipleRespuestaCorrecta() {
        ResultadoEvaluacion r = new MultipleChoiceEvaluator().evaluar(pregunta, "B");
        assertTrue(r.isFormatoValido());
        assertEquals(1.0, r.getPuntaje(), 0.0001);
    }

    @Test
    void seleccionMultipleAceptaMinusculasYEspacios() {
        ResultadoEvaluacion r = new MultipleChoiceEvaluator().evaluar(pregunta, "  b ");
        assertEquals(1.0, r.getPuntaje(), 0.0001);
    }

    @Test
    void seleccionMultipleRespuestaIncorrectaIncluyeJustificacion() {
        ResultadoEvaluacion r = new MultipleChoiceEvaluator().evaluar(pregunta, "A");
        assertEquals(0.0, r.getPuntaje(), 0.0001);
        assertTrue(r.getRetroalimentacion().contains("Porque B es la correcta"));
    }

    @Test
    void seleccionMultipleFormatoInvalido() {
        ResultadoEvaluacion r = new MultipleChoiceEvaluator().evaluar(pregunta, "Z");
        assertFalse(r.isFormatoValido());
        assertEquals(0.0, r.getPuntaje(), 0.0001);
    }

    @Test
    void seleccionMultipleRespuestaNulaEsInvalida() {
        assertFalse(new MultipleChoiceEvaluator().evaluar(pregunta, null).isFormatoValido());
    }

    @Test
    void verdaderoFalso() {
        assertEquals(1.0, new TrueFalseEvaluator(true).evaluar(pregunta, "V").getPuntaje(), 0.0001);
        assertEquals(0.0, new TrueFalseEvaluator(true).evaluar(pregunta, "F").getPuntaje(), 0.0001);
        assertEquals(1.0, new TrueFalseEvaluator(false).evaluar(pregunta, "falso").getPuntaje(), 0.0001);
        assertFalse(new TrueFalseEvaluator(true).evaluar(pregunta, "tal vez").isFormatoValido());
    }

    @Test
    void casoDeEstudioDaPuntajeParcial() {
        CaseStudyEvaluator ev = new CaseStudyEvaluator(List.of("acoplamiento", "pruebas"));
        ResultadoEvaluacion r = ev.evaluar(pregunta, "Se reduce el acoplamiento entre clases.");
        assertEquals(0.5, r.getPuntaje(), 0.0001);
    }

    @Test
    void casoDeEstudioRespuestaMuyCortaEsInvalida() {
        CaseStudyEvaluator ev = new CaseStudyEvaluator(List.of("acoplamiento"));
        assertFalse(ev.evaluar(pregunta, "no sé").isFormatoValido());
    }
}
