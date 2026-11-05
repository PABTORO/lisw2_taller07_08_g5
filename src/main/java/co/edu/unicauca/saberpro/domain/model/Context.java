package co.edu.unicauca.saberpro.domain.model;

/**
 * Contexto de la pregunta: el texto (caso, situación o lectura) que se le
 * presenta al estudiante antes del enunciado.
 */
public class Context {
    private final String texto;

    public Context(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
