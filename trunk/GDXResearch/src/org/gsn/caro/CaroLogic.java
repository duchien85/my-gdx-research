package org.gsn.caro;

public class CaroLogic {
	public final static int EMPTY = 0;
	public final static int pX = 1;
	public final static int pO = 2;
	public final static int SIZE = 15;
	
	private int[][] board;
	private int turn;
	private int count;
	public CaroLogic(){
		//init();
	}
	
	public void newGame(int first){
		board = new int[SIZE][SIZE];
		turn = first;
		count = 0;
	}
	
	public int getTurn(){
		return turn;
	}
	
	public boolean chessMove(int t, int x, int y){
		if (t != turn)
			return false;
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
			return false;
		if (board[x][y] != EMPTY)
			return false;
		board[x][y] = turn;
		turn = 3 - turn;
		count++;
		return true;
	}
	
	public int getCell(int x, int y){
		return board[x][y];
	}
	public int getCount(){
		return count;
	}
}
