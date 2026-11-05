package co.edu.unicauca.saberpro.templatemethod;

/** Resultado de evaluar una respuesta: puntaje, mensaje y si el formato era válido. */
public class ResultadoEvaluacion {
    private final double puntaje;
    private final String retroalimentacion;
    private final boolean formatoValido;

    public ResultadoEvaluacion(double puntaje, String retroalimentacion, boolean formatoValido) {
        this.puntaje = puntaje;
        this.retroalimentacion = retroalimentacion;
        this.formatoValido = formatoValido;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public boolean isFormatoValido() {
        return formatoValido;
    }
}
