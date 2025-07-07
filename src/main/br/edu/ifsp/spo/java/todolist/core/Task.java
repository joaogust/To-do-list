package main.br.edu.ifsp.spo.java.todolist.core;

import java.text.SimpleDateFormat;

public class Task {
    private String text; // Descrição da tarefa
    private Status status; // Não iniciada | Em andamento | Concluído
    private SimpleDateFormat expectedDate; // Data prevista de conclusão
    private SimpleDateFormat completionDate; // Data de conclusão
    private SimpleDateFormat changeDate; // Data de alteração
}
