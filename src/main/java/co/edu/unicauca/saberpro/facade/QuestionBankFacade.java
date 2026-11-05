package co.edu.unicauca.saberpro.facade;

import co.edu.unicauca.saberpro.adapter.QuestionProvider;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.repository.QuestionRepository;
import co.edu.unicauca.saberpro.templatemethod.MultipleChoiceEvaluator;
import co.edu.unicauca.saberpro.templatemethod.ResultadoEvaluacion;
import co.edu.unicauca.saberpro.validator.QuestionValidator;

import java.util.List;

/**
 * Facade: único punto de entrada para la GUI o el Main. Esconde el
 * repositorio, el validador, el builder y el importador externo.
 */
public class QuestionBankFacade {
    private final QuestionRepository repositorio = new QuestionRepository();
    private final QuestionValidator validador = new QuestionValidator();
    private final QuestionProvider proveedorExterno;

    public QuestionBankFacade(QuestionProvider proveedorExterno) {
        this.proveedorExterno = proveedorExterno;
    }

    public Question crearPregunta(String contexto, String enunciado, String[] opciones,
                                  int indiceCorrecta, String justificacion, String clasificacion) {
        QuestionDirector director = new QuestionDirector(new QuestionBuilder());
        Question q = director.construirPregunta(contexto, enunciado, opciones, indiceCorrecta, justificacion, clasificacion);
        validador.validar(q);
        return repositorio.guardar(q);
    }

    /** Trae las preguntas del servicio externo y las guarda. Devuelve cuántas entraron. */
    public int importarExternas() {
        int importadas = 0;
        for (Question q : proveedorExterno.obtenerPreguntas()) {
            validador.validar(q);
            repositorio.guardar(q);
            importadas++;
        }
        return importadas;
    }

    public List<Question> listarPreguntas() {
        return repositorio.listar();
    }

    public Question buscarPregunta(int id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la pregunta con id " + id));
    }

    public void enviarARevision(int id) {
        buscarPregunta(id).enviarARevision();
    }

    public void iniciarRevision(int id) {
        buscarPregunta(id).iniciarRevision();
    }

    public void aprobar(int id) {
        buscarPregunta(id).aprobar();
    }

    public void solicitarCorrecciones(int id) {
        buscarPregunta(id).solicitarCorrecciones();
    }

    public ResultadoEvaluacion evaluarRespuesta(int id, String respuesta) {
        return new MultipleChoiceEvaluator().evaluar(buscarPregunta(id), respuesta);
    }
}
