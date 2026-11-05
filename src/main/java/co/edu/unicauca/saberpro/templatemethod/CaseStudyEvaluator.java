package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.List;

/**
 * Caso de estudio: respuesta de texto libre. El puntaje es la fracción de
 * palabras clave que aparecen en la respuesta (puntaje parcial entre 0 y 1).
 */
public class CaseStudyEvaluator extends QuestionEvaluator {
    private static final int LONGITUD_MINIMA = 15;
    private final List<String> palabrasClave;

    public CaseStudyEvaluator(List<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    @Override
    protected boolean validarFormato(String respuesta) {
        return respuesta.length() >= LONGITUD_MINIMA;
    }

    @Override
    protected String descripcionFormato() {
        return "La respuesta debe tener al menos " + LONGITUD_MINIMA + " caracteres.";
    }

    @Override
    protected double calcularPuntaje(Question pregunta, String respuesta) {
        String texto = respuesta.toLowerCase();
        long encontradas = palabrasClave.stream()
                .filter(p -> texto.contains(p.toLowerCase()))
                .count();
        return (double) encontradas / palabrasClave.size();
    }
}
