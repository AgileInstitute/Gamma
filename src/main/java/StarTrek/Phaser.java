package StarTrek;

public class Phaser extends Weapon {

	private int energyLevel = 10000;
	
	public Phaser() {
	}
	
	public Phaser(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
	@Override
	public boolean isHit(int distance) {
		return !(distance > 4000);
	}

	@Override
	public int calculateDamage(int amount, int distance) {
		int damage = amount - (((amount /20)* distance /200) + super.rnd(200));
		if (damage < 1) 
			damage = 1;
		return damage;
	}
	
	@Override
	public boolean canFire(int amount) {
		return energyLevel >= amount;
	}

	@Override
	public void updateAfterFire(int amount) {
		energyLevel -= amount;
	}
	
	@Override
	public String getMissMessage(int distance) {
		return "Klingon out of range of phasers at " + distance + " sectors...";
	}

	@Override
	public String getHitMessage(int distance, int damage) {
		return "Phasers hit Klingon at " + distance + " sectors with " + damage + " units" + " || ";
	}

	@Override
	public String getCannotFireMessage() {
		return "Insufficient energy to fire phasers!";
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}



}
