package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.adapter.ExternalQuestionAdapter;
import co.edu.unicauca.saberpro.adapter.ExternalQuestionService;
import co.edu.unicauca.saberpro.decorator.BasicQuestion;
import co.edu.unicauca.saberpro.decorator.FeedbackQuestion;
import co.edu.unicauca.saberpro.decorator.HighDifficultyQuestion;
import co.edu.unicauca.saberpro.decorator.QuestionComponent;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.facade.QuestionBankFacade;
import co.edu.unicauca.saberpro.templatemethod.CaseStudyEvaluator;
import co.edu.unicauca.saberpro.templatemethod.ResultadoEvaluacion;
import co.edu.unicauca.saberpro.templatemethod.TrueFalseEvaluator;

import java.util.List;

/** Demo del Banco de Preguntas Saber PRO. Solo habla con la Facade. */
public class ApplicationMain {

    public static void main(String[] args) {
        ExternalQuestionAdapter adaptador = new ExternalQuestionAdapter(new ExternalQuestionService());
        QuestionBankFacade banco = new QuestionBankFacade(adaptador);

        System.out.println("=== 1. Builder + Facade: crear una pregunta ===");
        Question q = banco.crearPregunta(
                "Un equipo de desarrollo tiene una clase con un constructor de 10 parámetros.",
                "¿Qué patrón creacional simplifica construir ese objeto?",
                new String[]{"Singleton", "Builder", "Prototype", "Factory Method"},
                1,
                "Builder arma el objeto por pasos y evita constructores gigantes.",
                "Diseño de software");
        System.out.println(q);

        System.out.println("\n=== 2. Adapter: importar preguntas externas ===");
        int importadas = banco.importarExternas();
        System.out.println("Importadas: " + importadas + " | Omitidas por formato: " + adaptador.getOmitidas());
        banco.listarPreguntas().forEach(System.out::println);

        System.out.println("\n=== 3. State: ciclo de vida de la pregunta #1 ===");
        banco.enviarARevision(1);
        System.out.println(banco.buscarPregunta(1));
        try {
            banco.buscarPregunta(1).editarEnunciado("Intento de edición");
        } catch (IllegalStateException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }
        banco.iniciarRevision(1);
        banco.aprobar(1);
        System.out.println(banco.buscarPregunta(1) + " | publicable: " + banco.buscarPregunta(1).esPublicable());

        System.out.println("\n=== 4. Template Method: evaluar respuestas ===");
        imprimir("Selección múltiple, 'B'", banco.evaluarRespuesta(1, "B"));
        imprimir("Selección múltiple, 'A'", banco.evaluarRespuesta(1, "A"));
        imprimir("Selección múltiple, 'Z'", banco.evaluarRespuesta(1, "Z"));
        imprimir("Verdadero/Falso, 'V'", new TrueFalseEvaluator(true).evaluar(q, "V"));
        imprimir("Caso de estudio", new CaseStudyEvaluator(List.of("acoplamiento", "patrón", "pruebas"))
                .evaluar(q, "Reduce el acoplamiento y facilita las pruebas."));

        System.out.println("\n=== 5. Decorator: dificultad y explicación ===");
        QuestionComponent decorada = new FeedbackQuestion(
                new HighDifficultyQuestion(new BasicQuestion(q, 1.0), 1.5),
                "Builder separa la construcción de la representación del objeto.");
        System.out.println(decorada.getDescripcion());
        System.out.println("Puntaje final: " + decorada.getPuntaje());
    }

    private static void imprimir(String titulo, ResultadoEvaluacion r) {
        System.out.println(titulo + " -> puntaje " + r.getPuntaje() + " | " + r.getRetroalimentacion());
    }
}
