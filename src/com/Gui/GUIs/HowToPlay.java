package com.Gui.GUIs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUI;
import com.Gui.TextPeice;

public class HowToPlay extends GUI {

	public HowToPlay() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/howtoplay.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		TextPeice[] tps = new TextPeice[1];
		tps[0] = new TextPeice("Back", 355,530, true, true, 435, 29);
		this.setTps(tps);
	}

	public void draw() {

		if (backT1 != null) {
			GL11.glColor3f(1f, 1f, 1f);
			backT1.bind();
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0.782, 0);
		GL11.glVertex2d(Game.WIDTH, 0);
		GL11.glTexCoord2d(0.782, 0.58);
		GL11.glVertex2d(Game.WIDTH, Game.HEIGHT);
		GL11.glTexCoord2d(0, 0.58);
		GL11.glVertex2d(0, Game.HEIGHT);
		GL11.glEnd();
		this.drawBasic();
	}

	public void action(int id) {
		switch (id) {
		case 0:
			Logic.gameState=Logic.GameState.MENU;
			break;
		}
	}
}
