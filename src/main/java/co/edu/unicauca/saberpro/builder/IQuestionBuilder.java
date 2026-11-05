package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.domain.model.QuestionType;

/** Interfaz Builder: cada paso devuelve el mismo builder para poder encadenar. */
public interface IQuestionBuilder {

    IQuestionBuilder conContexto(String contexto);

    IQuestionBuilder conEnunciado(String enunciado);

    IQuestionBuilder agregarOpcion(String texto, boolean correcta);

    IQuestionBuilder conJustificacion(String justificacion);

    IQuestionBuilder conClasificacion(String clasificacion);

    IQuestionBuilder conTipo(QuestionType tipo);

    /** Valida (4 opciones y 1 correcta) y entrega la pregunta. */
    Question build();
}
