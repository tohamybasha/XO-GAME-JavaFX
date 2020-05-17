package tabs;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;

public class MainMenu extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage pStage) throws Exception {
		Label welcome = new Label("\t\t  Welcome");welcome.setStyle("-fx-text-fill:#66FF33");
		welcome.setFont(Font.font("Bizzak", FontWeight.EXTRA_BOLD, 50));
		
		Label menuu = new Label("\nMainMenu\n\n");menuu.setFont(Font.font("Bizzak", FontWeight.EXTRA_BOLD, 40));menuu.setStyle("-fx-text-fill:#66FF33");
		Button newGame = new Button("New Game");newGame.setPrefSize(200, 50);newGame.setStyle("-fx-background-color:#3366FF;-fx-text-fill:#66FF33");newGame.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Button scores = new Button("Score Board");scores.setPrefSize(200, 50);scores.setStyle("-fx-background-color:#3366FF;-fx-text-fill:#66FF33");scores.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Button exit = new Button("Exit");exit.setPrefSize(200, 50);exit.setStyle("-fx-background-color:#3366FF;-fx-text-fill:#66FF33");exit.setFont(Font.font("Arial",FontWeight.BOLD,20));
		
		VBox menu = new VBox();
		menu.getChildren().addAll(menuu,newGame,scores,exit);
		VBox.setMargin(newGame, new Insets(0,0,0,0));
		VBox.setMargin(scores, new Insets(10,0,0,0));
		VBox.setMargin(exit, new Insets(10,0,0,0));
	
		Image img = new Image("back.gif");
		BackgroundImage i = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
		Background bg = new Background(i);
		BorderPane pane = new BorderPane();pane.setBackground(bg);
		
		pane.setTop(welcome);pane.setCenter(menu);
		BorderPane.setMargin(menu, new Insets(10,0,20,30));
		
		Scene scene = new Scene(pane,680,600);
		newGame.setOnAction(e->{
			File f;
			try{
				f = new File("ScoreBoard.txt");
				Scanner in = new Scanner(f);
				in.nextLine();
				in.nextLine();
				in.close();
			}
			catch (FileNotFoundException e2) {
				f = new File("ScoreBoard.txt");
				PrintWriter fw;
				
				try {
					fw = new PrintWriter(f);
					fw.println("0");fw.println("0");
					fw.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
			SetupGame sg = new SetupGame();
			try {
				//pStage.setScene(scene);
				sg.start(pStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		scores.setOnAction(e->{
			pStage.close();
			ScoreBoard sg = new ScoreBoard();
			try {
				sg.start(pStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		exit.setOnAction(e->{
			pStage.close();
		});
		
		
		//Scene scene = new Scene(pane,680,600);
		pStage.setScene(scene);
		pStage.setTitle("X-O V1.0");
		pStage.show();
		pStage.setResizable(false);
		
	}
	

}
