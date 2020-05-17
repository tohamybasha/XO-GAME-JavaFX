package game;

public class Player {
	private int player; // 1 = x , 2 = o
	
	public Player(){
		
	}
	public Player(int player){
		this.player = player;
	}
	
	public void setPlayer(int player) {
		this.player = player;
	}
	public int getPlayer(){
	return player;
	}
	public void changePlayer()
	{
	if ( player ==1) player=2;
	else if(player==2) player=1;
	}

}
