package br.edu.ifsp.spo.java.todolist.core;

import br.edu.ifsp.spo.java.todolist.model.Todo;
import br.edu.ifsp.spo.java.todolist.ui.JavaFXUI;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoSystemTest {
    TodoSystem system = new TodoSystem(new JavaFXUI());

    @Test
    void criarTarefa() {
        // Setup
        Todo todoTest = new Todo("test");

        // Execute
        system.getRepository().create(todoTest);

        // Assert
        List<Todo> todos = system.getRepository().findAll();
        boolean teste = todos.contains(todoTest);
        assertTrue(teste);

        system.getRepository().delete(todoTest);
    }

    @Test
    void excluirTarefa() {
        // Setup
        Todo todoTest = new Todo("test");

        // Execute
        system.getRepository().create(todoTest);
        system.getRepository().delete(todoTest);

        // Assert
        List<Todo> todos = system.getRepository().findAll();
        boolean teste = todos.contains(todoTest);
        assertFalse(teste);
    }

    @Test
    void alterarTarefa() {
        // Setup
        Todo todoTest = new Todo("test a");
        system.getRepository().create(todoTest);

        // Execute
        todoTest.setText("test b"); // altero o texto da tarefa
        todoTest.setCompleted(true); // altero o status da tarefa
        system.getRepository().update(todoTest); // atualiza no banco de dados

        // Assert
        List<Todo> todos = system.getRepository().findAll();
        boolean teste = todos.contains(todoTest);
        /*
         O contains utiliza o metodo equals (sobrescrito) que compara todos os atributos e verifica se está no banco de dados. Logo, se foi alterado no banco de dados o teste deverá resultar true.
         */
        assertTrue(teste);

        system.getRepository().delete(todoTest);
    }

    @Test
    void tarefasFinalizadas() {
        /*
        Esse teste verifica se as tarefas pendentes aparecem somente na lista do metodo findByStatus(true).
         */

        // Setup
        Todo todoTest = new Todo("test");
        system.getRepository().create(todoTest);

        // Execute
        todoTest.setCompleted(true); // atualiza como tarefa concluída
        system.getRepository().update(todoTest); // altera no banco de dados

        // Assert
        List<Todo> todos = system.getRepository().findByStatus(false); // lista de tarefas não concluídas
        boolean teste = todos.contains(todoTest);
        assertFalse(teste);

        todos = system.getRepository().findByStatus(true); // lista de tarefas concluídas
        teste = todos.contains(todoTest);
        assertTrue(teste);

        system.getRepository().delete(todoTest);

    }

    @Test
    void tarefasPendentes() {
        /*
        Esse teste verifica se as tarefas pendentes aparecem somente na lista do metodo findByStatus(false).
         */

        // Setup
        Todo todoTest = new Todo("test");
        system.getRepository().create(todoTest);

        // Execute
        // todoTest.setCompleted(false);
        // system.getRepository().update(todoTest);
        /*
         Essas linhas de código comentadas acima não são necessárias, uma vez que o tarefa e instanciada como falsa pelo construtor.
         */

        // Assert
        List<Todo> todos = system.getRepository().findByStatus(false); // lista de tarefas não concluídas
        boolean teste = todos.contains(todoTest);
        assertTrue(teste);

        todos = system.getRepository().findByStatus(true); // lista de tarefas concluídas
        teste = todos.contains(todoTest);
        assertFalse(teste);

        system.getRepository().delete(todoTest);

    }
}
