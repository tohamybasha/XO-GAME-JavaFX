package game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import tabs.MainMenu;
import tabs.ScoreBoard;
import tabs.SetupGame;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestGame extends Application{
	private Player p;
	private Game g;
	int [][]board;
	public TestGame(Player p , Game g ){
		this.p = p;
		this.g = g;
		board = g.getgameBoard();//Virtual game table
	}
	Button [] buttons = new Button[9];
	ImageView []viewers = new ImageView[9];
	
	Image xImg = new Image("/XOSymbols/x.png");
	Image oImg = new Image("/XOSymbols/o.png");
	@Override
	public void start(Stage pStage) throws Exception {

		Label turn = new Label();
		//Creating ImageVeiws
		for(int i=1;i<=9;i++)
		{
			viewers[i-1] = new ImageView();
		}
		//Creating buttons
		for(int i=1;i<=9;i++)
		{
			buttons[i-1] = new Button("",viewers[i-1]);
			buttons[i-1].setPrefHeight(120);buttons[i-1].setPrefWidth(120);
			buttons[i-1].setStyle("-fx-background-color:#b4d4e6;-fx-border-color:#a38e53");
		}
//		turn.textProperty().addListener(ov->{
//			if(p.getPlayer() == 1)turn.setText("X Turn now");
//			else turn.setText("O Turn now");
//		});
		//Actions
		int i=1;
		for(i=1;i<=9;i++)
		{int z = (i-1)/3;
		 int x = i-1;
			buttons[i-1].setOnMouseClicked(e->{
				
				if(board[(z)][x%3] == 0)//Empty
				{
				if(p.getPlayer() == 1)	  {
					board[z][x%3]=1;viewers[x].setImage(xImg);
					if(g.getgameState()==2){
						p.changePlayer();turn.setText("O Turn now");
					}
					else if(g.getgameState() == 1){
						p.changePlayer();
						board = AIMove(board);
						turn.setText("Your turn ( X ) ");
					}
					
				if(g.win(board)!=0)
				{
					try {
					winResult(g,board,pStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}
				}
				
				else if(p.getPlayer()==2) {
					board[z][x%3]=2;  viewers[x].setImage(oImg);
					if(g.getgameState()==2){
						p.changePlayer();turn.setText("X Turn now");
					}
					else if(g.getgameState() == 1){
						p.changePlayer();
						board = AIMove(board);
						turn.setText("Your turn ( O ) ");
					}
				if(g.win(board)!=0)
				{try {
					winResult(g,board,pStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}}
				}
				
				}
				if(g.slotCheck(board)==0){
					 Label l = new Label();l.setText("All slot are full ,Restart the game?");l.setFont(Font.font("Bizzak", FontWeight.BOLD, 30));l.setTextFill(Color.LIGHTSEAGREEN);
					 Button yes = new Button("Yes");yes.setFont(Font.font("",20));
					 Button no = new Button("No");no.setFont(Font.font("",20));
					 HBox ch = new HBox();ch.getChildren().addAll(yes,no);HBox.setMargin(no, new Insets(0,0,0,10));
					 VBox pane = new VBox();pane.getChildren().addAll(l,ch);VBox.setMargin(ch, new Insets(10,10,10,250));
					 Scene sc = new Scene(pane);
					 Stage s = new Stage();s.setScene(sc);s.setTitle("Full");s.show();
					 no.setOnAction(e1->{
						 s.close();
						 pStage.close();
					 });
					yes.setOnAction(e2->{
						s.close();
							SetupGame sg = new SetupGame();
							try {
								sg.start(pStage);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						});
					 
				 }});}
		
		turn.setText("First "+(p.getPlayer()==1?"X":"O")+" turn");
		turn.setFont(Font.font("Bizzak", FontWeight.BOLD, 30));
		turn.setTextFill(Color.CRIMSON);
		
		StackPane label = new StackPane();
		label.getChildren().add(turn);
		// X-O Pane
		GridPane pane = new GridPane();

		int b=0;
		for(i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				pane.add(buttons[b], j, i);
				b++;
			}		
		pane.setPadding(new Insets(10,90,100,120));
		//Main Pane
		Button exit = new Button("Exit");exit.setFont(Font.font("",FontWeight.EXTRA_BOLD,20));
		Button reStart = new Button("Re-start");reStart.setFont(Font.font("",FontWeight.EXTRA_BOLD,15));
		Button back = new Button("Back");back.setFont(Font.font("",FontWeight.EXTRA_BOLD,15));
		Button scores = new Button("Scores");scores.setFont(Font.font("",FontWeight.EXTRA_BOLD,15));
		
		Label mustafa = new Label("Copyright by Moustafa Tohamy ;)");mustafa.setTextFill(Color.GREY.brighter());
		HBox bottom = new HBox();bottom.getChildren().addAll(back,scores,exit);
		HBox.setMargin(back, new Insets(0,180,0,30));
		HBox.setMargin(scores, new Insets(0,180,10,50));
		//Exit button
		exit.setOnAction(e->{
			pStage.close();
		});
		Image img = new Image("back.gif");
		BackgroundImage gg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
		Background bg = new Background(gg);
		VBox box = new VBox();
		box.setBackground(bg);
		box.getChildren().addAll(reStart,label,pane,bottom,mustafa);
		VBox.setMargin(pane, new Insets(0,0,0,50));
		VBox.setMargin(reStart, new Insets(10,0,0,10));
		Scene scene = new Scene(box,680,600);
		pStage.setScene(scene);
		pStage.show();
		pStage.setTitle("TicTacToe V1.0");
		pStage.setResizable(false);
		
		//Restart + Back + Scores >> buttons' actions
		reStart.setOnAction(e->{
			SetupGame sg = new SetupGame();
			try {
				pStage.setScene(scene);
				sg.start(pStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		back.setOnAction(e->{
			MainMenu m = new MainMenu();
			try {
				m.start(pStage);
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
		
	}
	public void winResult(Game g,int[][]board,Stage s) throws IOException
	{
		Label l = new Label();
		Button yes = new Button("Yes");yes.setFont(Font.font("",20));
		Button no = new Button("No");no.setFont(Font.font("",20));
		HBox ch = new HBox();ch.getChildren().addAll(yes,no);HBox.setMargin(no, new Insets(0,0,0,10));
		VBox pane = new VBox();pane.getChildren().addAll(l,ch);VBox.setMargin(ch, new Insets(10,10,10,250));
		Scene result = new Scene(pane);
		if(g.win(board)==1)
		{
			saveResult(1);
			l.setText("Winner is X !,Restart? ");
			l.setFont(Font.font("Bizzak", FontWeight.BOLD, 50));
			l.setTextFill(Color.CRIMSON);
		}
		if(g.win(board)==2)
		{
			saveResult(2);
			l.setText("Winner is O !,Restart? ");
			l.setFont(Font.font("Bizzak", FontWeight.BOLD, 50));
			l.setTextFill(Color.CRIMSON);
		}
		for(int i=1;i<=9;i++)
			buttons[i-1].setDisable(true);
		
		Stage s2 = new Stage();
		s2.setScene(result);s.setTitle("Result");
		s2.show();
		no.setOnAction(e->{
			s2.close();
		});
		yes.setOnAction(e->{
			SetupGame sg = new SetupGame();
			try {
				s2.close();
				s.setScene(s.getScene());
				sg.start(s);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	public void saveResult(int winner) throws IOException{
		try{
		File f = new File("ScoreBoard.txt");
		Scanner in = new Scanner(f);
		int xScore = Integer.parseInt(in.nextLine());
		int oScore = Integer.parseInt(in.nextLine());
		in.close();
		if(winner ==1){
			xScore++;
		}
		else {
			//in.nextInt();
			oScore++;
		}
		PrintWriter fw = new PrintWriter(f);
		fw.println(xScore);fw.println(oScore);
		fw.close();
		
	} catch (FileNotFoundException e) {
		File f = new File("ScoreBoard.txt");
		PrintWriter fw = new PrintWriter(f);
		fw.println("0");fw.println("0");
		fw.close();
	}
		
	}
	
	public int[][] AIMove(int[][]board){
		boolean done=false;
		while (done == false){
			int z = (int)(Math.random()*10%9);
			int y = z/3;
			int x = z%3;
			if(board[y][x] == 0)
			{
				if(p.getPlayer()==2) {
					board[y][x]=2;  viewers[z].setImage(oImg);break;
				}
				else if(p.getPlayer()==1) {
					board[y][x]=1;  viewers[z].setImage(xImg);break;
				}
				done = true;
				
			}
			
			else {
				if(g.slotCheck(board)==0){break;}
				done = false;
			}
		}
		p.changePlayer();
		return board;
	}}
//public void oneVsone(){
//	int i=1;
//	for(i=1;i<=9;i++)
//	{int z = (i-1)/3;
//	 int x = i-1;
//		buttons[i-1].setOnMouseClicked(e->{
//			
//			if(board[(z)][x%3] == 0)//Empty
//			{
//			if(p.getPlayer() == 1)	  {board[z][x%3]=1;viewers[x].setImage(xImg);p.changePlayer();turn.setText("O Turn now");
//			if(g.win(board)!=0)
//			{try {
//				pStage.setScene(winResult(g,board));
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}pStage.setTitle("Result");}}
//			
//			else if(p.getPlayer()==2) {board[z][x%3]=2;  viewers[x].setImage(oImg);p.changePlayer();turn.setText("X Turn now");
//			if(g.win(board)!=0)
//			{try {
//				pStage.setScene(winResult(g,board));
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}pStage.setTitle("Result");}}
//			
//			}
//			if(g.slotCheck(board)==0){
//				 Label l = new Label();l.setText("All slot are full.\nPlease,Restart the game.");l.setFont(Font.font("Bizzak", FontWeight.BOLD, 50));l.setTextFill(Color.LIGHTSEAGREEN);
//				 Scene sc = new Scene(l);
//				 Stage s = new Stage();
//				 s.setScene(sc);s.setTitle("Result");s.show();
//			 }});}
//	
//}