package co.edu.unicauca.saberpro.decorator;

/** Decorador: marca la pregunta como de dificultad alta y multiplica el puntaje. */
public class HighDifficultyQuestion extends QuestionDecorator {
    private final double multiplicador;

    public HighDifficultyQuestion(QuestionComponent componente, double multiplicador) {
        super(componente);
        this.multiplicador = multiplicador;
    }

    @Override
    public double getPuntaje() {
        return super.getPuntaje() * multiplicador;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Dificultad alta (x" + multiplicador + ")";
    }
}
