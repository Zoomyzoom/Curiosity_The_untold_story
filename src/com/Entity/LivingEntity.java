package com.Entity;

import java.util.Random;

import com.Game.Main.Game;

public abstract class LivingEntity extends DynamicEntity{

        public int fullhealth = 100;
	public int health = 100;
	public double walkspeed = 3;
	public double accelerationSpeed = 0.5;
	public int id;
	
	public void randomAccelerationX(){
		Random rand  = new Random();
		dx+=(0.5-rand.nextDouble());
	}
	
	public void randomAccelerationY(){
		Random rand  = new Random();
		dy+=(0.5-rand.nextDouble());
	}
	
	public void WalkToPlayer(){
		if(x-Game.p.getX()>0){
			if(Math.abs(dx)<walkspeed){
				dx-=accelerationSpeed;
			}
		}else{
			if(Math.abs(dx)<walkspeed){
				dx+=accelerationSpeed;
			}
		}
	}

	public double getWalkspeed() {
		return walkspeed;
	}

	public void setWalkspeed(double walkspeed) {
		this.walkspeed = walkspeed;
	}

	public double getAccelerationSpeed() {
		return accelerationSpeed;
	}

	public void setAccelerationSpeed(double accelerationSpeed) {
		this.accelerationSpeed = accelerationSpeed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
