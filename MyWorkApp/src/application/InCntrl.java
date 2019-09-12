package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InCntrl {
@FXML
Text crTxt = new Text();	
@FXML
AnchorPane inPane = new AnchorPane();
@FXML
ImageView logo = new ImageView();
@FXML
private void initialize() {
	inPane.setStyle("-fx-background-color: #B9C5C5");
}
@FXML
private void login() {
    try {
    	Stage stage1 = (Stage) crTxt.getScene().getWindow();
        stage1.close();
    	FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 550);
        Stage stage = new Stage();
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        /*FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("WorkFXML.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 350, 270);
        stage = new Stage();
        stage.setTitle("News desk");
        stage.setScene(scene2);
        stage.setX(100);
        stage.setY(46);*/
        //stage.setY(200);
        //stage.show();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}
}
