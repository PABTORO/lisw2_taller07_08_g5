# Taller Integrado 07 y 08 – Banco de Preguntas Saber PRO

Laboratorio de Ingeniería de Software II – Universidad del Cauca.

| Patrón | Paquete | Clases principales |
|---|---|---|
| Builder | `builder` | `IQuestionBuilder`, `QuestionBuilder`, `QuestionDirector` |
| Adapter | `adapter` | `ExternalQuestionService`, `QuestionProvider`, `ExternalQuestionAdapter` |
| Facade | `facade` | `QuestionBankFacade` |
| State | `state` | `IQuestionState`, `DraftState`, `PendingReviewState`, `ReviewState`, `ApprovedState` |
| Template Method | `templatemethod` | `QuestionEvaluator`, `MultipleChoiceEvaluator`, `TrueFalseEvaluator`, `CaseStudyEvaluator` |
| Decorator | `decorator` | `QuestionComponent`, `BasicQuestion`, `QuestionDecorator`, `HighDifficultyQuestion`, `FeedbackQuestion` |

## Cómo ejecutar
```
mvn test                                   # pruebas JUnit 5
mvn compile exec:java -Dexec.mainClass=co.edu.unicauca.saberpro.ApplicationMain
```
(o ejecutar `ApplicationMain` desde el IDE).

## Integrantes
- Nombre 1
- Nombre 2
