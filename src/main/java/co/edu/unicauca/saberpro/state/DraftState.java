package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.List;

/** Borrador: estado inicial, el autor puede editar todo. */
public class DraftState implements IQuestionState {

    @Override
    public String getNombre() {
        return "Borrador";
    }

    @Override
    public void editarEnunciado(Question q, String nuevo) {
        q.aplicarEnunciado(nuevo);
    }

    @Override
    public void editarOpciones(Question q, List<Option> nuevas) {
        q.aplicarOpciones(nuevas);
    }

    @Override
    public void enviarARevision(Question q) {
        q.setEstado(new PendingReviewState());
    }
}
