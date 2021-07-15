package util;

import java.awt.Rectangle;

import basic.Yield;

public class Methods {

	public double calculateDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	public static boolean isColliding(Yield e1, Yield e2)
	{
		Rectangle e1Mask = new Rectangle((int)e1.getX(),(int)e1.getY(),e1.getWidth(),e1.getHeight());
		Rectangle e2Mask = new Rectangle((int)e2.getX(),(int)e2.getY(),e2.getWidth(),e2.getHeight());
		
		return e1Mask.intersects(e2Mask);
	}
	
}
