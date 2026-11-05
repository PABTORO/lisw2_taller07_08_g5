package co.edu.unicauca.saberpro.adapter;

import java.util.List;

/**
 * Servicio externo (Adaptee). Simula una plataforma que entrega las
 * preguntas como cadenas crudas con el formato:
 * contexto|enunciado|opcion1;opcion2;opcion3;opcion4|indiceCorrecta|justificacion|clasificacion
 */
public class ExternalQuestionService {

    public List<String> obtenerPreguntasCrudas() {
        return List.of(
            "Una empresa maneja sus pedidos en una hoja de cálculo.|¿Qué patrón resuelve crear objetos complejos paso a paso?|Singleton;Builder;Observer;Proxy|1|Builder separa la construcción de la representación.|Diseño de software",
            "Un sistema de pagos debe notificar a varios módulos.|¿Qué patrón permite avisar a muchos objetos de un cambio?|Observer;Adapter;Facade;Prototype|0|Observer define una dependencia uno a muchos.|Diseño de software",
            // esta viene mal a propósito: solo trae 3 opciones
            "Contexto incompleto.|Pregunta con datos malos|A;B;C|0|Sin justificación|Pruebas"
        );
    }
}
