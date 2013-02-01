package main.java;

public abstract class Subsystem {

	private int damageInStarDates;

	public int timeToRepair() {
		return damageInStarDates;
	}

	public void takesDamage(int energyHit) {
		 damageInStarDates += energyHit / energyPerStarDate();
	
	}

	public abstract int energyPerStarDate();

	public void repair(int starDate) {
		damageInStarDates -= starDate;
		
	}
}
