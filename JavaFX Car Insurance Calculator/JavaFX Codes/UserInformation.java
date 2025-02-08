package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInformation extends Application {
	Connection connection; //initializing connection
	Statement statement2;
	PreparedStatement statement; // initializing statement
	ResultSet resultSet; // initilaizing resultSet

	public void insertValues(String name, String dob, double salary, String gender, String status, 
			String username, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
	
		connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
		
		System.out.println("Database connected");
		
		String queryString = "insert into User values(?,?,?,?,?,?,?)";
		statement = connection.prepareStatement(queryString);
		statement.setString(1, name);
		statement.setString(2, dob);
		statement.setString(3, String.valueOf(salary));
		statement.setString(4, gender);
		statement.setString(5, status);
		statement.setString(6, username);
		statement.setString(7, password);
		statement.execute();
	}
	
	public boolean returnValues(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
	
		connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
		System.out.println("Database connected");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet2 = statement.executeQuery("select user_password from user where username = " + "'" + username + "'");
		
		while(resultSet2.next()) {
			if (resultSet2.getString(1).equals(password))
				return true;}
		return false;
		
	}
	
	public static void main(String[] args) {  
	    launch(args);  
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	} 
	
}
