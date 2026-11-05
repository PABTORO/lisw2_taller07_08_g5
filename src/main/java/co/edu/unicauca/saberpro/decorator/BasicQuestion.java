package co.edu.unicauca.saberpro.decorator;

import co.edu.unicauca.saberpro.domain.model.Question;

/** Componente concreto: envuelve una Question con un puntaje base. */
public class BasicQuestion implements QuestionComponent {
    private final Question pregunta;
    private final double puntajeBase;

    public BasicQuestion(Question pregunta, double puntajeBase) {
        this.pregunta = pregunta;
        this.puntajeBase = puntajeBase;
    }

    @Override
    public String getEnunciado() {
        return pregunta.getEnunciado();
    }

    @Override
    public double getPuntaje() {
        return puntajeBase;
    }

    @Override
    public String getDescripcion() {
        return "Pregunta #" + pregunta.getId() + " (" + pregunta.getClasificacion() + ")";
    }
}
