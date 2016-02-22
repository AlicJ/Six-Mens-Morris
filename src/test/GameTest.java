package test;

import game.sixmensmorris.model.Game;

public class GameTest
{
	public static void main(String[] args)
	{
		Game currentGame = new Game(6);
		
		currentGame.setPiece('A', (char)0, currentGame.player(0));
		currentGame.setPiece('A', (char)2, currentGame.player(1));
		currentGame.setPiece('B', (char)3, currentGame.player(0));
		currentGame.setPiece('B', (char)3, currentGame.player(0)); // returns false

		print(currentGame.toString());

		currentGame.movePiece('A', (char)0, 'E', (char)4);
		print(currentGame.toString());
		currentGame.movePiece('E', (char)4, 'A', (char)0);
		print(currentGame.toString());
		currentGame.movePiece('E', (char)2, 'E', (char)4); // error, although no indication...
		print(currentGame.toString());
	}
	
	public static void print(String string)
	{
		System.out.println(string);
	}
}
