package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

/**
 * Pendiente de revisión: ya se envió y el autor no puede tocarla (por eso no
 * sobreescribe los métodos de edición). Solo avanza cuando un revisor la toma.
 */
public class PendingReviewState implements IQuestionState {

    @Override
    public String getNombre() {
        return "Pendiente de Revisión";
    }

    @Override
    public void iniciarRevision(Question q) {
        q.setEstado(new ReviewState());
    }
}
