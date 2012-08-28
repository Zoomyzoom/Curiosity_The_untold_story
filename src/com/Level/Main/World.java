package com.Level.Main;

import java.awt.Polygon;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.Entity.Entities.HealthPack;
import com.Entity.Entities.Kitten;
import com.Entity.Entities.Player;
import com.Entity.Entities.ShotgunBullet;
import com.Entity.Entities.Zombie;
import com.Entity.Entities.Bullet;
import com.Entity.Entities.ExplosiveBullet;
import com.Entity.Entities.SniperBullet;
import com.Evolution.Stats.StatsBase;
import com.Game.Main.Game;
import com.Game.Options.Options;
import com.Particle.PointParticle;

public class World {

	public int[] points = new int[5000];
	public PointParticle[] pps = new PointParticle[10000];
	public Zombie[] zs = new Zombie[15];
	public Kitten[] ks = new Kitten[5];
	public Bullet[] bs = new Bullet[500];
	public ExplosiveBullet[] ebs = new ExplosiveBullet[500];
	public SniperBullet[] sbs = new SniperBullet[500];
	public ShotgunBullet[] sgbs = new ShotgunBullet[500];
	public HealthPack[] hps = new HealthPack[5];

	public World(int[] points) {
		Random rand = new Random();
		this.points = points;
		/*
		 * for (int i = 0; i < zs.length/2; i++) { this.respawnZombie(i); }
		 */
		zs = new Zombie[Options.difficulty];
		ks = new Kitten[Options.difficulty];
		if(Options.kittyMode==2){
			for (int i = 0; i < zs.length; i++) {
				if(rand.nextBoolean()){
					zs[i] = new Zombie(20025-rand.nextInt(1000), -100, 38, 64);
				}else{
					zs[i] = new Zombie(12000+rand.nextInt(1000), -100, 38, 64);
				}
				zs[i].setId(i);
			}
		}else if(Options.kittyMode==0){
			for (int i = 0; i < ks.length; i++) {
				if(rand.nextBoolean()){
					ks[i] = new Kitten(20025-rand.nextInt(1000), -100, 64, 44);
				}else{
					ks[i] = new Kitten(12000+rand.nextInt(1000), -100, 64, 44);
				}
				ks[i].setId(i);
			}
		}else if(Options.kittyMode==1){
			for (int i = 0; i < ks.length/2; i++) {
				if(rand.nextBoolean()){
					ks[i] = new Kitten(20025-rand.nextInt(1000), -100, 64, 44);
				}else{
					ks[i] = new Kitten(12000+rand.nextInt(1000), -100, 64, 44);
				}
				ks[i].setId(i);
			}
			for (int i = 0; i < zs.length/2; i++) {
				if(rand.nextBoolean()){
					zs[i] = new Zombie(20025-rand.nextInt(1000), -100, 38, 64);
				}else{
					zs[i] = new Zombie(12000+rand.nextInt(1000), -100, 38, 64);
				}
				zs[i].setId(i);
			}
		}
		try {
			rockT = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/terrain/rock.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}

	public static World loadTestLevel() {
		int[] ps = new int[600];
		Random rand = new Random();
		for (int i = 0; i < ps.length; i++) {
			if (i < 11) {
				ps[i] = (int) (Math.sin(i / 3.0) * 80 + 10 + rand.nextInt(30));
			} else if (i > ps.length - 19) {
				ps[i] = (int) (Math.sin(i / 3.0) * 80 + 10 + rand.nextInt(30));
			} else {
				ps[i] = (int) (Math.sin(i / 3.0) * 80 + 260 + rand.nextInt(30));
			}
		}
		return new World(ps);
	}

	public boolean isPointInsideTerrain(Point p) {
		boolean debug = false;// Must tick player in render class

		Polygon poly = new Polygon();
		boolean collided = false;
		int x = 0;
		int i = 0;
		while (i < points.length) {
			if (points.length - i > 2) {

				poly.addPoint(x, points[i]);
				i++;
				x += 50;
				poly.addPoint(x, points[i]);
				poly.addPoint(x, Game.HEIGHT + 50);
				x -= 50;
				poly.addPoint(x, Game.HEIGHT + 50);
				x += 50;

				if (debug) {
					GL11.glColor3f(0, 1, 1);
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glVertex2d(poly.xpoints[0], poly.ypoints[0]);
					GL11.glVertex2d(poly.xpoints[1], poly.ypoints[1]);
					GL11.glVertex2d(poly.xpoints[2], poly.ypoints[2]);
					GL11.glVertex2d(poly.xpoints[3], poly.ypoints[3]);
					GL11.glEnd();
					GL11.glColor3f(1, 1, 1);
				}

				if (poly.contains(p.getX(), p.getY())) {
					collided = true;

				}
				poly = new Polygon();

				poly.addPoint(x, points[i]);
				i++;
				x += 50;
				poly.addPoint(x, points[i]);
				poly.addPoint(x, Game.HEIGHT + 50);
				x -= 50;
				poly.addPoint(x, Game.HEIGHT + 50);
				x += 50;

				if (debug) {
					GL11.glColor3f(1, 0, 1);
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glVertex2d(poly.xpoints[0], poly.ypoints[0]);
					GL11.glVertex2d(poly.xpoints[1], poly.ypoints[1]);
					GL11.glVertex2d(poly.xpoints[2], poly.ypoints[2]);
					GL11.glVertex2d(poly.xpoints[3], poly.ypoints[3]);
					GL11.glEnd();
					GL11.glColor3f(1, 1, 1);
				}

				if (poly.contains(p.getX(), p.getY())) {
					collided = true;
				} else {
					poly = new Polygon();
				}

			} else {
				i++;
			}
		}

		return collided;
	}

	public Texture rockT;

	public void render() {

		for (int i = 0; i < pps.length; i++) {
			if (pps[i] != null) {
				pps[i].draw();
			}
		}
		for (int i = 0; i < zs.length; i++) {
			if (zs[i] != null) {
				zs[i].draw();
			}
		}
		for (int i = 0; i < bs.length; i++) {
			if (bs[i] != null) {
				bs[i].draw();
			}
		}
		for (int i = 0; i < ks.length; i++) {
			if (ks[i] != null) {
				ks[i].draw();
			}
		}
		for (int i = 0; i < ebs.length; i++) {
			if (ebs[i] != null) {
				ebs[i].draw();
			}
		}
		for (int i = 0; i < sbs.length; i++) {
			if (sbs[i] != null) {
				sbs[i].draw();
			}
		}
		for (int i = 0; i < hps.length; i++) {
			if (hps[i] != null) {
				hps[i].draw();
			}
		}
		for (int i = 0; i < sgbs.length; i++) {
			if (sgbs[i] != null) {
				sgbs[i].draw();
			}
		}

		rockT.bind();
		int x = 0;
		int i = 0;
		while (i < points.length) {
			if (points.length - i > 2) {
				GL11.glColor3f(1, 1, 1);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2i(x, points[i]);
				x += 50;
				i++;
				GL11.glTexCoord2f(1, 0);
				GL11.glVertex2i(x, points[i]);
				GL11.glTexCoord2f(1, 1);
				GL11.glVertex2i(x, Game.HEIGHT + 50);
				x -= 50;
				GL11.glTexCoord2f(0, 1);
				GL11.glVertex2i(x, Game.HEIGHT + 50);
				x += 50;
				GL11.glEnd();

				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2i(x, points[i]);
				x += 50;
				i++;
				GL11.glTexCoord2f(1, 0);
				GL11.glVertex2i(x, points[i]);
				GL11.glTexCoord2f(1, 1);
				GL11.glVertex2i(x, Game.HEIGHT + 50);
				x -= 50;
				GL11.glTexCoord2f(0, 1);
				GL11.glVertex2i(x, Game.HEIGHT + 50);
				x += 50;
				GL11.glEnd();
			} else {
				i++;
			}
		}

	}

	public void addPointParticle(PointParticle p) {
		boolean done = false;
		for (int i = 0; i < pps.length; i++) {
			if (!done) {
				if (pps[i] == null) {
					p.setId(i);
					pps[i] = p;
					done = true;
				}
			}
		}
	}

	public void addKitten(Kitten e) {
		boolean done = false;
		for (int i = 0; i < ks.length; i++) {
			if (!done) {
				if (ks[i] == null) {
					e.setId(i);
					ks[i] = e;
					done = true;
				}
			}
		}
	}

	public void addZombie(Zombie e) {
		boolean done = false;
		for (int i = 0; i < zs.length; i++) {
			if (!done) {
				if (zs[i] == null) {
					e.setId(i);
					zs[i] = e;
					done = true;
				}
			}
		}
	}
	
	public void addShotgunBullet(ShotgunBullet b) {
		boolean done = false;
		for (int i = 0; i < sgbs.length; i++) {
			if (!done) {
				if (sgbs[i] == null) {
					b.setId(i);
					sgbs[i] = b;
					done = true;
				}
			}
		}
	}

	public void addExplosiveBullet(ExplosiveBullet b) {
		boolean done = false;
		for (int i = 0; i < ebs.length; i++) {
			if (!done) {
				if (ebs[i] == null) {
					b.setId(i);
					ebs[i] = b;
					done = true;
				}
			}
		}
	}

	public void addSniperBullet(SniperBullet b) {
		boolean done = false;
		for (int i = 0; i < bs.length; i++) {
			if (!done) {
				if (sbs[i] == null) {
					b.setId(i);
					sbs[i] = b;
					done = true;
				}
			}
		}
	}

	public void addHealthPack(HealthPack p) {
		boolean done = false;
		for (int i = 0; i < hps.length; i++) {
			if (!done) {
				if (hps[i] == null) {
					p.setId(i);
					hps[i] = p;
					done = true;
				}
			}
		}
	}
	
	public void addBullet(Bullet b) {
		boolean done = false;
		for (int i = 0; i < bs.length; i++) {
			if (!done) {
				if (bs[i] == null) {
					b.setId(i);
					bs[i] = b;
					done = true;
				}
			}
		}
	}

	public void respawnKitten(int id) {
		StatsBase.kills++;
		StatsBase.kittyKills++;
		Random rand = new Random();
		if(rand.nextInt(400)==4){
			this.addHealthPack(new HealthPack(zs[id].getX(), zs[id].getY()));
		}
		boolean gotx = false;
		int x = 0;
		while (!gotx) {
			if (rand.nextBoolean()) {
				x = (int) Game.p.x + Game.WIDTH / 2 + 200;
			} else {
				x = (int) Game.p.x - Game.WIDTH / 2 - 200;
			}
			if (x > 0 && x < points.length * 50) {
				if (x > Game.p.x + Game.WIDTH / 2) {
					gotx = true;
				}
				if (x < Game.p.x - Game.WIDTH / 2) {
					gotx = true;
				}
			}
			if (x < 200) {
				x = 200;
			}
			if (x > 30000) {
				x = 30000;
			}
		}
		ks[id] = null;
		if (!this.isPointInsideTerrain(new Point(x, 150))) {
			ks[id] = new Kitten(x, 80, 64, 44);
		} else {
			ks[id] = new Kitten(x, -150, 64, 44);
		}
		ks[id].setId(id);

		if (Game.p.equipedGun == Player.EquipedGun.MACHINE) {
			StatsBase.machineGunKills++;
		}
		if (Game.p.equipedGun == Player.EquipedGun.SNIPER) {
			StatsBase.sniperKills++;
		}
		if (Game.p.equipedGun == Player.EquipedGun.RPG) {
			StatsBase.rpgKills++;
		}
		if (Game.p.equipedGun == Player.EquipedGun.SHOTGUN) {
			StatsBase.shotgunKills++;
		}
	}

	public void respawnZombie(int id) {
		StatsBase.kills++;
		Random rand = new Random();
		
		if(rand.nextInt(400)==4){
			this.addHealthPack(new HealthPack(zs[id].getX(), zs[id].getY()));
		}
		
		boolean gotx = false;
		int x = 0;
		while (!gotx) {
			if (rand.nextBoolean()) {
				x = (int) Game.p.x + Game.WIDTH / 2 + 200;
			} else {
				x = (int) Game.p.x - Game.WIDTH / 2 - 200;
			}
			if (x > 0 && x < points.length * 50) {
				if (x > Game.p.x + Game.WIDTH / 2) {
					gotx = true;
				}
				if (x < Game.p.x - Game.WIDTH / 2) {
					gotx = true;
				}
			}
			if (x < 200) {
				x = 200;
			}
			if (x > 30000) {
				x = 30000;
			}
		}
		zs[id] = null;
		if (!this.isPointInsideTerrain(new Point(x, 150))) {
			zs[id] = new Zombie(x, 80, 38, 64);
		} else {
			zs[id] = new Zombie(x, -150, 38, 64);
		}
		zs[id].setId(id);

		if (Game.p.equipedGun == Player.EquipedGun.MACHINE) {
			StatsBase.machineGunKills++;
		}
		if (Game.p.equipedGun == Player.EquipedGun.SNIPER) {
			StatsBase.sniperKills++;
		}
		if (Game.p.equipedGun == Player.EquipedGun.RPG) {
			StatsBase.rpgKills++;
		}
		if (Game.p.equipedGun == Player.EquipedGun.SHOTGUN) {
			StatsBase.shotgunKills++;
		}
		
	}

	public void tick() {
		for (int i = 0; i < pps.length; i++) {
			if (pps[i] != null) {
				pps[i].tick();
			}
		}

		for (int i = 0; i < zs.length; i++) {
			if (zs[i] != null) {
				zs[i].tick();
			}
		}

		for (int i = 0; i < ks.length; i++) {
			if (ks[i] != null) {
				ks[i].tick();
			}
		}

		for (int i = 0; i < bs.length; i++) {
			if (bs[i] != null) {
				bs[i].tick();
			}
		}

		for (int i = 0; i < ebs.length; i++) {
			if (ebs[i] != null) {
				ebs[i].tick();
			}
		}

		for (int i = 0; i < sbs.length; i++) {
			if (sbs[i] != null) {
				sbs[i].tick();
			}
		}
		
		for (int i = 0; i < sgbs.length; i++) {
			if (sgbs[i] != null) {
				sgbs[i].tick();
			}
		}
		
		for (int i = 0; i < hps.length; i++) {
			if (hps[i] != null) {
				hps[i].tick();
			}
		}
	}
	public void addExtraKitty() {
		Kitten[] newKittys = new Kitten[ks.length+1];
		
		for(int i = 0;i<ks.length;i++){
			newKittys[i]=ks[i];
			
		}
		newKittys[newKittys.length-1] = new Kitten(3, -100, 64, 44);
		newKittys[newKittys.length-1].setId(newKittys.length-1);
		ks = newKittys;
	}

	public void addExtraZombie() {
		Zombie[] newZombies = new Zombie[zs.length+1];
		
		for(int i = 0;i<zs.length;i++){
			newZombies[i]=zs[i];
			
		}
		newZombies[newZombies.length-1] = new Zombie(3, -100, 38, 64);
		newZombies[newZombies.length-1].setId(newZombies.length-1);
		zs = newZombies;
	}
}
