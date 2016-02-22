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
	
	private NodeView currentNode;
	private int id;
	
	public PieceView(int x, int y, int id)
	{
		super(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
		this.id = id;
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
	
	public int getId()
	{
		return id;
	}
}
