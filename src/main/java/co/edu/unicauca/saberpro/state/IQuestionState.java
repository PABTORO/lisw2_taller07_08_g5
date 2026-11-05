package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;

import java.util.List;

/**
 * Interfaz del patrón State. Por defecto ninguna acción está permitida y
 * cada estado concreto sobreescribe solo lo que SÍ puede hacer. Así no hace
 * falta ningún if/else ni switch para validar las transiciones.
 */
public interface IQuestionState {

    String getNombre();

    default void editarEnunciado(Question q, String nuevo) {
        throw noPermitido("editar el enunciado de");
    }

    default void editarOpciones(Question q, List<Option> nuevas) {
        throw noPermitido("editar las opciones de");
    }

    default void enviarARevision(Question q) {
        throw noPermitido("enviar a revisión");
    }

    default void iniciarRevision(Question q) {
        throw noPermitido("iniciar la revisión de");
    }

    default void aprobar(Question q) {
        throw noPermitido("aprobar");
    }

    default void solicitarCorrecciones(Question q) {
        throw noPermitido("solicitar correcciones a");
    }

    default boolean esPublicable() {
        return false;
    }

    private IllegalStateException noPermitido(String accion) {
        return new IllegalStateException("No se puede " + accion + " una pregunta en estado " + getNombre());
    }
}
