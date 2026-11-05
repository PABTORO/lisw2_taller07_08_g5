package co.edu.unicauca.saberpro.decorator;

/** Decorador base: guarda el componente y por defecto le delega todo. */
public abstract class QuestionDecorator implements QuestionComponent {
    protected final QuestionComponent componente;

    protected QuestionDecorator(QuestionComponent componente) {
        this.componente = componente;
    }

    @Override
    public String getEnunciado() {
        return componente.getEnunciado();
    }

    @Override
    public double getPuntaje() {
        return componente.getPuntaje();
    }

    @Override
    public String getDescripcion() {
        return componente.getDescripcion();
    }
}
