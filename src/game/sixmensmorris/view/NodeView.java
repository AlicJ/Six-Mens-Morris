package game.sixmensmorris.view;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import game.sixmensmorris.model.Node;
import game.sixmensmorris.model.Piece;

public class NodeView extends Ellipse2D.Double
{
	private static final long serialVersionUID = -162314867417625047L;
	private static final int RADIUS = 10;
	private static final int CLICKRADIUS = 20;
	
	private Node node;
	
	public NodeView(int x, int y, Node node)
	{
		super(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
		this.node = node;
	}
	
	public boolean contains(Point click)
	{
		return (click.getX() > getCenterX() - CLICKRADIUS) &&
			(click.getX() < getCenterX() + CLICKRADIUS) &&
			(click.getY() > getCenterY() - CLICKRADIUS) &&
			(click.getY() < getCenterY() + CLICKRADIUS);
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public Piece getPiece()
	{
		return node.getPiece();
	}
}
