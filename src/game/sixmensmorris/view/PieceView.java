package game.sixmensmorris.view;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import game.sixmensmorris.model.Piece;

public class PieceView extends Ellipse2D.Double
{
	private static final long serialVersionUID = -162314867417625047L;
	private static final int RADIUS = 20;
	private static final int CLICKRADIUS = 20;
	
	private Piece piece;
	
	public PieceView(int x, int y, Piece piece)
	{
		super(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
		this.piece = piece;
	}
	
	public boolean contains(Point click)
	{
		return (click.getX() > getCenterX() - CLICKRADIUS) &&
			(click.getX() < getCenterX() + CLICKRADIUS) &&
			(click.getY() > getCenterY() - CLICKRADIUS) &&
			(click.getY() < getCenterY() + CLICKRADIUS);
	}
	
	public Piece getPiece()
	{
		return piece;
	}
}
