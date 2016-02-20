package game.sixmensmorris.controller;

import java.awt.EventQueue;

import game.sixmensmorris.model.Game;
import game.sixmensmorris.view.MainView;

public class MainController {
	
	private static Game currentGame;
	private static MainView view;
	
//	public static void main (String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				currentGame = new Game(6);
//				view = new MainView(currentGame);
//
//				currentGame.setPiece('A', (char)0, currentGame.player());
//				currentGame.setPiece('A', (char)2, currentGame.computer());
//				currentGame.setPiece('B', (char)3, currentGame.player());
//				currentGame.setPiece('B', (char)3, currentGame.player()); // returns false
//
//				print(currentGame.toString());
//
//				currentGame.movePiece('A', (char)0, 'E', (char)4);
//				print(currentGame.toString());
//				currentGame.movePiece('E', (char)4, 'A', (char)0);
//				print(currentGame.toString());
//				currentGame.movePiece('E', (char)2, 'E', (char)4); // error, although no indication...
//				print(currentGame.toString());
//			}
//		});
//	}
	
	private static void print(String m) {
		System.out.println(m);
	}
	
}
