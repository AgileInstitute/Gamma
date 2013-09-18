package StarTrek;

public class Photons extends Weapon {

	private int numberPhotons = 8;
	
	public Photons() {
	}
	
	public Photons(int numberPhotons) {
		this.numberPhotons = numberPhotons;
	}
	
	@Override
	public boolean isHit(int distance) {
		return !(calculateMissleDistanceToTarget(distance) > 7);
	}
	
	private int calculateMissleDistanceToTarget(int distance) {
		return (super.rnd(4) + ((distance / 500) + 1));
	}
	
	@Override
	public int calculateDamage(int amount, int distance) {
		return amount * (800 + rnd(50));
	}
	
	@Override
	public boolean canFire(int amount) {
		return numberPhotons  > 0;
	}

	@Override
	public void updateAfterFire(int amount) {
		numberPhotons -= amount;
	}
	
	@Override
	public String getMissMessage(int distance) {
		return "Torpedo missed Klingon at " + distance + " sectors...";
	}

	@Override
	public String getHitMessage(int distance, int damage) {
		return "Photons hit Klingon at " + distance + " sectors with " + damage + " units" + " || ";
	}

	@Override
	public String getCannotFireMessage() {
		return "No more photon torpedoes!";
	}
	
	public int getNumberPhotons() {
		return numberPhotons;
	}

	public void setNumberPhotons(int numberPhotons) {
		this.numberPhotons = numberPhotons;
	}



}
