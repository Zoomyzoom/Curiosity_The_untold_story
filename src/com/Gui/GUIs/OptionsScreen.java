package com.Gui.GUIs;

import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Game.Options.Options;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUI;
import com.Gui.TextPeice;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class OptionsScreen extends GUI {

	public OptionsScreen() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/options.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		this.setX(0);
		this.setY(0);
		this.setWidth(Game.WIDTH);
		this.setHeight(Game.HEIGHT);

		TextPeice[] tps = new TextPeice[3];
		tps[0] = new TextPeice("Musical Swag - " + Options.getMessage(Options.music), 250, 200, true, false, 550, 29);
		tps[1] = new TextPeice("Kitty Mode - " + Options.getMessage(Options.kittyMode), 250,260, true, false, 550, 29);

		tps[2] = new TextPeice("Back", 355, 500, true, false, 439, 29);
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
		GL11.glTexCoord2d(0.782, 0);
		GL11.glVertex2d(x + width, y);
		GL11.glTexCoord2d(0.782, 0.58);
		GL11.glVertex2d(x + width, y + height);
		GL11.glTexCoord2d(0, 0.58);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();

		this.drawBasic();

		if (!Mouse.isButtonDown(0)) {
			clicked = false;
		}
	}

	boolean clicked = true;

	public void action(int id) {
		if (!clicked) {
			switch (id) {
			case 0:
				Options.music = !Options.music;
				tps[0] = new TextPeice("Musical Swag - " + Options.getMessage(Options.music), 250, 200, true, false, 550, 29);
				clicked = true;
				break;
			case 1:
				Options.kittyMode++;
				if (Options.kittyMode > 2) {
					Options.kittyMode = 0;
				}
				tps[1] = new TextPeice("Kitty Mode - " + Options.getMessage(Options.kittyMode), 250, 260, true, false, 550, 29);
				clicked = true;
				break;
			case 9:
				clicked = true;
				break;
			case 99:
				clicked = true;
				break;
			case 999:
				clicked = true;
				break;
			case 2:
				Logic.gameState = Logic.GameState.MENU;
				break;
			case 9999:
				clicked = true;
				break;
			}
		}

	}
}
