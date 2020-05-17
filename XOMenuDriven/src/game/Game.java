package game;

public class Game {
	
	private int gameState ; //1 for computer / 2 for 1 vs 1 
	private int[][] gameBoard ;
	public Game(){
		//gameState = 1;
		gameBoard = new int[3][3];
	}
	public int getgameState()
	{
		return gameState;
	}
	public void setGameState(int g)
	{
		gameState=g;
	}
	public int[][] getgameBoard()
	{
		return gameBoard;
	}
	
	public int win(int[][] gameBoard)
	{   //X
		if(gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][0] == gameBoard[0][2] && gameBoard[0][0] ==1)return 1;
		if(gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][0] == gameBoard[1][2] && gameBoard[1][0] ==1)return 1;
		if(gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][0] == gameBoard[2][2] && gameBoard[2][0] ==1)return 1;
		if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] ==1)return 1;
		if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] ==1)return 1;
		if(gameBoard[0][0] == gameBoard[1][0] && gameBoard[0][0] == gameBoard[2][0] && gameBoard[0][0] ==1)return 1;
		if(gameBoard[0][1] == gameBoard[1][1] && gameBoard[0][1] == gameBoard[2][1] && gameBoard[0][1] ==1)return 1;
		if(gameBoard[0][2] == gameBoard[1][2] && gameBoard[0][2] == gameBoard[2][2] && gameBoard[0][2] ==1)return 1;
		//O
		if(gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][0] == gameBoard[0][2] && gameBoard[0][0] ==2)return 2;
		if(gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][0] == gameBoard[1][2] && gameBoard[1][0] ==2)return 2;
		if(gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][0] == gameBoard[2][2] && gameBoard[2][0] ==2)return 2;
		if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] ==2)return 2;
		if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] ==2)return 2;
		if(gameBoard[0][0] == gameBoard[1][0] && gameBoard[0][0] == gameBoard[2][0] && gameBoard[0][0] ==2)return 2;
		if(gameBoard[0][1] == gameBoard[1][1] && gameBoard[0][1] == gameBoard[2][1] && gameBoard[0][1] ==2)return 2;
		if(gameBoard[0][2] == gameBoard[1][2] && gameBoard[0][2] == gameBoard[2][2] && gameBoard[0][2] ==2)return 2;
		
		else return 0;
	}
	public int slotCheck(int[][]board)
	{
		for (int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(board[i][j] == 0) return 1;
				
		return 0;
	}
}
