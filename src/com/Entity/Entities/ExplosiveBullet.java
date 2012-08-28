package com.Entity.Entities;

import java.awt.Rectangle;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.Evolution.Stats.StatsBase;
import com.Game.Main.Game;
import com.Particle.Particle;
import com.Particle.PointParticle;

public class ExplosiveBullet extends Particle {

	Rectangle rect = new Rectangle();

	public ExplosiveBullet(double x, double y, double dx, double dy, int time) {
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
		GL11.glColor3f(0, 0, 1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x + width, y);
		GL11.glVertex2d(x + width, y + height);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();
		GL11.glColor3f(1, 1, 1);
	}

	@Override
	public void tick() {
		if (this.moveCollide(dx, dy)) {
			this.explode();
		}
		dy+=0.2;
		time--;
		if(time<0){
			Game.world.bs[id] = null;
		}
		rect.setBounds((int)x, (int)y, (int)width, (int)height);
	}

	public void explode() {
		Random rand = new Random();
		Float[] col = { 1f, 1f, 1f };
		for (int i = 0; i < 100; i++) {
			double deltaX = 0;
			double deltaY = -5;
			double angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) * 3);
			deltaX = 2 * Math.cos(angle) + ((0.5 - rand.nextDouble()) * 6) + ((0.5 - rand.nextDouble()) * 6);
			;
			deltaY = 2 * Math.sin(angle) + ((0.5 - rand.nextDouble()) * 6) + ((0.5 - rand.nextDouble()) * 6);
			;
			if(rand.nextInt(10)==4){
				col = new Float[]{ 1f, 0f, 0f };
			}else if(rand.nextInt(10)==7){
				col = new Float[]{ 1f, 1f, 0f };
			}
			Game.world.addPointParticle(new PointParticle(x, y, deltaX, deltaY, 5+rand.nextInt(20), col, false));
		}
		Zombie[] bes = Game.world.zs;
		for (int i = 0; i < bes.length; i++) {
			if (bes[i] != null) {
				if (bes[i].x < x + 80 && bes[i].x > x - 80) {
					bes[i].health -= 20;
					if(bes[i].health<0){
						StatsBase.rpgEvolve--;
					}
				}
			}
		}
		Game.world.ebs[id] = null;

	}

}
