package co.edu.unicauca.saberpro.validator;

import co.edu.unicauca.saberpro.domain.model.Question;

/** Validaciones de negocio antes de guardar una pregunta en el banco. */
public class QuestionValidator {

    public void validar(Question q) {
        if (q.getContexto() == null || q.getContexto().getTexto().isBlank()) {
            throw new IllegalArgumentException("La pregunta debe tener contexto");
        }
        if (q.getClasificacion() == null || q.getClasificacion().isBlank()) {
            throw new IllegalArgumentException("La pregunta debe tener una clasificación");
        }
    }
}
