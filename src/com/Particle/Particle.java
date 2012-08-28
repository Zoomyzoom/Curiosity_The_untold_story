package com.Particle;

import com.Entity.DynamicEntity;

public abstract class Particle extends DynamicEntity {

	public int time;
	public int id;
	public Float[] col = { 1f, 1f, 1f };
	public boolean flicker;

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

	public Float[] getCol() {
		return col;
	}

	public void setCol(Float[] col) {
		this.col = col;
	}

	public boolean isFlicker() {
		return flicker;
	}

	public void setFlicker(boolean flicker) {
		this.flicker = flicker;
	}

}
