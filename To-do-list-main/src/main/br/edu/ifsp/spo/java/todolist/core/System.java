package main.br.edu.ifsp.spo.java.todolist.core;

import java.util.ArrayList;
import java.util.List;

public class System {
    private List<Task> taskList = new ArrayList<>(); // Listapara as tarefas

    public void registerTask(String text) {
        Task task = new Task(text); // Registra nova tarefa com base no construtor em Task. Recebe um texto(descrição)
        taskList.add(task); // Adiciona a tarefa na lista
    }

    public boolean changeStatus(int i) {
        if (i >= 0 && i < taskList.size()) { // Posição dentro do tamanho da lista
            Task task = taskList.get(i); // Pega a tarefa na posição
            task.setCompleted(!task.isCompleted()); // NOT para alterar o status. Função de alterar status, recebe ele como true
            return true;
        }
        return false; // Apenas para se i(posição) for inválido
    }

    public List<Task> getAllTasks() { // Retorna todas as tarefas em uma copia da lista
        return new ArrayList<>(taskList);
    }

    public List<Task> getNotCompletedTasks() { // Retorna só as tarefas pendentes, que estão como não concluídas
        List<Task> result = new ArrayList<>(); // Lista suporte para guardar as tarefas filtradas
        for (Task task : taskList) { // Para cada tarefa na lista de tarefas
            if (task.isCompleted() == false) { // Verifica se o status está como falso
                result.add(task); // Se sim, adiciona na lista de tarefas não concluidas
            }
        }
        return result;
    }

    public List<Task> getCompletedTasks() { // Retorna só as tarefas que estão como concluídas
        List<Task> result = new ArrayList<>(); // Lista suporte para guardar as tarefas filtradas
        for (Task task : taskList) { // Para cada tarefa na lista de tarefas
            if (task.isCompleted() == true) { // Verifica se o status está como verdadeiro
                result.add(task); // Se sim, adiciona na lista de tarefas concluidas
            }
        }
        return result;
    }

}



