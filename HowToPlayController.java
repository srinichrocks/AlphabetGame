package com.alphabet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HowToPlayController {
    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    @FXML
    Button goBackBtn;
    @FXML
    public void doGoBack(){
        stage.hide();
    }
    public void initialize() {}
}
