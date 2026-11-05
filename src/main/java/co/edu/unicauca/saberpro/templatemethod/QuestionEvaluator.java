package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

/**
 * Template Method. evaluar() es final y fija el orden del algoritmo:
 * 1) recibir respuesta, 2) validar formato, 3) calcular puntaje,
 * 4) generar retroalimentación. Las subclases solo rellenan los pasos
 * que cambian según el tipo de pregunta.
 */
public abstract class QuestionEvaluator {

    public final ResultadoEvaluacion evaluar(Question pregunta, String respuesta) {
        String limpia = recibirRespuesta(respuesta);
        if (!validarFormato(limpia)) {
            return new ResultadoEvaluacion(0, "Formato inválido. " + descripcionFormato(), false);
        }
        double puntaje = calcularPuntaje(pregunta, limpia);
        String mensaje = generarRetroalimentacion(pregunta, puntaje);
        return new ResultadoEvaluacion(puntaje, mensaje, true);
    }

    // paso 1 (implementación por defecto)
    protected String recibirRespuesta(String respuesta) {
        return respuesta == null ? "" : respuesta.trim();
    }

    // paso 2
    protected abstract boolean validarFormato(String respuesta);

    protected abstract String descripcionFormato();

    // paso 3
    protected abstract double calcularPuntaje(Question pregunta, String respuesta);

    // paso 4 (hook: las subclases pueden cambiarlo)
    protected String generarRetroalimentacion(Question pregunta, double puntaje) {
        if (puntaje >= 1.0) {
            return "¡Respuesta correcta!";
        }
        return "Respuesta incorrecta o incompleta. " + pregunta.getJustificacion();
    }
}
