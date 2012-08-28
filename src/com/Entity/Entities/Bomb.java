package com.Entity.Entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.Entity.DynamicEntity;
import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Particle.PointParticle;

public class Bomb extends DynamicEntity {

	int time = 120;
	Texture tex;

	public Bomb(double x, double y, double dx, double dy) {
		try {
			tex = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/Bomb.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		this.x = x; 
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.width = 32;
		this.height = 32;
	}

	@Override
	public void draw() {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		tex.bind();
		GL11.glTranslated(Render.transx, Render.transy, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1, 1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2d(x + width, y);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2d(x + width, y + height);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void tick() {
		dy += 0.2;
		this.moveCollide(dx, dy);
		time--;
		if (time < 0) {
			explode();
		}
	}

	private void explode() {
		Random rand = new Random();
		Float[] col = { 1f, 1f, 1f };
		for (int i = 0; i < 2000; i++) {
			double deltaX = 0;
			double deltaY = -5;
			double angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) * 3);
			deltaX = 6* Math.cos(angle) + ((0.5 - rand.nextDouble()) *6)+ ((0.5 - rand.nextDouble()) * 6);
			;
			deltaY = 6 * Math.sin(angle) + ((0.5 - rand.nextDouble()) * 6)+ ((0.5 - rand.nextDouble()) * 6);
			;
			Game.world.addPointParticle(new PointParticle(x, y, deltaX, deltaY, 20 + rand.nextInt(150), col, false));
		}
		Zombie[] bes = Game.world.zs;
		for (int i = 0; i < bes.length; i++) {
			if (bes[i] != null) {
				if (bes[i].x < x + 600 && bes[i].x > x - 600) {
					Game.world.respawnZombie(i);
				}
			}
		}
		Kitten[] ks = Game.world.ks;
		for (int i = 0; i < ks.length; i++) {
			if (ks[i] != null) {
				if (ks[i].x < x + 600 && ks[i].x > x - 600) {
					Game.world.respawnKitten(i);
				}
			}
		}
		

		Logic.bomb = null;
	}

}
