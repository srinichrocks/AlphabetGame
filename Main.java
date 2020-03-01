package com.alphabet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private MainController main;
//    private final File highScores;
//    
////    Main () throws URISyntaxException {
////    	highScores = new File(getClass().getResource("Resources/data/HighScores.csv").toURI());
////    }
////    
//////    @Override
//////    public void init()throws Exception{
////////    	BufferedReader bfr=new BufferedReader(new FileReader (highScores));
//////    	//We could read scores from file if we wanted to.
//////    }
////    
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Resources/fxml/Main.fxml"));
        main = new MainController();
        main.setMainStage(primaryStage);
        fxmlLoader.setController(main);
        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();

        primaryStage.setTitle("Alphabet Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Resources/img/icon.jpg")));
        primaryStage.show();

    }

    @Override
    public void stop()throws Exception {
        List<Player> highScoreData = main.getHighScoreController().getHighScoresTblViewData();
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(getClass().getResource("Resources/data/HighScores.csv").toURI())));
        bfw.write("Ranking,Name,Score");
        for (Player p : highScoreData) {
            bfw.newLine();
            bfw.write(p.getRanking()+","+p.getUserName()+","+p.getScore());
        }
        bfw.newLine();
        bfw.close();
    }
    public static void main(String[] args) {
        launch(args);

    }
}

