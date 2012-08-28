package com.Entity.Entities;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import com.Game.Main.Game;
import com.Game.Unlocks.Unlocked;
import com.Particle.Particle;
import com.Particle.PointParticle;

public class SniperBullet extends Particle {

	Rectangle rect = new Rectangle();

	public SniperBullet(double x, double y, double dx, double dy, int time) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.time = time;
		this.width = 5;
		this.height = 5;
		rect.setBounds((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void draw() {
		GL11.glColor3f(1, 1, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x + width, y);
		GL11.glVertex2d(x + width, y + height);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);
		Game.world.addPointParticle(new PointParticle(x+width/2,y+height/2,dx*0.5,dy*0.5,20,new Float[]{1f,1f,1f},false));
	}

	@Override
	public void tick() {
		if (this.moveCollide(dx, dy)) {
			if(!Unlocked.HotBullets){
                Game.world.sbs[id] = null;
		}
		}
		dy += 0.1;
		time--;
		if (time < 0) {
			Game.world.sbs[id] = null;
		}
		rect.setBounds((int) x, (int) y, (int) width, (int) height);
	}

}
