package StarTrek;

import java.util.HashMap;
import java.util.Map;

public class Enterprise {
	
	private int xLocation;
	private int yLocation;
	private int reserveEnergy = 10000;
	private int sheildEnergy = 10000;
	private Map<String, SubSystem> subSystems;

	public Enterprise() {
		subSystems = new HashMap<String, SubSystem>();
		
		subSystems.put("shields", new SubSystem("shields", 1000));
		subSystems.put("engines", new SubSystem("engines", 1000));
		subSystems.put("lifeSupport", new SubSystem("lifeSupport", 1000));
		subSystems.put("weapons", new SubSystem("weapons", 1000));
		subSystems.put("navigation", new SubSystem("navigation", 1000));
		subSystems.put("transporter", new SubSystem("transporter", 1000));
		
		phaser = new Phaser();
		photons = new Photons();
	}
	private Phaser phaser;
	private Photons photons;
	
	public void transferEnergyToShields(int energyToTranser) {
		reserveEnergy -= energyToTranser;
		sheildEnergy += energyToTranser;
	}
	public void takeDamage(int damage) {
		sheildEnergy -= damage;
		if (sheildEnergy < 0) {
			subSystems.get("engines").reduceEnergy(damage);
			sheildEnergy = 0;
		}
	}
	
	public Phaser getPhaser() {
		return phaser;
	}
	public void setPhaser(Phaser phaser) {
		this.phaser = phaser;
	}
	public Photons getPhotons() {
		return photons;
	}
	public void setPhotons(Photons photons) {
		this.photons = photons;
	}
	public int getxLocation() {
		return xLocation;
	}
	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	public int getyLocation() {
		return yLocation;
	}
	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}
	public int getSheildEnergy() {
		return sheildEnergy;
	}
	public void setSheildEnergy(int sheildEnergy) {
		this.sheildEnergy = sheildEnergy;
	}
	public Map<String, SubSystem> getSubSystems() {
		return subSystems;
	}
	public void setSubSystems(Map<String, SubSystem> subSystems) {
		this.subSystems = subSystems;
	}
	public int getReserveEnergy() {
		return reserveEnergy;
	}
	public void setReserveEnergy(int reserveEnergy) {
		this.reserveEnergy = reserveEnergy;
	}

}
