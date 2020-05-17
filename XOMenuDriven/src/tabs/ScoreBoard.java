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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScoreBoard extends Application{
	private int xScore =0,oScore=0;
	private File f;
	@Override
	public void start(Stage pStage) throws Exception {
		
		try{
			f = new File("ScoreBoard.txt");
			Scanner in = new Scanner(f);
			 xScore = Integer.parseInt(in.nextLine());
			 oScore = Integer.parseInt(in.nextLine());
			 in.close();
		}
		catch (FileNotFoundException e) {
			PrintWriter fw = new PrintWriter(f);
			fw.println("0");fw.println("0");
			fw.close();
			
		}
		
		
		Label title = new Label("\t  X \t\t:\t  O");title.setFont(Font.font(40));title.setTextFill(Color.WHITE);
		Label xo = new Label("\t  "+xScore+"\t\t:\t  "+oScore);xo.setFont(Font.font(40));xo.setTextFill(Color.WHITE);
		VBox scores = new VBox();
		scores.getChildren().addAll(title,xo);
		scores.setStyle("-fx-background-color:black;-fx-border-color:red");
		scores.setPrefSize(200, 200);
		Button back = new Button("Back");back.setFont(Font.font("",FontWeight.EXTRA_BOLD,20));
		Button exit = new Button("Exit");exit.setFont(Font.font("",FontWeight.EXTRA_BOLD,20));
		Button reset = new Button("Reset Scores");reset.setFont(Font.font("",FontWeight.EXTRA_BOLD,20));
		HBox box = new HBox();
		box.getChildren().addAll(back,reset,exit);
		HBox.setMargin(back, new Insets(0,180,0,30));
		HBox.setMargin(reset, new Insets(0,180,0,0));
		
		Image img = new Image("back.gif");
		BackgroundImage i = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
		Background bg = new Background(i);
		Pane p = new Pane();p.setPrefSize(100, 100);
		BorderPane pane = new BorderPane();pane.setBackground(bg);
		pane.setBottom(box);pane.setCenter(scores);pane.setRight(p);
		BorderPane.setMargin(box, new Insets(20,0,20,0));
		BorderPane.setMargin(scores, new Insets(100,0,20,100));
		
		Scene scene = new Scene(pane,680,600);
		pStage.setScene(scene);
		pStage.setTitle("X-O V1.0");
		pStage.show();
		pStage.setResizable(false);
		back.setOnAction(e->{
			MainMenu m = new MainMenu();
			try {
				m.start(pStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		exit.setOnAction(e->{
			pStage.close();
			
		});
		reset.setOnAction(e->{
			try {
				pStage.close();
				PrintWriter fw = new PrintWriter(f);
				fw.println("0");fw.println("0");fw.close();
				this.start(pStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}
	

}
