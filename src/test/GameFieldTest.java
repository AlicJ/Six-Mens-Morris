package test;

import game.sixmensmorris.model.GameField;
import game.sixmensmorris.model.Piece;

public class GameFieldTest
{
	public static void main (String[] args) {
		GameField field = new GameField(9);
		Piece p1 = new Piece(0);
		Piece p2 = new Piece(0);
		Piece p3 = new Piece(0);
		Piece c1 = new Piece(1);
		field.assign('A', (char)0, p1);
		field.assign('B', (char)1, p2);
		field.assign('C', (char)2, p3);
		field.assign('D', (char)3, p1); // Error
		field.assign('E', (char)4, c1);
		field.assign('F', (char)5, c1);
		field.assign('G', (char)6, c1);
		field.assign('G', (char)9, c1); // Error
		field.move('B', (char)1, 'E', (char) 22);
		System.out.println(field);
		System.out.println(field);
		field.move('A', (char)0, 'D', (char) 6);
		System.out.println(field);
		field.move('A', (char)0, 'D', (char) 6);
		System.out.println(field);
		field.move('A', (char)0, 'D', (char) 6);
		System.out.println(field);
		field.move('D', (char)0, 'E', (char) 2);
		System.out.println(field);
	}
}
