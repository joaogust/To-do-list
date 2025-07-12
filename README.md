# To-do-list

## Sistema de lista de tarefas com banco de dados

O sistema permite a manipulação e armazenamento de tarefas através da interface gráfica JavaFX. Nele, é possível cadastrar, alterar e remover tarefas.

Ademais o sistema permite listar todas as tarefas, tarefas pendentes ou tarefas concluídas.

Uma tarefa contém os seguintes atributos:
- id (int): contém a chave primária da tarefa;
- text (String): descrevendo a tarefa a ser executada;
- completed (boolean): indicando se a tarefa foi concluída ou não;
- changeDate (LocalDateTime): indicando a data de alteração de status da tarefa.

Todos apresentam restrição "NOT NULL", com o intuito de garantir a integridade dos dados.

O projeto foi cuidadosamente organizado em diferentes classes e diretórios.
Os diretórios que separam as classes são: core, infra, model, repository e ui.
As classes são:
- TodoSystem: responsável gerenciar as funcionalidades e o uso da interface;
- ConnectionDB: responsável por conectar com o banco de dados SQLite;
- Todo: classe que contém os atributos, construtor, getters e setters das tarefas;
- TodoRepository: responsável por criar a tabela das tarefas e as queries para a inserção, remoção, alteração e exibição de tarefas no banco de dados;
- SystemUI: Interface responsável por padronizar métodos das "UIs";
- JavaFXUI: Implementa a interface SystemUI e cria uma UI utilizando JavaFX.
- App: Classe responsável por iniciar a aplicação.


Ele apresenta uma interface intuitiva e uma estrutura modular, podendo futuramente apresentar novas "UIs" com mais funcionalidades.

### Estrutura de pastas do projeto:
``````
src
├───main
│   ├───java
│   │   └───br
│   │       └───edu
│   │           └───ifsp
│   │               └───spo
│   │                   └───java
│   │                       └───todolist
│   │                           ├───core
│   │                           ├───infra
│   │                           ├───model
│   │                           ├───repository
│   │                           └───ui
│   └───resources
│       └───com
│           └───example
│               └───To-do-list
└───test
    └───java
        └───br
            └───edu
                └───ifsp
                    └───spo
                        └───java
                            └───todolist
                                └───core
``````
### Tecnologias Utilizadas
* [Gladle]
* [JDBC]
* [SQLite]
* [JavaFX]

## Como rodar o projeto ✅
Para rodar o projeto é necessário que antes da compilação seja executado o seguinte comando no terminal:

```
.\gradlew.run
```
Após isso, poderá compilar o sistema corretamente.
