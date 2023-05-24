package dev.wson.start.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import dev.wson.start.controller.DBController;

public class ToDoStartController implements Initializable {
    @FXML
    private Label labelOla;
    @FXML
    private Button btProjetos;
    @FXML
    private Button btTarefas;
    @FXML
    private Label labelCredit;
    private DBController dbController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarLabels();
        iniciarBotoes();     
    }
    public void iniciarLabels() {
        labelOla.setText("Carga de teste no Banco de Dados");
        labelCredit.setText("Desenvolvido por @wson.alves");
    }
    public void iniciarBotoes() {
        dbController = new DBController();
        btProjetos.setText("Criar Projetos");
        btProjetos.setOnAction(e -> dbController.salvarProjetos());
        btTarefas.setText("Criar Tarefas");
        btTarefas.setOnAction(e -> dbController.salvarTarefas());
    }
}