package main.br.edu.ifsp.spo.java.todolist.ui;

public class InterfaceSystemUI implements SystemUI{
    public void start() {
        while (true) {                      // Loop infinito até escolher “sair”
            showMenu();                     // Exibe opções
            String op = in.nextLine().trim(); // Lê escolha do usuário

            switch (op) {                   // Decide ação
                case "1" -> addTask();      // Criar nova tarefa
                case "2" -> toggleTask();   // Concluir/reabrir
                case "3" -> show(service.listAll());       // Todas
                case "4" -> show(service.listPending());   // Pendentes
                case "5" -> show(service.listCompleted()); // Concluídas
                case "0" -> {               // Sair
                    System.out.println("Até logo!");
                    return;                 // Encerra método → encerra app
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }


}
