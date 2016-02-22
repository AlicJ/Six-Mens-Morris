package game.sixmensmorris.view;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import game.sixmensmorris.model.Node;
import game.sixmensmorris.model.Piece;

public class PieceView extends Ellipse2D.Double
{
	private static final long serialVersionUID = -162314867417625047L;
	private static final int RADIUS = 30;
	private static final int CLICKRADIUS = 30;
	
	private Piece piece;
	private NodeView currentNode;
	
	public PieceView(int x, int y, Piece piece)
	{
		super(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
		this.piece = piece;
	}
	
	public void moveToNode(NodeView node)
	{		
		this.x = node.getCenterX() - RADIUS;
		this.y = node.getCenterY() - RADIUS;
		currentNode = node;
	}
	
	public boolean contains(Point click)
	{
		return (click.getX() > getCenterX() - CLICKRADIUS) &&
			(click.getX() < getCenterX() + CLICKRADIUS) &&
			(click.getY() > getCenterY() - CLICKRADIUS) &&
			(click.getY() < getCenterY() + CLICKRADIUS);
	}
	
	// Returns the current node at which the piece is placed
	public NodeView currentNode()
	{
		return currentNode;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public int getId()
	{
		return piece.id();
	}
}
