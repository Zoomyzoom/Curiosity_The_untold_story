package com.Game.Options;

public class Options {

	public static boolean music = true;
	public static int kittyMode = 2;
	public static int difficulty = 15;

	public static String getMessage(boolean b) {
		if (b) {
			return "On";
		} else {
			return "Off";
		}
	}

	public static String getMessage(int id) {
		if (id == 0) {
			return "Kittys Only";
		}
		if (id == 1) {
			return "Both";
		}
		if (id == 2) {
			return "Off";
		}
		return "Off";
	}
}
