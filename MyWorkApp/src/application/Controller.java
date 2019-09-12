package application;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Controller {
	int count = 0;
	@FXML
	AnchorPane anchP1 = new AnchorPane();
	@FXML
	TextFlow memTxt = new TextFlow();
    @FXML
    ImageView img1 = new ImageView();
    @FXML
    ImageView img2 = new ImageView();
    @FXML
    ImageView img3 = new ImageView();
    @FXML
    ImageView img4 = new ImageView();
    @FXML
    ImageView img5 = new ImageView();
    @FXML
	TextFlow TextChat = new TextFlow();
    @FXML
    TextField textEnt = new TextField();
    @FXML
    AnchorPane anchPane = new AnchorPane();
    @FXML
    Line line = new Line();
	@FXML
	private void initialize() {
		textEnt.setStyle("-fx-background-color: #D3D3D3");
		anchPane.setStyle("-fx-background-color: #A9A9A9");
		TextChat.setStyle("-fx-background-color: #C0C0C0");
		 Text text1 = new Text(" Igor: Good morning, guys!");
		  text1.setFont(new Font(15));
		  text1.setFill(Color.BLACK);
		  Text text2 = new Text("\n                                                                       You: Hello! How are you?");
		  text2.setFont(new Font(15));
		  text2.setFill(Color.BLACK);
		  Text text3 = new Text("\n Elena: What tasks do we have for today?");
		  text3.setFont(new Font(15));
		  text3.setFill(Color.BLACK);
		  Text text4 = new Text("\n Igor: Sent voice message ");
		  text4.setFont(new Font(15));
		  text4.setFill(Color.BLACK);
		  //TextChat.setMinSize(400, 450);
	      ObservableList<Node> list = TextChat.getChildren(); 
	      list.addAll(text1, text2, text3, text4);
			ImageView imageView = new ImageView(
				    new Image(getClass().getResource("25.png").toExternalForm())
				);
			imageView.setFitHeight(25);
			imageView.setFitWidth(25);
			TextChat.getChildren().add(imageView);
	}
	@FXML
	private void newsR() throws IOException {
		 FXMLLoader fxmlLoader2 = new FXMLLoader();
		 Stage stage = new Stage();
	        fxmlLoader2.setLocation(getClass().getResource("WorkFXML.fxml"));
	        Scene scene2 = new Scene(fxmlLoader2.load(), 350, 270);
	        stage = new Stage();
	        stage.setTitle("News desk");
	        stage.setScene(scene2);
	        stage.setX(100);
	        stage.setY(46);
	        stage.show();
	}
	@FXML
	private void memList() throws IOException {
		if(anchP1.getOpacity()==100) {
		anchP1.setOpacity(0);
		}
		else if(anchP1.getOpacity()==0) {
			anchP1.setOpacity(100);
		}
		if(count==0) {
		//memTxt = new TextFlow();
	    anchP1.setOpacity(100);
		anchP1.setStyle("-fx-background-color: #A9A9A9");
		memTxt.setStyle("-fx-background-color: #C0C0C0");
	    ObservableList<Node> list1 = memTxt.getChildren(); 
	    ImageView imageView1 = new ImageView(
			    new Image(getClass().getResource("images.png").toExternalForm())
		);
		imageView1.setFitHeight(25);
		imageView1.setFitWidth(25);
		ImageView imageView2 = new ImageView(
			    new Image(getClass().getResource("images.png").toExternalForm())
		);
		imageView2.setFitHeight(25);
		imageView2.setFitWidth(25);
		ImageView imageView3 = new ImageView(
			    new Image(getClass().getResource("images.png").toExternalForm())
		);
		imageView3.setFitHeight(25);
		imageView3.setFitWidth(25);
		ImageView imageView4 = new ImageView(
			    new Image(getClass().getResource("images.png").toExternalForm())
		);
		imageView4.setFitHeight(25);
		imageView4.setFitWidth(25);
		ImageView imageView5 = new ImageView(
			    new Image(getClass().getResource("images.png").toExternalForm())
		);
		imageView5.setFitHeight(25);
		imageView5.setFitWidth(25);
		memTxt.getChildren().add(imageView1);
	    Text text1 = new Text(" Igor\n");
		list1.add(text1);
		memTxt.getChildren().add(imageView2);
	    Text text2 = new Text(" Alexandr\n");
		list1.add(text2);
	    memTxt.getChildren().add(imageView3);
	    Text text3 = new Text(" Vladymir\n");
		list1.add(text3);	    
		memTxt.getChildren().add(imageView4);
	    Text text4 = new Text(" Vladymir\n");
		list1.add(text4);		
	    memTxt.getChildren().add(imageView5);
	    Text text5 = new Text(" Elena");
		  list1.add(text5);	    
		  text1.setFont(new Font(15));
		  text1.setFill(Color.BLACK);
		count++;
		}
	}
}
