package main.br.edu.ifsp.spo.java.todolist.core;

import java.awt.*;
import java.time.LocalDateTime;

public class Task {

    private int idTask; // id da tarefa
    private String text; // Descrição da tarefa
    private boolean completed; // Tarefa concluída ou não
    private LocalDateTime changeDate; // Data de alteração

    // Deixei comentado pq está diferente do enunciado no Moodle. Podemos implementar em outros commits
    // private SimpleDateFormat expectedDate; // Data prevista de conclusão
    // private SimpleDateFormat completionDate; // Data de conclusão
    // private Status status; // Não iniciada | Em andamento | Concluído

    public Task(String text) {  // Construtor para criar tareas no sstema
        this.text = text;  // Texto para descrever a tarefa
        this.completed = false; // Tarefa começa não concluída
        this.changeDate = LocalDateTime.now(); // Função para definir data e hora de criação
    }

    public Task(int idTask, String text, boolean completed, LocalDateTime changeDate) { // Construtor para o bdd
        this.idTask = idTask;
        this.text = text;
        this.completed = completed;
        this.changeDate = changeDate;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
        this.changeDate = LocalDateTime.now(); // Atualiza a data quando altera o texto
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        if (completed != this.completed) { // Só muda para concluido se valor passado for diferente do atual
            this.completed = completed; // Muda para concluido
            this.changeDate = LocalDateTime.now(); // Muda a data ao alterar
        }
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

}

//Uma tarefa deve conter os seguintes campos:
//- Texto (String) (descrevendo a tarefa a ser executada)
//- Concluído (Booleano) (indicando se a tarefa foi concluída ou não
//- Data de Alteração (indicando a data de alteração de status da tarefa)


// Representa uma tarefa (domínio da aplicação)