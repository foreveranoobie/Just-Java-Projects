package application;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class deskInfoCtrl {
  @FXML
  AnchorPane deskinfoAnch = new AnchorPane();
  @FXML
  Text timeT = new Text();
  @FXML
  Text DesripT = new Text();
  @FXML
  Text AuthorT = new Text();
  @FXML
  Text NameT = new Text();
  @FXML
  private void initialize(){
	  deskinfoAnch.setStyle("-fx-background-color:#C0C0C0");
  };
}
