package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

/** En revisión: el revisor aprueba o la devuelve al autor con correcciones. */
public class ReviewState implements IQuestionState {

    @Override
    public String getNombre() {
        return "En Revisión";
    }

    @Override
    public void aprobar(Question q) {
        q.setEstado(new ApprovedState());
    }

    @Override
    public void solicitarCorrecciones(Question q) {
        q.setEstado(new DraftState());
    }
}
