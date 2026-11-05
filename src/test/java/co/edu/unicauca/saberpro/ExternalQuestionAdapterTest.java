package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.adapter.ExternalQuestionAdapter;
import co.edu.unicauca.saberpro.adapter.ExternalQuestionService;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalQuestionAdapterTest {

    @Test
    void importaLasValidasYOmiteLasMalFormadas() {
        ExternalQuestionAdapter adaptador = new ExternalQuestionAdapter(new ExternalQuestionService());
        List<Question> preguntas = adaptador.obtenerPreguntas();
        assertEquals(2, preguntas.size());
        assertEquals(1, adaptador.getOmitidas());
    }

    @Test
    void laOpcionCorrectaSeConvierteBien() {
        ExternalQuestionAdapter adaptador = new ExternalQuestionAdapter(new ExternalQuestionService());
        Question primera = adaptador.obtenerPreguntas().get(0);
        assertTrue(primera.getOpciones().get(1).isCorrecta()); // Builder
    }
}
