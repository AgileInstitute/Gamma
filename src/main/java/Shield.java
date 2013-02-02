package main.java;

public class Shield extends Subsystem {

	private int energy;

	@Override
	public int energyPerStarDate() {
		// TODO Auto-generated method stub
		return 200;
	}

	public int getEnergy() {
		// TODO Auto-generated method stub
		return energy;
	}

	public void addEnergy(int energy) {
		this.energy += energy;
	}

	public void takesHit(int energyHit) {
		if (energyHit > this.energy) {
			this.energy = 0;	
		} else {
			this.energy -= energyHit;		
		}
		
	}

	public boolean isDown() {
		// TODO Auto-generated method stub
		return this.energy == 0;
	}
}
