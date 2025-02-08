package application;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartHere extends Application{
	HBox hBox = new HBox();
	VBox vBox = new VBox();
	GridPane gridPane = new GridPane();
	SignIn user_signIn = new SignIn();
	Admin admin_signIn = new Admin();
	GridPane gPane = new GridPane();
	@Override
	public void start(Stage arg0) throws Exception {
		Label lb_title = new Label("TOKIO MARINE");
		lb_title.setFont(Font.font("Times new Roman",40));
		
		Label lb_user = new Label("USER");
		lb_user.setFont(Font.font("Times new Roman", 20));
		
		Label lb_admin = new Label("ADMIN");
		lb_admin.setFont(Font.font("Times new Roman", 20));
		
		ImageView userLogo = new ImageView("user.png");
		userLogo.setFitHeight(60);
		userLogo.setFitWidth(60);
		userLogo.setCursor(Cursor.HAND);
		
		ImageView adminLogo = new ImageView("admin.png");
		adminLogo.setFitHeight(60);
		adminLogo.setFitWidth(60);
		adminLogo.setCursor(Cursor.HAND);
		
		gPane.add(userLogo, 0, 0);
		gPane.add(lb_user, 0, 1);
		gPane.add(adminLogo, 2, 0);
		gPane.add(lb_admin, 2, 1);
		
		gPane.setHgap(50);
		gPane.setVgap(20);
		gPane.setPadding(new Insets(20));
		
		
		vBox.getChildren().add(lb_title);
		vBox.getChildren().add(gPane);
		vBox.setAlignment(Pos.CENTER);
		
		hBox.getChildren().add(vBox);
		hBox.setPadding(new Insets(20));
		hBox.setAlignment(Pos.CENTER);
		
		  ScaleTransition trans = new ScaleTransition(Duration.seconds(2), lb_title);
	        trans.setFromX(1.0);
	        trans.setToX(0.40);
	        trans.setFromY(1.0);
	        trans.setToY(0.40);
	        // Let the animation run forever        
	        trans.setCycleCount(ScaleTransition.INDEFINITE);
	        // Reverse direction on alternating cycles
	        trans.setAutoReverse(true);
	        // Play the Animation
	        trans.play();
		
		userLogo.setOnMouseClicked(e->{
			try {
				user_signIn.start(arg0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		adminLogo.setOnMouseClicked(e->{
			try {
				admin_signIn.start(arg0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		hBox.setStyle("-fx-background-image: url(\"white.jpg\");"
				+ "-fx-background-size : 300px 300px");
		
		arg0.setTitle("Welcome To Tokyo Marine");
		arg0.setScene(new Scene(hBox));
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
