package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;

/**
 * Director: conoce el orden en que se arman los pasos. Así el resto del
 * sistema no repite la secuencia cada vez que crea una pregunta.
 */
public class QuestionDirector {
    private final IQuestionBuilder builder;

    public QuestionDirector(IQuestionBuilder builder) {
        this.builder = builder;
    }

    public Question construirPregunta(String contexto, String enunciado, String[] textosOpciones,
                                      int indiceCorrecta, String justificacion, String clasificacion) {
        builder.conContexto(contexto).conEnunciado(enunciado);
        for (int i = 0; i < textosOpciones.length; i++) {
            builder.agregarOpcion(textosOpciones[i], i == indiceCorrecta);
        }
        return builder.conJustificacion(justificacion)
                .conClasificacion(clasificacion)
                .build();
    }
}
