package co.edu.unicauca.saberpro.adapter;

import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.List;

/** Interfaz que espera el Banco de Preguntas Saber PRO (Target del Adapter). */
public interface QuestionProvider {
    List<Question> obtenerPreguntas();
}
