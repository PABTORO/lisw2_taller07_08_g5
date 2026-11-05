package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Context;
import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.domain.model.QuestionType;

import java.util.ArrayList;
import java.util.List;

/** Builder concreto de Question. */
public class QuestionBuilder implements IQuestionBuilder {
    private Context contexto;
    private String enunciado;
    private List<Option> opciones = new ArrayList<>();
    private String justificacion;
    private String clasificacion;
    private QuestionType tipo = QuestionType.MULTIPLE_CHOICE;

    @Override
    public IQuestionBuilder conContexto(String contexto) {
        this.contexto = new Context(contexto);
        return this;
    }

    @Override
    public IQuestionBuilder conEnunciado(String enunciado) {
        this.enunciado = enunciado;
        return this;
    }

    @Override
    public IQuestionBuilder agregarOpcion(String texto, boolean correcta) {
        this.opciones.add(new Option(texto, correcta));
        return this;
    }

    @Override
    public IQuestionBuilder conJustificacion(String justificacion) {
        this.justificacion = justificacion;
        return this;
    }

    @Override
    public IQuestionBuilder conClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
        return this;
    }

    @Override
    public IQuestionBuilder conTipo(QuestionType tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public Question build() {
        if (enunciado == null || enunciado.isBlank()) {
            throw new IllegalStateException("La pregunta necesita un enunciado");
        }
        if (opciones.size() != 4) {
            throw new IllegalStateException("La pregunta debe tener exactamente 4 opciones (tiene " + opciones.size() + ")");
        }
        long correctas = opciones.stream().filter(Option::isCorrecta).count();
        if (correctas != 1) {
            throw new IllegalStateException("La pregunta debe tener exactamente 1 respuesta correcta (tiene " + correctas + ")");
        }
        Question pregunta = new Question(contexto, enunciado, opciones, justificacion, clasificacion, tipo);
        reiniciar(); // el builder queda limpio para armar otra pregunta
        return pregunta;
    }

    private void reiniciar() {
        contexto = null;
        enunciado = null;
        opciones = new ArrayList<>();
        justificacion = null;
        clasificacion = null;
        tipo = QuestionType.MULTIPLE_CHOICE;
    }
}
