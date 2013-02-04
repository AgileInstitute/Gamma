package main.java;

public abstract class Subsystem {

	private int damageInStarDates;

	public int timeToRepair() {
		return damageInStarDates;
	}

	public void takesDamage(int energyHit) {
		 damageInStarDates += energyHit / energyPerStarDate();
	
	}
	
	public int getDamageInStarDates() {
		
		return damageInStarDates;
	}

	public abstract int energyPerStarDate();

	public void repair(int starDate) {
		if (starDate < 1)
				return;
		
		if (starDate > damageInStarDates){
			starDate = damageInStarDates;
		}
		damageInStarDates -= starDate;
		
	}

	public boolean isActive() {
		return damageInStarDates == 0;
	}
}
