package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkCntrl {
   @FXML
   AnchorPane newsPane = new AnchorPane();
   @FXML
   TableView<Desk> deskT = new TableView();
   @FXML
   TableColumn<Desk,String> titleC;
   @FXML
   TableColumn<Desk,String> dateC;
   @FXML
   private void initialize() {
	      newsPane.setStyle("-fx-background-color:#C0C0C0");
	      dateC.setStyle("-fx-background-color:#DCDCDC; -fx-border-color:#C0C0C0");
	      titleC.setStyle("-fx-background-color:#DCDCDC; -fx-border-color:#C0C0C0");
		  deskT.setEditable(true);
		  ObservableList<Desk> DataSh = FXCollections.observableArrayList();
		  Desk dsk= new Desk("21 May 12:00","Team Meeting");
		  DataSh.add(dsk);
		  dateC.setCellValueFactory(new PropertyValueFactory<Desk,String>("Date"));
		  titleC.setCellValueFactory(new PropertyValueFactory<Desk,String>("Title"));
		  //dateC.setCellFactory(TextFieldTableCell.forTableColumn());
		 // titleC.setCellFactory(TextFieldTableCell.forTableColumn());
	      deskT.setItems(DataSh);
   }
   @FXML
   private void showD(MouseEvent event) throws IOException {
	   if (event.getClickCount() == 2) //Checking double click
	    {
	       // System.out.println(deskT.getSelectionModel().getSelectedItem().getDate());
	        Stage stage = new Stage();
	        FXMLLoader fxmlLoader2 = new FXMLLoader();
	        fxmlLoader2.setLocation(getClass().getResource("deskInfo.fxml"));
	        Scene scene2 = new Scene(fxmlLoader2.load(), 428, 400);
	        stage = new Stage();
	        stage.setTitle(deskT.getSelectionModel().getSelectedItem().getTitle());
	        stage.setScene(scene2);
	        stage.setX(100);
	        stage.setY(46);
	        //stage.setY(200);
	        stage.show();
	    }
   }
}
