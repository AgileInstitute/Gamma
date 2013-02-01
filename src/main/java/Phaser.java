package main.java;

public class Phaser {
	
	

	private int damage;

	public int timeToRepair() {
		return damage;
	}

	public void takesDamage(int energyHit) {
	 damage = energyHit / 100;
		
	}

}
