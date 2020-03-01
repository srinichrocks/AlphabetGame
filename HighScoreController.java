package com.alphabet;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class HighScoreController {

    private Stage stage, gameStage;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private TableView<Player> highScoresTblView;

    @FXML
    void initialize() {

    }

    @FXML
    public void doGoBack() {
        stage.hide();
        if (gameStage.isShowing())
            gameStage.hide();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setGameStage(Stage gameStage){
        this.gameStage = gameStage;
    }
    public List<Player> getHighScoresTblViewData() {
        return highScoresTblView.getItems();
    }
}
