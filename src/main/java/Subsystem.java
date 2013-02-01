package main.java;

public abstract class Subsystem {

	private int damage;

	public int timeToRepair() {
		return damage;
	}

	public void takesDamage(int energyHit) {
		 damage += energyHit / energyPerStarDate();
	
	}

	public abstract int energyPerStarDate();
}
