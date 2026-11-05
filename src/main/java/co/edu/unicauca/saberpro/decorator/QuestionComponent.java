package co.edu.unicauca.saberpro.decorator;

/** Componente común que comparten la pregunta básica y los decoradores. */
public interface QuestionComponent {
    String getEnunciado();

    double getPuntaje();

    String getDescripcion();
}
