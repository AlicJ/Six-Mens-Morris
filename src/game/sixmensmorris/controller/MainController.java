package game.sixmensmorris.controller;

import game.sixmensmorris.model.Game;
import game.sixmensmorris.view.MainView;

public class MainController {
	
	private static Game model;
	private static MainView view;
	
	public static void main (String[] args) {
		
		model = new Game(6);
		view = new MainView();
		
		model.setPiece('A', (char)0, model.player());
		model.setPiece('A', (char)2, model.computer());
		model.setPiece('B', (char)3, model.player());
		model.setPiece('B', (char)3, model.player()); // returns false
		
		print(model.toString());
			
		model.movePiece('A', (char)0, 'E', (char)4);
		print(model.toString());
		model.movePiece('E', (char)4, 'A', (char)0);
		print(model.toString());
		model.movePiece('E', (char)2, 'E', (char)4); // error, although no indication...
		print(model.toString());
		
	}
	
	private static void print(String m) {
		System.out.println(m);
	}
	
}
