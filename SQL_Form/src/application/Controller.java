package application;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Controller {
	String url = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	String username = "root";
	String password = "67245";
	Connection conn = null;
	@FXML
	private Button delBut;
	@FXML
	private Button updT;
	private ObservableList<ShopT> DataSh = FXCollections.observableArrayList();
	@FXML
	private Button addN;
	@FXML
	TableColumn<ShopT, String> ID_part;
	@FXML
	TableColumn<ShopT, String> type;
	@FXML
	TableColumn<ShopT, String> vendor;
	@FXML
	TableColumn<ShopT, String> price;
	@FXML
	TableColumn<ShopT, String> exist;
	@FXML
	TableView<ShopT> shop = new TableView<ShopT>();
	@FXML
	TextField typeRow = new TextField();
	@FXML
	TextField codeRow = new TextField();
	@FXML
	TextField priceRow = new TextField();
	@FXML
	TextField existRow = new TextField();
	@FXML
	Button addNbut = new Button();
	int size = 0;

	@FXML
	private void initialize()
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		shop.setEditable(true);
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully connected");
		Statement stmt = conn.createStatement();
		String strSelect = "select count(*) size from shop";
		ResultSet rset = stmt.executeQuery(strSelect);
		while (rset.next()) {
			strSelect = rset.getString("size");
		}
		size = Integer.parseInt(strSelect);
	}

	@FXML
	private void editVendor(TableColumn.CellEditEvent<ShopT, String> prod)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		ShopT shpt = shop.getSelectionModel().getSelectedItem();
		shpt.setVendor(prod.getNewValue());
		String ID = shpt.getID_part();
		String vend = shpt.getVendor();
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		String strSelect = "update mydb.shop set vendor_code_sh = '" + vend + "' where ID_part =" + ID;
		System.out.println(strSelect);
		PreparedStatement pstmt = conn.prepareStatement(strSelect);
		pstmt.executeUpdate();
	}

	@FXML
	private void editType(TableColumn.CellEditEvent<ShopT, String> prod)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		ShopT shpt = shop.getSelectionModel().getSelectedItem();
		shpt.setType(prod.getNewValue());
		String ID = shpt.getID_part();
		String vend = shpt.getType();
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		String strSelect = "update mydb.shop set type = '" + vend + "' where ID_part =" + ID;
		System.out.println(strSelect);
		PreparedStatement pstmt = conn.prepareStatement(strSelect);
		pstmt.executeUpdate();
	}

	@FXML
	private void editPrice(TableColumn.CellEditEvent<ShopT, String> prod)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		ShopT shpt = shop.getSelectionModel().getSelectedItem();
		shpt.setPrice(prod.getNewValue());
		String ID = shpt.getID_part();
		String vend = shpt.getPrice();
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		String strSelect = "update mydb.shop set price = " + vend + " where ID_part =" + ID;
		System.out.println(strSelect);
		PreparedStatement pstmt = conn.prepareStatement(strSelect);
		pstmt.executeUpdate();
	}

	@FXML
	private void editExist(TableColumn.CellEditEvent<ShopT, String> prod)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		ShopT shpt = shop.getSelectionModel().getSelectedItem();
		shpt.setExist(prod.getNewValue());
		String ID = shpt.getID_part();
		String vend = shpt.getExist();
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		String strSelect = "update mydb.shop set exist = '" + vend + "' where ID_part =" + ID;
		System.out.println(strSelect);
		PreparedStatement pstmt = conn.prepareStatement(strSelect);
		pstmt.executeUpdate();
	}

	@FXML
	private void updateTab() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully connected");
		Statement stmt = conn.createStatement();
		String strSelect = "select count(*) size from shop";
		ResultSet rset = stmt.executeQuery(strSelect);
		while (rset.next()) {
			strSelect = rset.getString("size");
		}
		size = Integer.parseInt(strSelect);
		if (size == 0) {
			strSelect = "ALTER TABLE mydb.shop AUTO_INCREMENT = 1";
			PreparedStatement pstmt = conn.prepareStatement(strSelect);
			pstmt.executeUpdate();
		}
		if (size > 0) {
			shop.getItems().clear();
		}
		System.out.println(size);
		for (int k = 1; k < size + 1; k++) {
			ShopT spT = new ShopT(k);
			DataSh.add(spT);
		}
		ID_part.setCellValueFactory(new PropertyValueFactory<ShopT, String>("ID_part"));
		type.setCellValueFactory(new PropertyValueFactory<ShopT, String>("type"));
		vendor.setCellValueFactory(new PropertyValueFactory<ShopT, String>("vendor"));
		price.setCellValueFactory(new PropertyValueFactory<ShopT, String>("price"));
		exist.setCellValueFactory(new PropertyValueFactory<ShopT, String>("exist"));
		type.setCellFactory(TextFieldTableCell.forTableColumn());
		vendor.setCellFactory(TextFieldTableCell.forTableColumn());
		price.setCellFactory(TextFieldTableCell.forTableColumn());
		exist.setCellFactory(TextFieldTableCell.forTableColumn());
		shop.setItems(DataSh);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@FXML
	private void AddProd(ActionEvent event)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(Controller.class.getResource("NewPrInfo.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("My modal window");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.show();
		//
	}

	@FXML
	private void deleteEl() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		int index = shop.getSelectionModel().getSelectedIndex();
		@SuppressWarnings("unused")
		String idtodel = ID_part.getCellData(index);
		System.out.println("k");
		System.out.println(idtodel.length());
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully connected");
		Statement stmt = conn.createStatement();
		index++;
		String strSelect = "DELETE FROM mydb.shop WHERE ID_part = " + idtodel;
		System.out.println(strSelect);
		PreparedStatement pstmt = conn.prepareStatement(strSelect);
		pstmt.executeUpdate();
		size--;
		shop.getItems().removeAll(shop.getSelectionModel().getSelectedItems());
	}

	@FXML
	private void addNel() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully connected");
		Statement stmt = conn.createStatement();
		size++;
		String strSelect;
		strSelect = "INSERT INTO mydb.shop (vendor_code_sh, type, price, exist) values('" + codeRow.getText() + "',"
				+ "'" + typeRow.getText() + "'," + priceRow.getText() + ",'" + existRow.getText() + "')";
		// }
		System.out.println(strSelect);
		PreparedStatement pstmt = conn.prepareStatement(strSelect);
		pstmt.executeUpdate();
	}
}
