package co.edu.unicauca.saberpro.state;

/** Aprobada: validada pedagógicamente, ya se puede usar en simulacros. */
public class ApprovedState implements IQuestionState {

    @Override
    public String getNombre() {
        return "Aprobada";
    }

    @Override
    public boolean esPublicable() {
        return true;
    }
}
