package com.Entity.Entities;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import com.Game.Main.Game;
import com.Game.Unlocks.Unlocked;
import com.Particle.Particle;

public class Bullet extends Particle{

	Rectangle rect = new Rectangle();
	
	public Bullet(double x,double y,double dx,double dy,int time) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.time = time;
		this.width = 5;
		this.height = 5;
		rect.setBounds((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void draw() {
		GL11.glColor3f(1, 0, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x+width, y);
		GL11.glVertex2d(x+width, y+height);
		GL11.glVertex2d(x, y+height);
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);
	}

	@Override
	public void tick() {
		if(this.moveCollide(dx, dy)){
			if(!Unlocked.HotBullets){
                    Game.world.bs[id] = null;
			}
                }
                dy+=0.2;
		time--;
		if(time<0){
			Game.world.bs[id] = null;
		}
		rect.setBounds((int)x, (int)y, (int)width, (int)height);
	}

}
