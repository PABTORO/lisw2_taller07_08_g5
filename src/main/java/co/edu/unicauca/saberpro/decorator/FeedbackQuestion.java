package co.edu.unicauca.saberpro.decorator;

/** Decorador: adjunta una explicación técnica detallada a la pregunta. */
public class FeedbackQuestion extends QuestionDecorator {
    private final String explicacion;

    public FeedbackQuestion(QuestionComponent componente, String explicacion) {
        super(componente);
        this.explicacion = explicacion;
    }

    public String getExplicacion() {
        return explicacion;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Explicación: " + explicacion;
    }
}
