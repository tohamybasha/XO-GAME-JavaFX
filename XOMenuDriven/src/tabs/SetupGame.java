package tabs;

import javafx.scene.paint.Color;
import game.Game;
import game.Player;
import game.TestGame;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SetupGame extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage pStage) throws Exception {
		
		Label menuu = new Label("\n\n Set up your game:\n\n");menuu.setFont(Font.font("Bizzak", FontWeight.EXTRA_BOLD, 40));menuu.setStyle("-fx-text-fill:#66FF33");
		HBox box = new HBox();
		//First Player
		Label player = new Label(" First Player: ");player.setFont(Font.font(30));player.setStyle("-fx-text-fill:#66FF33");
		
		Label x = new Label("X");x.setStyle("-fx-text-fill:#66FF33");x.setFont(Font.font("",FontWeight.BOLD,30));
		RadioButton x1 = new RadioButton();x.setGraphic(x1);x.setContentDisplay(ContentDisplay.LEFT);
		
		Label o = new Label("O");o.setStyle("-fx-text-fill:#66FF33");o.setFont(Font.font("",FontWeight.BOLD,30));
		RadioButton o1 = new RadioButton();o.setGraphic(o1);o.setContentDisplay(ContentDisplay.LEFT);
		
		box.getChildren().addAll(player,x,o);
		HBox.setMargin(x, new Insets(10));HBox.setMargin(o, new Insets(10));
		ToggleGroup g = new ToggleGroup();
		x1.setToggleGroup(g);
		o1.setToggleGroup(g);
		//GameMode
		HBox box2 = new HBox();
		Label mode = new Label(" GameMode:");mode.setFont(Font.font(30));mode.setStyle("-fx-text-fill:#66FF33");
		
		Label ai = new Label("Vs Computer");ai.setStyle("-fx-text-fill:#66FF33");ai.setFont(Font.font("",FontWeight.BOLD,30));
		RadioButton ai1 = new RadioButton();ai.setGraphic(ai1);ai.setContentDisplay(ContentDisplay.LEFT);
		
		Label twoPlayers = new Label("1 vs 1");twoPlayers.setStyle("-fx-text-fill:#66FF33");twoPlayers.setFont(Font.font("",FontWeight.BOLD,30));
		RadioButton two = new RadioButton();twoPlayers.setGraphic(two);twoPlayers.setContentDisplay(ContentDisplay.LEFT);
		
		box2.getChildren().addAll(mode,ai,twoPlayers);
		HBox.setMargin(ai, new Insets(10));HBox.setMargin(twoPlayers, new Insets(10));
		
		ToggleGroup g1 = new ToggleGroup();
		two.setToggleGroup(g1);
		ai1.setToggleGroup(g1);
		
		//Start button 
		Button start = new Button("Start");start.setFont(Font.font(30));start.setStyle("-fx-text-fill:#66FF33;-fx-background-color:grey");
		start.setPrefSize(200, 50);
		
		Player p = new Player();
		Game game = new Game();
		//Actions
		x1.setOnAction(e->{
			if(x1.isSelected()){
				p.setPlayer(1);
			}
			
		});
		o1.setOnAction(e->{
			if(o1.isSelected()){
				p.setPlayer(2);
			}
		});
		ai1.setOnAction(e->{
			if(ai1.isSelected()){
				game.setGameState(1);
			}
		});
		two.setOnAction(e->{
			if(two.isSelected()){
				game.setGameState(2);
			}
		});
		
		VBox menu = new VBox();
		menu.getChildren().addAll(menuu,box,box2);
	
		
		//BackGround
		Image img = new Image("back.gif");
		BackgroundImage i = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
		Background bg = new Background(i);
		
		BorderPane pane = new BorderPane();pane.setBackground(bg);
		pane.setCenter(menu);pane.setBottom(start);
		BorderPane.setMargin(start, new Insets(30,0,200,250));
		
		Scene scene = new Scene(pane,680,600);
		start.setOnAction(e->{
			if((x1.isSelected() || o1.isSelected()) && (ai1.isSelected() || two.isSelected()) ){
				TestGame tg = new TestGame(p,game);
				pStage.setScene(scene);
				try {
					tg.start(pStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				Label l = new Label("One field is missing!");l.setFont(Font.font(20));l.setTextFill(Color.BLUE);
				StackPane pa = new StackPane();pa.getChildren().add(l);
				Stage st = new Stage();
				Scene s = new Scene(pa);
				st.setScene(s);
				st.show();
				st.setTitle("Error");
			}
			
		});
		
		
		
		pStage.setScene(scene);
		pStage.setTitle("X-O V1.0");
		pStage.show();
		pStage.setResizable(false);
		
	}
	

}