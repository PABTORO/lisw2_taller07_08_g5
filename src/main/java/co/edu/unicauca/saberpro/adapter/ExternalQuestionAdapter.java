package co.edu.unicauca.saberpro.adapter;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter: convierte las cadenas crudas del servicio externo en objetos
 * Question, usando el Builder para que también cumplan las validaciones.
 */
public class ExternalQuestionAdapter implements QuestionProvider {
    private final ExternalQuestionService servicio;
    private int omitidas;

    public ExternalQuestionAdapter(ExternalQuestionService servicio) {
        this.servicio = servicio;
    }

    @Override
    public List<Question> obtenerPreguntas() {
        omitidas = 0;
        List<Question> resultado = new ArrayList<>();
        for (String cruda : servicio.obtenerPreguntasCrudas()) {
            try {
                resultado.add(convertir(cruda));
            } catch (IllegalArgumentException | IllegalStateException e) {
                omitidas++; // reactivo mal formado, no entra al banco
            }
        }
        return resultado;
    }

    private Question convertir(String cruda) {
        String[] partes = cruda.split("\\|", -1);
        if (partes.length != 6) {
            throw new IllegalArgumentException("Formato inválido: " + cruda);
        }
        String[] opciones = partes[2].split(";");
        int correcta = Integer.parseInt(partes[3].trim());
        QuestionDirector director = new QuestionDirector(new QuestionBuilder());
        return director.construirPregunta(partes[0], partes[1], opciones, correcta, partes[4], partes[5]);
    }

    /** Cuántas preguntas se descartaron en la última importación. */
    public int getOmitidas() {
        return omitidas;
    }
}
