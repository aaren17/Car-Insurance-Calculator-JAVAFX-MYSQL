package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin extends Application{	
		Label l1 = new Label("Welcome Admin");
		TextField agent_id = new TextField("");
		PasswordField agent_pass = new PasswordField();
		Button log_in = new Button("Log In");
		GridPane gpane = new GridPane();
		TextField username = new TextField();
		VBox vBox = new VBox();
		TextArea ta_displayUser =  new TextArea();
	@Override
	public void start(Stage arg0) throws Exception {
		StartHere mainMenu = new StartHere();
		HBox hBox = new HBox();
		hBox.getChildren().add(l1);
		hBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(hBox);
		
		Button bt_back = new Button("Back");
		
		gpane.add(new Label("Agent ID: "), 0, 0);
		gpane.add(agent_id, 1, 0);
		gpane.add(new Label("Agent Pass"), 0, 1);
		gpane.add(agent_pass, 1, 1);
		gpane.add(log_in, 1, 2);
		gpane.add(bt_back, 1, 3);
		gpane.setHgap(10);
		gpane.setVgap(10);
		gpane.setPadding(new Insets(15));
		
		vBox.getChildren().add(gpane);
		
		vBox.setPadding(new Insets(20));
		
		log_in.setOnAction(e->{
			Boolean isCorrect = false;
			try {
				isCorrect = returnValues(agent_id.getText(), agent_id.getText());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(isCorrect = true) {
				admin_gui(arg0);
			}
			else {
				System.out.println("nope");
			}
		});
		
		bt_back.setOnAction(e->{
			try {
				mainMenu.start(arg0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		vBox.setStyle("-fx-background-image: url(\"white.jpg\");"
				+ "-fx-background-size : 300px 300px");
		arg0.setTitle("Admin Log In");
		arg0.setScene(new Scene(vBox));
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public boolean returnValues(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
	
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
		System.out.println("Database connected");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet2 = statement.executeQuery("select admin_pass from admin where admin_id = " + "'" + username + "'");
		
		while(resultSet2.next()) {
			if (resultSet2.getString(1).equals(password))
				return true;}
		return false;
		
	}
	
	public void admin_gui(Stage stage) {
		Admin admin_Menu = new Admin();
		Button bt_pieChart = new Button("Piechart");
		Button bt_lineChart = new Button("Line Chart");
		GridPane gp = new GridPane();
		VBox vbox_admin = new VBox();
		Button back_button = new Button("Back");
		gp.add(new Label("Search User_Id"), 0, 0);
		gp.add(username, 6, 0);
		gp.add(bt_pieChart, 2, 0);
		gp.add(bt_lineChart, 4, 0);
		gp.add(back_button, 8, 0);
		
		gp.setHgap(20);
		gp.setVgap(20);
		gp.setPadding(new Insets(20));
		
		vbox_admin.getChildren().add(gp);
		vbox_admin.getChildren().add(ta_displayUser);
		
		back_button.setOnAction(e->{
			try {
				admin_Menu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bt_pieChart.setOnMouseClicked(e->{
			try {
				getChartData();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		bt_lineChart.setOnMouseClicked(e->{
			try {
				getLineData();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		username.setOnAction(e->{
			try {
				displayUser(username.getText());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		stage.setTitle("ADMIN");
		stage.setScene(new Scene(vbox_admin));
		stage.show();
	}
	
	public void getChartData() throws ClassNotFoundException, SQLException {  
		Stage primaryStage = new Stage();
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
		System.out.println("Database connected");
		Statement statement = connection.createStatement();
		
		String query = "select cartype, count(carType) from carInfo group by cartype";
		ResultSet resultSet = statement.executeQuery(query);
		ObservableList<Data> list = FXCollections.observableArrayList();
		while(resultSet.next()) {
	    list.addAll(new PieChart.Data(String.valueOf(resultSet.getString(1)), Integer.parseInt(resultSet.getString(2))));  }
		PieChart piechart = new PieChart();    
		piechart.setData(list);  
		StackPane root = new StackPane();  
		  
	    //Adding pie-chart to the layout   
	    root.getChildren().add(piechart);  
	  
	    //configuring scene   
	    Scene scene = new Scene(root,400,400);  
	    primaryStage.setTitle("Pie Chart");
	    primaryStage.setScene(scene);  
	    primaryStage.setTitle("PieChart Example");  
	    primaryStage.show();  
	}
	
	public void getLineData() throws ClassNotFoundException, SQLException {
		Stage stage = new Stage();
		stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Location");       
        
        final LineChart<String,Number> lineChart =  new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Line Graph");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("Type of cars Looked up according to Location ");
        
        Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
		System.out.println("Database connected");
		Statement statement = connection.createStatement();
		
		String query = "select location, count(location) from carInfo group by location";
		ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
        series.getData().add(new XYChart.Data(String.valueOf(resultSet.getString(1)), Integer.parseInt(resultSet.getString(2))));}
     
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        stage.setTitle("Line Chart");
        stage.setScene(scene);
        stage.show();
	}
	
	public void displayUser(String name) throws ClassNotFoundException, SQLException {
		ta_displayUser.clear();
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
	
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
		System.out.println("Database connected");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select u.user_name, c.carType, c.price, c.coverage \r\n"
				+ "from user u, carInfo c \r\n"
				+ "where u.username = c.username and u.user_name = " + "'" + name + "'");
		
		ta_displayUser.appendText("Username: " + name + "'s History\n");
		while(resultSet.next()) {
			ta_displayUser.appendText("Car Type: " + resultSet.getString(2) + "\t");
			ta_displayUser.appendText(" Price: " + resultSet.getString(3) + "\t");
			ta_displayUser.appendText("Coverage type" + resultSet.getString(4) + "\n");
		}
	}
}
