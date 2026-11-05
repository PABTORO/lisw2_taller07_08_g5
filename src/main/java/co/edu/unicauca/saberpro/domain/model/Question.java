package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.state.DraftState;
import co.edu.unicauca.saberpro.state.IQuestionState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidad principal del banco. Además de guardar los datos, hace de
 * "Context" del patrón State: delega en el estado actual lo que se puede
 * (o no) hacer con la pregunta.
 */
public class Question {
    private int id;
    private final Context contexto;
    private String enunciado;
    private List<Option> opciones;
    private final String justificacion;
    private final String clasificacion;
    private final QuestionType tipo;
    private IQuestionState estado;

    public Question(Context contexto, String enunciado, List<Option> opciones,
                    String justificacion, String clasificacion, QuestionType tipo) {
        this.contexto = contexto;
        this.enunciado = enunciado;
        this.opciones = new ArrayList<>(opciones);
        this.justificacion = justificacion;
        this.clasificacion = clasificacion;
        this.tipo = tipo;
        this.estado = new DraftState(); // toda pregunta nace en borrador
    }

    /** Regla del negocio: exactamente 4 opciones y solo 1 correcta. */
    public static boolean opcionesValidas(List<Option> opciones) {
        long correctas = opciones.stream().filter(Option::isCorrecta).count();
        return opciones.size() == 4 && correctas == 1;
    }

    // ---- acciones que dependen del estado (se delegan) ----
    public void editarEnunciado(String nuevo) {
        estado.editarEnunciado(this, nuevo);
    }

    public void editarOpciones(List<Option> nuevas) {
        estado.editarOpciones(this, nuevas);
    }

    public void enviarARevision() {
        estado.enviarARevision(this);
    }

    public void iniciarRevision() {
        estado.iniciarRevision(this);
    }

    public void aprobar() {
        estado.aprobar(this);
    }

    public void solicitarCorrecciones() {
        estado.solicitarCorrecciones(this);
    }

    public boolean esPublicable() {
        return estado.esPublicable();
    }

    // ---- métodos que usan los estados para modificar la pregunta ----
    public void aplicarEnunciado(String nuevo) {
        this.enunciado = nuevo;
    }

    public void aplicarOpciones(List<Option> nuevas) {
        if (!opcionesValidas(nuevas)) {
            throw new IllegalArgumentException("Las opciones deben ser 4 y con una sola correcta");
        }
        this.opciones = new ArrayList<>(nuevas);
    }

    public void setEstado(IQuestionState estado) {
        this.estado = estado;
    }

    // ---- getters ----
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Context getContexto() {
        return contexto;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public List<Option> getOpciones() {
        return Collections.unmodifiableList(opciones);
    }

    public String getJustificacion() {
        return justificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public QuestionType getTipo() {
        return tipo;
    }

    public String getNombreEstado() {
        return estado.getNombre();
    }

    @Override
    public String toString() {
        return "Pregunta #" + id + " [" + estado.getNombre() + "] " + enunciado;
    }
}
