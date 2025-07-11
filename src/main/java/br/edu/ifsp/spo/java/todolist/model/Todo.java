package br.edu.ifsp.spo.java.todolist.model;

import java.time.LocalDateTime;

public class Todo {
    private int id;
    private String text; // Descrição da tarefa
    private Boolean completed; // Pendente | Concluída
     private LocalDateTime changeDate; // Data de alteração

    public Todo(String text) {
        this.text = text; // Texto para descrever a tarefa
        this.completed = false; // A tarefa começa não concluída
        this.changeDate = LocalDateTime.now(); // Função para definir data e hora de criação
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
        setChangeDate(LocalDateTime.now());
    }
    /*
    Optamos por atualizar a changeDate nos momentos em que o texto ou o status (Pendente / Concluído) são alterados.
     */

    public void setCompleted(Boolean status) {
        this.completed = status;
        setChangeDate(LocalDateTime.now());
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

}
