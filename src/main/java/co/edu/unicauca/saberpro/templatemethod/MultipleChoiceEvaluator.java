package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

/** Selección múltiple: la respuesta es una letra de la A a la D. */
public class MultipleChoiceEvaluator extends QuestionEvaluator {

    @Override
    protected String recibirRespuesta(String respuesta) {
        return super.recibirRespuesta(respuesta).toUpperCase();
    }

    @Override
    protected boolean validarFormato(String respuesta) {
        return respuesta.matches("[A-D]");
    }

    @Override
    protected String descripcionFormato() {
        return "Responda con una letra entre A y D.";
    }

    @Override
    protected double calcularPuntaje(Question pregunta, String respuesta) {
        int indice = respuesta.charAt(0) - 'A';
        return pregunta.getOpciones().get(indice).isCorrecta() ? 1.0 : 0.0;
    }
}
