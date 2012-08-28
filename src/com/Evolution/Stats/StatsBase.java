package com.Evolution.Stats;

import com.Game.Main.Game;
import com.Game.Options.Options;
import com.Gui.GUIs.GUINotifications;

public class StatsBase {

	public static int machineGunKills = 0;
	public static int shotgunKills = 0;
	public static int sniperKills = 0;
	public static int rpgKills = 0;
	public static int blahKills = 0;

	public static int machineGunDamage = 0;
	public static int shotgunDamage = 0;
	public static int sniperDamage = 0;
	public static int rpgDamage = 0;
	public static int blahDamage = 0;

	public static int machineGunOriginalDamage = 25;
	public static int shotgunOriginalDamage = 30;
	public static int sniperOriginalDamage = 30;
	public static int rpgOriginalDamage = 20;
	public static int blahOriginalDamage = 0;

	public static int machineGunEvolve = 0;
	public static int shotgunEvolve = 0;
	public static int sniperEvolve = 0;
	public static int rpgEvolve = 0;
	public static int blahEvolve = 0;

	public static int evolveKills = 600;
	
	public static int kills = 0;
	public static int xp = 0;
	public static int kittyKills = 0;

	public static void init(){
		machineGunKills = 0;
		shotgunKills = 0;
		sniperKills = 0;
		rpgKills = 0;
		blahKills = 0;
		
		machineGunEvolve = evolveKills;
		shotgunEvolve = evolveKills;
		sniperEvolve = evolveKills;
		rpgEvolve = evolveKills;
		blahEvolve = evolveKills;
		
		shotgunDamage = shotgunOriginalDamage;
		machineGunDamage = machineGunOriginalDamage;
		sniperDamage = sniperOriginalDamage;
		rpgDamage = rpgOriginalDamage;
		blahDamage = blahOriginalDamage;
	}
	
	public static void tick() {
		if (machineGunDamage < 1)
			machineGunDamage = 1;
		if (sniperDamage < 1)
			sniperDamage = 1;
		if (rpgDamage < 1)
			rpgDamage = 1;
		if (shotgunDamage < 1)
			shotgunDamage = 1;
		if (blahDamage < 1)
			blahDamage = 1;

		if (shotgunEvolve < 0) {
			shotgunEvolve = evolveKills-100;
			shotgunDamage--;
			GUINotifications.setMessage("Zombies Have Evolved! Shotgun is less effective!", "", 0, 0, true);
			if(Options.kittyMode!=0){
				Game.world.addExtraZombie();
			}else{
				Game.world.addExtraKitty();
			}
		}
		if (machineGunEvolve < 0) {
			machineGunEvolve = evolveKills;
			machineGunDamage--;
			GUINotifications.setMessage("Zombies Have Evolved! Generic-Machinegun is less effective!", "", 0, 0, true);
			if(Options.kittyMode!=0){
				Game.world.addExtraZombie();
			}else{
				Game.world.addExtraKitty();
			}
		}
		if (sniperEvolve < 0) {
			sniperEvolve = evolveKills-150;
			sniperDamage--;
			GUINotifications.setMessage("Zombies Have Evolved! Sniper is less effective!", "", 0, 0, true);
			if(Options.kittyMode!=0){
				Game.world.addExtraZombie();
			}else{
				Game.world.addExtraKitty();
			}
		}
		if (rpgEvolve < 0) {
			rpgEvolve = evolveKills;
			rpgDamage--;
			GUINotifications.setMessage("Zombies Have Evolved! RPG is less effective!", "", 0, 0, true);
			if(Options.kittyMode!=0){
				Game.world.addExtraZombie();
			}else{
				Game.world.addExtraKitty();
			}
		}
		if (blahEvolve < 0) {
			blahEvolve = evolveKills;
			blahDamage--;
			GUINotifications.setMessage("Zombies Have Evolved! Blah is less effective!", "", 0, 0, true);
			if(Options.kittyMode!=0){
				Game.world.addExtraZombie();
			}else{
				Game.world.addExtraKitty();
			}
		}
	}

	
}
