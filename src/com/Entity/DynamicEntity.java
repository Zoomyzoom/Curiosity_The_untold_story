package com.Entity;

import org.lwjgl.util.Point;

import com.Game.Main.Game;

public abstract class DynamicEntity extends Entity {

	public double dx, dy;

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void moveNonCollide(double dx, double dy) {
		x = x + dx;
		y = y + dy;
	}

	public boolean moveCollide(double dx, double dy) {
		Point p = new Point();
		p.setLocation((int) (x + (width / 2) + dx), (int) (y + height + dy));
		if (!Game.world.isPointInsideTerrain(p)) {
			x = x + dx;
			y = y + dy;
			return false;
		} else {
			double dy2 = dy;
			dy += 0.5;
			for (int i = 0; i < 15; i++) {
				p.setLocation((int) (x + (width / 2) + dx), (int) (y + height + dy2));
				if (!Game.world.isPointInsideTerrain(p)) {
					x = x + dx;
					y = y + dy2;
					this.dx = dx * 0.8;
					return true;
				}
				dy2 -= 0.5;
			}
		}
		this.dy = 0;
		return true;

	}
}
