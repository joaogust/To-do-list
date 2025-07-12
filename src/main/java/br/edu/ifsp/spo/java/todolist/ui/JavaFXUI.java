package br.edu.ifsp.spo.java.todolist.ui;

import br.edu.ifsp.spo.java.todolist.core.TodoSystem;
import br.edu.ifsp.spo.java.todolist.model.Todo;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class JavaFXUI extends Application implements SystemUI{
    private TodoSystem system;
    TableView<Todo> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.system = new TodoSystem(this);

        // Criação da tabela
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        TableColumn<Todo, String> textColumn = new TableColumn<>("Tarefa");
        textColumn.setCellValueFactory(todo -> new SimpleStringProperty(todo.getValue().getText()));
        textColumn.setPrefWidth(350);

        TableColumn<Todo, String> statsColumn = new TableColumn<>("Status");
        statsColumn.setCellValueFactory(todo -> new SimpleStringProperty(todo.getValue().isCompleted() ? "[  X  ]" : "[      ]"));
        statsColumn.setPrefWidth(45);

        TableColumn<Todo, String> dateColumn = new TableColumn<>("Data de alteração");
        dateColumn.setCellValueFactory(todo -> new SimpleStringProperty(todo.getValue().getChangeDate().toString()));
        dateColumn.setPrefWidth(200);

        table.getColumns().addAll(statsColumn, textColumn, dateColumn);


        // Botões do menu
        Button allTodos = new Button("Listar todas as tarefas");
        allTodos.setOnAction(e -> system.menuAllTodos());

        Button completedTodos = new Button("Listar tarefas concluídas");
        completedTodos.setOnAction(e -> system.menuCompletedTodos());

        Button pendingTodos = new Button("Listar tarefas pendentes");
        pendingTodos.setOnAction(e -> system.menuPendingTodos());

        Button addTodo = new Button("Adicionar tarefa");
        addTodo.setOnAction(e -> createTodo());

        Button removeTodo = new Button("Remover tarefa");
        removeTodo.setOnAction(e -> {
            Todo selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                system.deleteTodo(selected);
                system.menuAllTodos();
            } else {
                showMessage("Selecione uma tarefa para excluir!");
            }
        });

        Button changeTodo = new Button("Alterar tarefa");
        changeTodo.setOnAction(e -> changeTodo());

        // Espaços
        Label space1 = new Label();
        space1.setStyle("-fx-padding: 50");
        Label space2 = new Label();
        space2.setStyle("-fx-padding: 15");

        // Título
        Label titleTodos = new Label("Lista de Tarefas");
        titleTodos.setStyle("-fx-padding: 20; -fx-font-size: 30px");

        // Layouts
        VBox menu = new VBox(10, space1, allTodos, completedTodos, pendingTodos, space2, addTodo, removeTodo, changeTodo);
        menu.setPrefWidth(200);
        menu.setStyle("-fx-padding: 10;");

        VBox todoTable = new VBox(10, titleTodos, table);
        todoTable.setPrefWidth(500);

        VBox space = new VBox();
        space.setPrefWidth(100);

        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(todoTable);
        root.setRight(space);

        // janela
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.setTitle("Lista de Tarefas");
        primaryStage.show();

        system.menuAllTodos();
    }

    @Override
    public void menuTodos(List<Todo> todos) {
        table.setItems(FXCollections.observableArrayList(todos));
    }

    @Override
    public void createTodo() {
        TextInputDialog janela = new TextInputDialog();
        janela.setTitle("Nova tarefa");
        janela.setHeaderText("Adicionar nova tarefa");
        janela.setContentText("Descrição: ");
        janela.showAndWait().ifPresent(text -> {
            if (!text.isBlank()) {
                system.addTodo(text);
                system.menuAllTodos();
            }
        });
    }

    @Override
    public void changeTodo() {
        // vai dizer o id, alterar o título e o status. Se o id não for encontrado
        Todo selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showMessage("Selecione uma tarefa para alterar!");
            return;
        }

        Dialog<ButtonType> janela = new Dialog<>();
        janela.setTitle("Editar tarefa");

        TextField textField = new TextField(selected.getText());
        CheckBox checkBox = new CheckBox("Concluída");
        checkBox.setSelected(selected.isCompleted());

        VBox vbox = new VBox(10, new Label("Descrição: "), textField, checkBox);
        janela.getDialogPane().setContent(vbox);
        janela.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        janela.showAndWait().ifPresent(tipo -> {
            if(tipo == ButtonType.OK) {
                String text = textField.getText();
                boolean status = checkBox.isSelected();

                Todo todo = new Todo(text);
                todo.setCompleted(status);
                todo.setId(selected.getId());

                if (!text.isBlank()) {
                    system.changeTodo(todo);
                    system.menuAllTodos();
                } else {
                    showMessage("A descrição não pode estar vazia");
                }
            }
        });

    }

    @Override
    public void showMessage(String message) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}
