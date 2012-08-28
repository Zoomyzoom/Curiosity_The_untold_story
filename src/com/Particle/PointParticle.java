package com.Particle;

import org.lwjgl.opengl.GL11;

import com.Game.Main.Game;
import com.Game.Main.Render;

public class PointParticle extends Particle{

	public boolean notInWorld = false;
        
	public PointParticle(double x,double y,double dx,double dy,int time,Float[] col, boolean flicker){
		this.x = x;
		this.y = y;
		this.col = col;
		this.dx = dx;
		this.dy = dy;
		this.time = time;
		this.flicker = flicker;
	}
	
	public PointParticle(double x,double y,int time,Float[] col, boolean flicker){
		this.x = x;
		this.y = y;
		this.col = col;
		this.time = time;
		this.flicker = flicker;
	}
	
	public PointParticle(double x,double y,int time,Float[] col){
		this.x = x;
		this.y = y;
		this.time = time;
		this.col = col;
	}
	
	@Override
	public void draw() {
		GL11.glColor3f(col[0], col[1], col[2]);
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex2d(x, y);
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);
	}

	@Override
	public void tick() {
		this.moveNonCollide(dx, dy);
		time--;
		if(time<0){
                    if(notInWorld){
                        Render.particles[id] = null;
                    }else{
			Game.world.pps[id] = null;
		}
        }
	}

}
