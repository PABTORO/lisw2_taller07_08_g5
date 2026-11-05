package co.edu.unicauca.saberpro.domain.model;

/** Una opción de respuesta de la pregunta. */
public class Option {
    private final String texto;
    private final boolean correcta;

    public Option(String texto, boolean correcta) {
        this.texto = texto;
        this.correcta = correcta;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    @Override
    public String toString() {
        return texto;
    }
}
