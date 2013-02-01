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

}
