package main.java;

public class Ship {

	private int energy = 10000;
	private Shield shield = new Shield();
	private int minimumEnergy = 2000;

	public int getEnergy(){
		return energy ;
		
	}
	
	public void tranferEnergyToShield(int energy) {
		int energyToBePassed = 0;
		if (energy > this.energy - minimumEnergy) {
			energyToBePassed = this.energy - minimumEnergy;
		} else {
			energyToBePassed = energy;
		}
		this.energy -= energyToBePassed;
		getShield().addEnergy(energyToBePassed);
	}

	public Shield getShield() {
		// TODO Auto-generated method stub
		return shield ;
	}

	public int getMinimumEnergy() {
		// TODO Auto-generated method stub
		return minimumEnergy;
	}

	public void takesHit(int energyHit) {
		getShield().takesHit(energyHit);
		
	}

}
