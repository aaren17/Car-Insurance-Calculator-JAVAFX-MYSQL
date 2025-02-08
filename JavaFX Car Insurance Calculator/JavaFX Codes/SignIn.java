package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import application.SignIn.user;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignIn extends Application{
	GridPane gPane = new GridPane();
	TextField tf_Username = new TextField();
	PasswordField tf_passowrd = new PasswordField();
	Label lbl_register = new Label("New User? Register Here");
 	VBox vBox = new VBox();
	TextArea ta = new TextArea();
	UserInformation user = new UserInformation();
	CalcInsurance calcInsurance = new CalcInsurance();
	String whoIsUser = "";
	DecimalFormat df = new DecimalFormat ("0.00");
	@Override
	public void start(Stage arg0)  {
		StartHere mainMenu = new StartHere();
		Stage heheStage = new Stage();
		Button bt_signIn = new Button("Log in");
		Label label = new Label();
		Button back = new Button("Back");
		
		gPane.add(new Label("Username "), 0, 0);
		gPane.add(tf_Username, 1, 0);
		gPane.add(new Label("Password "), 0, 1);
		gPane.add(tf_passowrd, 1, 1);
		gPane.add(bt_signIn, 1, 2);
		gPane.add(back, 1, 3);
		
		gPane.setVgap(10);
		gPane.setHgap(10);
		gPane.setPadding(new Insets(30));
		
		lbl_register.setTextFill(Color.BLUE);
		lbl_register.setCursor(Cursor.HAND);
		lbl_register.setOnMouseClicked(e-> RegisterUsers());
		
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(gPane);
		vBox.getChildren().add(label);
		vBox.getChildren().add(lbl_register);
	
		bt_signIn.setOnAction(e -> {
			
			Boolean isCorrect = null;
			try {
				isCorrect =  user.returnValues(tf_Username.getText(), tf_passowrd.getText());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (isCorrect) {
				whoIsUser = tf_Username.getText().trim();
				label.setText("");
				calcInsurance.begin(arg0);
			}
			else {
				label.setText("Incorrect Password");
				label.setTextFill(Color.RED);
				
			}
		});
		
		back.setOnAction(e->{
			try {
				mainMenu.start(arg0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		vBox.setStyle("-fx-background-image: url(\"white.jpg\");"
				+ "-fx-background-size : 280px 300px");
		
		arg0.setTitle("User Sign In ");
		arg0.setScene(new Scene(vBox));
		arg0.setResizable(false);
		arg0.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void RegisterUsers(){
		GridPane gpane2 = new GridPane();
		GridPane gPane3 = new GridPane();
		TextField lb_FullName = new TextField();
		DatePicker birthday = new DatePicker();
		TextField tf_username = new TextField();
		TextField tf_password = new TextField();
		TextField tf_Salary = new TextField();
		Button bt_register = new Button("Register");
		Label lb_Title = new Label("USER REGISTER");
		lb_Title.setFont(Font.font("Times new Roman", 50));
		lb_Title.setTextFill(Color.BLACK);
		
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(500));
		fade.setFromValue(10);
		fade.setToValue(0.1);
		fade.setCycleCount(1000);
		fade.setAutoReverse(true);
		fade.setNode(lb_Title);
		fade.play();
		ComboBox<String> cbo_gender = new ComboBox<>();
		String[] gender = {"Male","Female","Not Specified"};
		ObservableList<String> genders= FXCollections.observableArrayList(gender);
		cbo_gender.getItems().addAll(genders);
		
		RadioButton rb_single = new RadioButton("Single");
		rb_single.setPadding(new Insets(5,5,5,0));
		RadioButton rb_married = new RadioButton("Married");
		rb_married.setPadding(new Insets(5));
		RadioButton rb_divorced = new RadioButton("Divorced");
		rb_divorced.setPadding(new Insets(5));
		
		ToggleGroup group = new ToggleGroup();
		rb_single.setToggleGroup(group);
		rb_married.setToggleGroup(group);
		rb_divorced.setToggleGroup(group);
		
		HBox radiobuttons = new HBox();
		radiobuttons.setPadding(new Insets(5,0,0,0));
		radiobuttons.getChildren().add(rb_single);
		radiobuttons.getChildren().add(rb_married);
		radiobuttons.getChildren().add(rb_divorced);
		
 		VBox vbox2 = new VBox();
		Stage args = new Stage();
		
		ta.setMaxSize(250, 20);
		
		   // TODO Auto-generated method stub
			gpane2.add(new Label("Full Name"), 0, 0);
			gpane2.add(lb_FullName, 1, 0);
			gpane2.add(new Label("Date Of Birth"), 0, 1);
			gpane2.add(birthday, 1, 1);
			gpane2.add(new Label("Gender"), 2, 0);
			gpane2.add(cbo_gender, 3, 0);
			gpane2.add(new Label("Status"), 2, 1);
			gpane2.add(radiobuttons, 3, 1);
			gpane2.add(new Label("Salary "), 0, 2);
			gpane2.add(tf_Salary, 1, 2);
			gpane2.setVgap(10);
			gpane2.setHgap(10);
			gpane2.setPadding(new Insets(30,30,10,30));
			
			ta.setEditable(false);
			
			gPane3.add(new Label("USERNAME"), 0, 0);
			gPane3.add(tf_username, 0, 1);
			gPane3.add(new Label("PASSWORD"), 0, 2);
			gPane3.add(tf_password, 0, 4);
			gPane3.add(bt_register, 0, 5);
			gPane3.add(ta, 0, 6);
			HBox hBox = new HBox();
			gPane3.setVgap(5);
			gPane3.setHgap(10);
			gPane3.setPadding(new Insets(30));
			hBox.getChildren().add(gPane3);
			hBox.setAlignment(Pos.CENTER);
			
			vbox2.getChildren().add(lb_Title);
			vbox2.getChildren().add(gpane2);
			vbox2.getChildren().add(hBox);
			vbox2.setAlignment(Pos.CENTER);
			
			bt_register.setOnAction(e-> {
				RadioButton getSelectedvalue = (RadioButton) group.getSelectedToggle();
				String selectedValue = getSelectedvalue.getText();
				try {
					user.insertValues(lb_FullName.getText(), String.valueOf(birthday.getValue()), 
							Double.parseDouble(tf_Salary.getText()),String.valueOf(cbo_gender.getValue().trim()) ,
							String.valueOf(selectedValue) , tf_username.getText(), tf_password.getText());
					ta.appendText("Successfully Registered");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			vbox2.setStyle("-fx-background-image: url(\"white.jpg\");"
					+ "-fx-background-size : 600px 530px");
			Scene scene = new Scene(vbox2,600,500);
			
			args.setTitle("User Register");
			args.setScene(scene);
			args.setResizable(false);
			args.show();
	}
	
	public class user extends SignIn{
		public String uname;
		public String password;
		
		
		user(String u,String p){
			uname= u;
			password = p;	
		}
	}
	
	class CalcInsurance {
		

		// Override the start method in the Application class
		TextArea ta = new TextArea();
		ComboBox <String>[] cb = new ComboBox[5];
		Label [] label = new Label[9];
		TextField price = new TextField();
		
		public void begin(Stage primaryStage) {
			SignIn signIn_Menu = new SignIn();
			GridPane gp = new GridPane();
			BorderPane bp = new BorderPane();
			BorderPane bpMain= new BorderPane();
			Button calcBt = new Button("Calculate");
			Button back_button = new Button("Back");
			VBox[] y = new VBox[4];
			HBox[] x = new HBox[4];
			
			for(int i =0;i<y.length;i++) {
				y[i] = new VBox();
				x[i] = new HBox();
			}
			
			for(int i =0; i<cb.length;i++) {
				cb[i] =new ComboBox();
			}
			
			addComboBox();
			createLabel();
			
			gp.add(label[0],0,0);	
			gp.add(cb[0], 0, 1);
			
			gp.add(label[1], 0, 2);
			gp.add(cb[1], 0, 3);
			
			gp.add(label[2], 0, 4);
			gp.add(cb[2], 0, 5);
			
			gp.add(label[3], 1, 0);
			gp.add(price, 1, 1);
			
			gp.add(label[4], 1, 2);
			gp.add(cb[3], 1, 3);
			
			gp.add(label[5], 1, 4);
			gp.add(cb[4], 1, 5);
			
			gp.setPadding(new Insets(0,30,30,30));
			gp.setVgap(10);
			gp.setHgap(70);
			
			y[0].getChildren().add(gp);
			y[0].setAlignment(Pos.CENTER);
			x[0].getChildren().add(y[0]);
			x[0].setAlignment(Pos.CENTER);
			x[2].setAlignment(Pos.CENTER);
			gp.autosize();
			bp.setTop(x[0]) ;
			ta.setPrefSize( 20,90);
			ta.setOpacity(0.8);
			
			ComboBox<qna> faq1 = new ComboBox();
			
			GridPane gpqna = new GridPane();
					
			gpqna.setPadding(new Insets(30));
			gpqna.setHgap(10);
			gpqna.setVgap(10);
			gpqna.add(label[6], 0, 0);
			gpqna.add( faq1, 0, 1);
			gpqna.add( ta, 0, 2);
			bp.setBottom(gpqna);
				
			y[1].setAlignment(Pos.CENTER);
			
			Label pay = new Label("Estimated Yearly Insurance Payment\n");
			pay.setFont(Font.font("Lucida Sans Unicode", 12));
			Label pay2 = new Label("");
			pay2.setFont(Font.font("Lucida Bright", FontWeight.BOLD, 16));
			
			x[2].getChildren().add(calcBt);
			x[2].getChildren().add(new Label("     "));
			x[2].getChildren().add(back_button);
			
			x[2].setAlignment(Pos.CENTER);
			y[2].getChildren().add(x[2]);
			
			y[1].getChildren().add(y[2]);
			y[1].getChildren().add(pay);
			y[1].getChildren().add(pay2);
			
			calcBt.setOnAction(e->{
				try {
					insertIntoCarInfor();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pay2.setText("RM "+calcIns());
			});
			
			back_button.setOnAction(e->{
				signIn_Menu.start(primaryStage);
			});
		
			bp.setCenter(y[1]);
			
			ta.setWrapText(true);
				
			addQna(faq1);
			
			faq1.setOnAction(e -> {ta.clear();  ta.appendText(faq1.getValue().ans);});
			openLink(gpqna);
					
			x[1].setPadding(new Insets(20));
			ImageView logo = new ImageView("logomama.png");
			x[1].getChildren().add(logo);
			logo.setFitHeight(50);
			logo.setFitWidth(190);
			
			
			bpMain.setTop(x[1]);
			bpMain.setCenter(bp);
			bpMain.setStyle("-fx-background-image: url(\"whitenew.jpg\");"
					+ "-fx-background-size : 800px 650px");
			
			ta.setStyle("-fx-background-color: null");
				
//			ScrollPane sp = new ScrollPane(bp);
			
			Scene scene = new Scene(bpMain);
			
			primaryStage.setTitle("Insurance Calculator");
			primaryStage.setScene(scene); // Place the scene in the stage	
			primaryStage.setResizable(false);
			primaryStage.show(); // Display the stage
		
		}
		
		public void createLabel() {
			for(int i =0;i<label.length;i++) {
				label[i] = new Label();
				label[i].setFont(Font.font("Constantia", FontWeight.BOLD, 12));
				label[i].setTextFill(Color.BLACK);
			
			}
			label[0].setText("Select Car");
			label[1].setText("Coverage Type");
			label[2].setText("Engine Capacity (cc)");
			label[3].setText("Car Price (RM)");
			label[4].setText("Location");
			label[5].setText("No Claims Discount");
			label[6].setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
			label[6].setText("Insurance FAQ");	
		}
		
		public String calcIns() {
			
			double disc=0;
			double ccDisc=13;
		
			if(cb[4].getValue()=="None - 0%")  disc = 0;
			else if(cb[4].getValue()=="First year - 25%")
				disc = 25.0;
				else if(cb[4].getValue()=="Second year - 30%")
					disc= 30.0;
					else if(cb[4].getValue()=="Thrid year - 38.33%")
						disc=38.33;
						else if(cb[4].getValue()=="Fourth year & above- 45%")
							disc=45.0;
			
			if(cb[2].getValue()=="1000")  
				ccDisc = 13;
			else if(cb[2].getValue()=="1000-1500")
				ccDisc = 10;
				else if(cb[2].getValue()=="1500-2000")
					ccDisc= 7;
					else if(cb[2].getValue()=="2000 and 2500")
						ccDisc=5;
						else if(cb[2].getValue()=="2500 and above")
							ccDisc=2;
			
			double carPrice = Double.parseDouble(price.getText());
			double ins = (3.1/100) * carPrice;
			double insDisc = ins - (disc/100)*ins;
			double insccDisc = insDisc - (ccDisc/100)*insDisc;
			String insStr = df.format(insccDisc);
			return insStr;
		
		}
		
		public void addComboBox() {
				
			cb[0].getItems().add("Sedan");
			cb[0].getItems().add("SUV");
			cb[0].getItems().add("Hatchback");
			cb[0].getItems().add("Coupe");
			
			cb[1].getItems().add("Comprehensive");
			cb[1].getItems().add("Third Party");
			
			cb[2].getItems().add("1000");
			cb[2].getItems().add("1000-1500");
			cb[2].getItems().add("1500-2000");
			cb[2].getItems().add("2000 and 2500");
			cb[2].getItems().add("2500 and above");
			
			cb[3].getItems().add("Malaysia");
			cb[3].getItems().add("India");
			cb[3].getItems().add("Japan");
			cb[3].getItems().add("Korea");
			cb[3].getItems().add("China");
			
			
			cb[4].getItems().add("None - 0%");
			cb[4].getItems().add("First year - 25%");
			cb[4].getItems().add("Second year - 30%");
			cb[4].getItems().add("Thrid year - 38.33%");
			cb[4].getItems().add("Fourth year & above- 45%");
		
		}
		
		public void addQna(ComboBox<qna> s) {
			qna [] qna = new qna[4];
			qna[0]= new qna("What is the purpose of car insurance?","Car insurance serves to offer financial protection to "
					+ "the parties involved in a traffic incident. The priority is the well-being of the other person involved in the accident. "
					+ "Depending on the type of coverage, some parties are not protected (usually yourself).");
			qna[1]= new qna("Is a car insurance compulsory in Malaysia?","Yes. Car insurance is compulsory in Malaysia. Without a car "
					+ "insurance policy, you are unable to obtain a road tax. No insurance means no road tax. Without a road tax, it is illegal to"
					+ " drive your car on public roads.");
			qna[2]= new qna("What happens if I get into an accident without car insurance?","This usually happens when you drive a "
					+ "car that you do not own (borrowed). You are not an insured driver. In this case, you will have to bear the financial "
					+ "damage yourself. Car rentals are usually insured (as long as you are the insured driver) so you don’t have to worry "
					+ "about that \r\n"
					+ "\r\n"
					+ "Do you really need full coverage for auto insurance?");
			qna[3]= new qna("How do I claim my insurance in the event of an accident?","In the event of an accident, check if you and the "
					+ "other party have suffered from any injuries first. You will need to contact your insurance provider, "
					+ "lodge a police report, document the accident, gather relevant documents, and submit the relevant documents to your insurer.");
		
			s.getItems().addAll(qna);
			
		}
		
		public void openLink(GridPane gpqna) {
			
			GridPane gp = new GridPane();
			gp.setHgap(10);
			
			String URL = "https://www.investopedia.com/terms/i/insurance.asp";    
			Hyperlink link = new Hyperlink();
			gp.add(link, 0, 0);
			link.setText("For more info click here");
			link.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	getHostServices().showDocument(URL);
			    }
			});
			
			String URLFB = "https://www.facebook.com/";
			
			HBox b = new HBox();
			Hyperlink fb = new Hyperlink();
			ImageView imgfb = new ImageView("fb.png");
			fb.setGraphic(imgfb);
			imgfb.setFitHeight(30);
			imgfb.setFitWidth(30);
			b.getChildren().add(fb);
			fb.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	getHostServices().showDocument(URLFB);
			    }
			});
			
			String URLINSTA = "https://www.instagram.com/";
			HBox c = new HBox();
			Hyperlink insta = new Hyperlink();
			ImageView imginsta = new ImageView("insta.png");
			insta.setGraphic(imginsta);
			imginsta.setFitHeight(30);
			imginsta.setFitWidth(30);
			c.getChildren().add(insta);
			
			insta.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	getHostServices().showDocument(URLINSTA);
			    }
			});
			
			String URLGMAIL = "https://mail.google.com/mail/";
			HBox d = new HBox();
			Hyperlink gmail = new Hyperlink();
			ImageView imggmail = new ImageView("gmail.png");
			gmail.setGraphic(imggmail);
			imggmail.setFitHeight(27);
			imggmail.setFitWidth(30);
			d.getChildren().add(gmail);
			gmail.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	getHostServices().showDocument(URLGMAIL);
			    }
			});
			
			gp.add(b,2,0 );
			gp.add(c, 3, 0);
			gp.add(d, 4, 0);
			
			Text spacing = new Text("\t\t\t\t\t\t\t");
			gp.add(spacing, 1, 0);
			
			gpqna.add(gp, 0, 3);
			
			
		}
			
		public static void main(String[] args) {
			launch(args);
		}
		
		public class qna {
			String ques;
			String ans;
			
			qna(String q, String a){
				ques = q;
				ans =a;	
			}

			
			public String toString() {
				return ques;
			}		
		}
	
		
		public void insertIntoCarInfor() throws SQLException, ClassNotFoundException {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			//Establish a connection
		
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sql_workbench" , "root", "Kabi@25lan");
			
			System.out.println("Database connected");
			
			String queryString = "insert into carInfo values(?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(queryString);
			statement.setString(1, tf_Username.getText());
			statement.setString(2, String.valueOf(cb[0].getValue()));
			statement.setString(3, String.valueOf(price.getText()));
			statement.setString(4, String.valueOf(cb[1].getValue()));
			statement.setString(5, String.valueOf(cb[3].getValue()));
			statement.setString(6, String.valueOf(cb[2].getValue()));
			statement.setString(7, String.valueOf(cb[4].getValue()));
			statement.execute();
		}
		
 }
}