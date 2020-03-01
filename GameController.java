package com.alphabet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.*;

public class GameController {
    private Stage stage, highScoreStage;
    private Map<String,Image> imgAnswerMap;
    private Player player;
    private Queue<Integer> imgStack;
    private List<Player> highScores;
    private final List<Integer> list = IntStream.range(97, 123).boxed().collect(Collectors.toList());

    @FXML
    private ImageView imageView;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button quitBtn;

    @FXML
    private TextField enterTxt;

    @FXML
    private Label scoreCountLbl;

    @FXML
    public void initialize() {
        imgAnswerMap = populateImgAnswerMap();
        imgStack = new ArrayDeque<>();
        initImg();

        highScores = TableView.class.cast(highScoreStage.getScene().getRoot()
                .getChildrenUnmodifiable()
                .stream()
                .filter( n -> n instanceof TableView)
                .findFirst()
                .get()).getItems();

        enterTxt.requestFocus();
    }

    @FXML
    public void doQuit() {
        Platform.exit();
    }

    @FXML
    public void doGoBack() {
        stage.hide();
        reset();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void doEnter(){
        String clientRsp = enterTxt.getText().trim();

        String response = clientRsp.length()>0 ? String.valueOf(enterTxt.getText().toLowerCase().charAt(0)) : "";
        if(!response.isEmpty() && imageView.getImage().equals(imgAnswerMap.get(response))) {
            player.setScore(player.getScore() + 1);
            scoreCountLbl.setText(player.getScore() + "/26");
        }

        Integer next = imgStack.poll();

        //if there are no more images (is null) then the game is over!
        if (next == null) {
            //update the high scores
            highScores.add(player);

            //rank
            highScores.sort ( Comparator.comparingInt((Player p) -> p.getScore() ).reversed());
            for (int i = 0; i < highScores.size(); i++) {
//                highScores.get(i).setRanking();
                Player p = highScores.get(i);
                p.setRanking(i+1);
            }

            //show the highScoreStage
            highScoreStage.show();
            //reset game play screen
            reset();
        }
        else //we still have more pictures to go!
            imageView.setImage(imgAnswerMap.get(String.valueOf((char)(next.intValue()))));

    }

    private Map<String, Image> populateImgAnswerMap() {
        Map<String, Image> list = new HashMap<>();
        list.put("a", new Image(getClass().getResourceAsStream( "Resources/img/PicturesForGame/A-Apple.jpeg")));
        list.put("b", new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/B-Balloon.jpg")));
        list.put("c",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/C-Cat.png")));
        list.put("d",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/D-Dog.jfif")));
        list.put("e",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/E-Elephant.png")));
        list.put("f",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/F-Fire.png")));
        list.put("g",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/G-Giraffe.png")));
        list.put("h",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/H-Horse.jfif")));
        list.put("i",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/I-Igloo.png")));
        list.put("j",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/J-Jump.gif")));
        list.put("k",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/K-Kangaroo.jpg")));
        list.put("l",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/L-Lion.png")));
        list.put("m",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/M-Math.jfif")));
        list.put("n",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/N-Nest.png")));
        list.put("o",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/O-Octopus.jpg")));
        list.put("p",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/P-Park.jpg")));
        list.put("q",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/Q-Queen.jfif")));
        list.put("r",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/R-Rainbow.jpg")));
        list.put("s",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/S-Spider.jpg")));
        list.put("t",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/T-Turtle.png")));
        list.put("u",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/U-Umbrella.jpg")));
        list.put("v",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/V-Vampire.png")));
        list.put("w",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/W-Whale.jfif")));
        list.put("x",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/X-Xray.jpg")));
        list.put("y",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/Y-Yoyo.png")));
        list.put("z",new Image(getClass().getResourceAsStream("Resources/img/PicturesForGame/Z-Zebra.png")));
        return list;
    }

    public void setPlayer(Player player){
        this.player=player;
    }

    public void setHighScoreStage(Stage highScoresStage) {
        this.highScoreStage = highScoresStage;
    }

    public void initImg() {
        Collections.shuffle(list);
        imgStack.clear();
        imgStack.addAll(list);
        imageView.setImage(imgAnswerMap.get(String.valueOf((char)(imgStack.poll().intValue()))));
    }

    public void reset(){
        initImg();
        //rest game play screen counter -
        scoreCountLbl.setText("0/26");
    }
}
