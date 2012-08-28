package com.Entity.Entities;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.Entity.DynamicEntity;
import com.Game.Main.Game;
import com.Game.Unlocks.Unlocked;

public class HealthPack extends DynamicEntity {

	int time = 0,id;
	Rectangle rect = new Rectangle();
	Texture tex;

	public HealthPack(double x, double y) {
		this.x = x;
		this.y = y;
		this.dy = -5;
		this.time = 60000;
		this.width = 32;
		this.height = 32;
		rect.setBounds((int) x, (int) y, (int) width, (int) height);
		try {
			tex = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/health/healthpack.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}

	@Override
	public void draw() {
		GL11.glEnable(GL11.GL_BLEND);
		tex.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1, 1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2f(1f, 0);
		GL11.glVertex2d(x + width, y);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2d(x + width, y + height);
		GL11.glTexCoord2f(0, 1f);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void tick() {
		if(this.moveCollide(dx, dy)){
			
		}
        
		dy+=0.2;
		time--;
		if(time<0){
			Game.world.hps[id] = null;
		}
		rect.setBounds((int)x, (int)y, (int)width, (int)height);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	
}
