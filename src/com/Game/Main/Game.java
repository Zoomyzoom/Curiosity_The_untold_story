package com.Game.Main;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.Entity.Entities.Player;
import com.Evolution.Stats.StatsBase;
import com.Gui.GUIs.About;
import com.Gui.GUIs.Credits;
import com.Gui.GUIs.DeathScreen;
import com.Gui.GUIs.DifficultyScreen;
import com.Gui.GUIs.GunUpgrade;
import com.Gui.GUIs.HowToPlay;
import com.Gui.GUIs.Menu;
import com.Gui.GUIs.OptionsScreen;
import com.Gui.GUIs.Pause;
import com.Gui.GUIs.Story;
import com.Gui.GUIs.Upgrade;
import com.Level.Main.World;

public class Game {

	public static int WIDTH = 800, HEIGHT = 600;
	public static World world;
	public static Player p;

	public static TrueTypeFont font;
	public static TrueTypeFont font2;

	public static Sound song1;
	public static Sound song2;
	public static Sound song3;

	public Game() {

		setupDisplay();
		setupOpenGL();
		loadingScreen();
		p = new Player(300 * 50, -20);
		setupFonts();
		setupGUIs();
		setupSound();
		StatsBase.init();
		while (!Display.isCloseRequested()) {
			Logic.logic();
			Render.render();

			Display.update();
			Display.sync(60);
		}
	}

	public static void loadingScreen() {
		Texture loading = null;

		try {
			loading = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/loading.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		loading.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0.782, 0);
		GL11.glVertex2d(WIDTH, 0);
		GL11.glTexCoord2d(0.782, 0.58);
		GL11.glVertex2d(WIDTH, HEIGHT);
		GL11.glTexCoord2d(0, 0.58);
		GL11.glVertex2d(0, HEIGHT);
		GL11.glEnd();
		Display.update();
	}

	static void setupSound() {
		try {
			song1 = new Sound("res/sound/song1.wav");
			song2 = new Sound("res/sound/song2.wav");
			song3 = new Sound("res/sound/song3.wav");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// oggStream.loop(1f,0.3f);
	}

	public static void setupWorld() {
		world = World.loadTestLevel();

	}

	public static void setupFonts() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);

		awtFont = new Font("Times New Roman", Font.BOLD, 12);
		font2 = new TrueTypeFont(awtFont, false);

	}

	public static void setupOpenGL() {

		GL11.glPointSize(2f);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_NEAREST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		
	}

	private void setupDisplay() {
		try {
			Display.setInitialBackground(0.1f, 0.1f, 0.1f);
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Ludum Dare");
			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Game();

	}

	public static Pause pause;
	public static Upgrade upgrade;
	public static About about;
	public static GunUpgrade gunUpgrade;
	public static Menu menu;
	public static DeathScreen death;
	public static Credits credits;
	public static DifficultyScreen difficulty;
	public static HowToPlay howtoplay;
	public static OptionsScreen options;
	public static Story story;

	public static void setupGUIs() {
		try {
			Render.sky = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/terrain/sky.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			AL.destroy();
			System.exit(1);
		}
		
		pause = new Pause();
		upgrade = new Upgrade();
		about = new About();
		gunUpgrade = new GunUpgrade();
		menu = new Menu();
		death = new DeathScreen();
		credits = new Credits();
		difficulty = new DifficultyScreen();
		howtoplay = new HowToPlay();
		options = new OptionsScreen();
		story = new Story();
	}

}
