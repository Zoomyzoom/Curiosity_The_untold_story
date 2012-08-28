/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gui.GUIs;

import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Gui.GUI;
import com.Gui.TextPeice;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * 
 * @author William
 */
public class Pause extends GUI {

	public Pause() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/paused.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		this.setX(Game.WIDTH / 2 - 150);
		this.setY(50);
		this.setWidth(300);
		this.setHeight(500);
		this.setTitle("");

		TextPeice[] tps = new TextPeice[3];
		tps[0] = new TextPeice("Resume", Game.WIDTH / 2 - 120, 200, true, false, Game.WIDTH / 2 + 120, 29);
		tps[1] = new TextPeice("Upgrade", Game.WIDTH / 2 - 120, 250, true, false, Game.WIDTH / 2 + 120, 29);
		tps[2] = new TextPeice("Quit", Game.WIDTH / 2 - 120, 300, true, false, Game.WIDTH / 2 + 120, 29);
		setTps(tps);
	}

	public void draw() {
		if (backT1 != null) {
			GL11.glColor3f(1f, 1f, 1f);
			backT1.bind();
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(0.5855, 0);
		GL11.glVertex2d(x + width, y);
		GL11.glTexCoord2d(0.5855, 0.975);
		GL11.glVertex2d(x + width, y + height);
		GL11.glTexCoord2d(0, 0.975);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();

		this.drawBasic();
	}

	public void action(int id) {
		switch (id) {
		case 0:
			Logic.gameState = Logic.GameState.GAME;
			break;
		case 1:
			Logic.gameState = Logic.GameState.UPGRADE;
			break;
		case 2:
			Game.song1.stop();
			Game.song2.stop();
			Game.song3.stop();
			Logic.gameState = Logic.GameState.MENU;
			break;
		}
	}
}
