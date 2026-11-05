package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

/**
 * Verdadero/Falso. El builder obliga a 4 opciones, así que aquí el valor
 * correcto se le pasa al evaluador en el constructor.
 */
public class TrueFalseEvaluator extends QuestionEvaluator {
    private final boolean valorCorrecto;

    public TrueFalseEvaluator(boolean valorCorrecto) {
        this.valorCorrecto = valorCorrecto;
    }

    @Override
    protected String recibirRespuesta(String respuesta) {
        return super.recibirRespuesta(respuesta).toUpperCase();
    }

    @Override
    protected boolean validarFormato(String respuesta) {
        return respuesta.matches("V|F|VERDADERO|FALSO");
    }

    @Override
    protected String descripcionFormato() {
        return "Responda V o F.";
    }

    @Override
    protected double calcularPuntaje(Question pregunta, String respuesta) {
        boolean eligioVerdadero = respuesta.startsWith("V");
        return eligioVerdadero == valorCorrecto ? 1.0 : 0.0;
    }
}
