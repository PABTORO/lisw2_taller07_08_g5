package co.edu.unicauca.saberpro.repository;

import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Repositorio en memoria (no usamos base de datos en este taller). */
public class QuestionRepository {
    private final Map<Integer, Question> preguntas = new LinkedHashMap<>();
    private int siguienteId = 1;

    public Question guardar(Question pregunta) {
        pregunta.setId(siguienteId++);
        preguntas.put(pregunta.getId(), pregunta);
        return pregunta;
    }

    public Optional<Question> buscarPorId(int id) {
        return Optional.ofNullable(preguntas.get(id));
    }

    public List<Question> listar() {
        return new ArrayList<>(preguntas.values());
    }
}
