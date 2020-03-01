package com.alphabet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController{

    private TextInputDialog usernameEntry;
    private final Media media;
    private final MediaPlayer mediaPlayer;
    private Stage highScoresStage, howToPlayStage, gameStage,mainStage;
    private GameController gameController = null;
    private HighScoreController highScoreController = null;

    public MainController() {
        media = new Media(getClass().getResource("Resources/sounds/tintin.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startGameBtn;

    @FXML
    private Button howToPlayBtn;

    @FXML
    private Button highScoresBtn;

    @FXML
    private Button quitBtn;

    @FXML
    void initialize() throws IOException {
        gameStage = new Stage();
        highScoresStage =new Stage(StageStyle.TRANSPARENT);
        howToPlayStage = new Stage(StageStyle.TRANSPARENT);
        setupStages();
    }

    @FXML
    public void doQuit() {
        Platform.exit();
    }

    @FXML
    public void doHighScores() {
        highScoresStage.showAndWait();
    }
    public void doHowToPlay() {
        howToPlayStage.showAndWait();
    }
    public void doGame() throws IOException {

        usernameEntry.showAndWait();

        //TODO: WE NEED AN ACTUAL RESPONSE!
        Player currPlayer = new Player(usernameEntry.getEditor().getText());

        gameController.setPlayer(currPlayer);
        gameStage.show();
    }

    private void setupStages() throws IOException {

        FXMLLoader fxmlLoader = null;
        Parent root  = null;

        //User Entry Window
        usernameEntry=new TextInputDialog();
        Stage userNameEntryStage = Stage.class.cast(usernameEntry.getDialogPane().getScene().getWindow());
        userNameEntryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("Resources/img/Icon.jpg")) );
        userNameEntryStage.setAlwaysOnTop(true);
        userNameEntryStage.setTitle("Enter a username:");



        //How To Play Window
        howToPlayStage =new Stage(StageStyle.TRANSPARENT);

        fxmlLoader = new FXMLLoader(getClass().getResource("Resources/fxml/HowToPlay.fxml"));

        HowToPlayController howToPlayController = new HowToPlayController();
        howToPlayController.setStage(howToPlayStage);
        fxmlLoader.setController(howToPlayController);
        fxmlLoader.load();

        root = fxmlLoader.getRoot();

        howToPlayStage.setTitle("How To Play");
        howToPlayStage.setScene(new Scene(root));
        howToPlayStage.getIcons().add(new Image(getClass().getResourceAsStream("Resources/img/icon.jpg")));
        howToPlayStage.setAlwaysOnTop(true);

        //High Score Window
        fxmlLoader = new FXMLLoader(getClass().getResource("Resources/fxml/HighScore.fxml"));

        highScoreController = new HighScoreController();
        highScoreController.setStage(highScoresStage);
        highScoreController.setGameStage(gameStage);
        fxmlLoader.setController(highScoreController);
        fxmlLoader.load();

        root = fxmlLoader.getRoot();

        highScoresStage.setTitle("High Scores");
        highScoresStage.setScene(new Scene(root));
        highScoresStage.getIcons().add(new Image(getClass().getResourceAsStream("Resources/img/icon.jpg")));
        highScoresStage.setAlwaysOnTop(true);

        //Gameplay Window
        fxmlLoader = new FXMLLoader(getClass().getResource("Resources/fxml/Game.fxml"));
        gameController = new GameController();
        gameController.setStage(gameStage);
        gameController.setHighScoreStage(highScoresStage);

        fxmlLoader.setController(gameController);
        fxmlLoader.load();

        root = fxmlLoader.getRoot();

        gameStage.setTitle("Alphabet Fun");
        gameStage.setScene(new Scene(root));
        gameStage.getIcons().add(new Image(getClass().getResourceAsStream("Resources/img/icon.jpg")));
        gameStage.setAlwaysOnTop(true);
    }
    public void setMainStage(Stage mainStage){
        this.mainStage=mainStage;
    }
    public HighScoreController getHighScoreController() {
        return highScoreController;
    }
}

