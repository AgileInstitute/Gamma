package StarTrek;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Enterprise {
	
	private int xLocation;
	private int yLocation;
	private boolean isDocked = false;
	private Location location;
	private int reserveEnergy = 10000;
	private int sheildEnergy = 10000;
	private Map<String, AbstractSubSystem> subSystems;
	private Random subsystemRandomizer;
	private Phaser phaser;
	private Photons photons;
	
	public Enterprise() {
		subSystems = new HashMap<String, AbstractSubSystem>();
		
		subSystems.put("shields", new ShieldsSubSystem("shields"));
		subSystems.put("engines", new StandardSubSystem("engines"));
		subSystems.put("lifeSupport", new StandardSubSystem("lifeSupport"));
		subSystems.put("phasers", new PhaserSubSystem("phasers"));
		subSystems.put("photons", new StandardSubSystem("photons"));
		subSystems.put("navigation", new StandardSubSystem("navigation"));
		subSystems.put("transporter", new StandardSubSystem("transporter"));
		
		subsystemRandomizer = new Random();
		
		phaser = new Phaser();
		photons = new Photons();
	}
	
	public void repair(int starDates) {
		
		for (int i = 0; i<starDates; i++) {
			for (AbstractSubSystem subSystem : subSystems.values()) {
				subSystem.repair(isDocked);
			}
		}
	}
	
	public void transferEnergyToShields(int energyToTranser) {
		reserveEnergy -= energyToTranser;
		sheildEnergy += energyToTranser;
	}
	
	public void takeDamage(int damage) {
		
		if (sheildEnergy > damage) {
			sheildEnergy -= damage;
		} else {
			int unblockedDamage = damage - sheildEnergy;
			determineSubSystemThatIsDamaged().takeDamage(unblockedDamage);
			sheildEnergy = 0; 
		}
	}
	
	protected AbstractSubSystem determineSubSystemThatIsDamaged() {
		int subSystemSelection = subsystemRandomizer.nextInt(subSystems.size());
		Object objects [] = subSystems.values().toArray(); 
		return (AbstractSubSystem) objects[subSystemSelection];
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
	public int getSheildEnergy() {
		return sheildEnergy;
	}
	public void setSheildEnergy(int sheildEnergy) {
		this.sheildEnergy = sheildEnergy;
	}
	public Map<String, AbstractSubSystem> getSubSystems() {
		return subSystems;
	}
	public void setSubSystems(Map<String, AbstractSubSystem> subSystems) {
		this.subSystems = subSystems;
	}
	public int getReserveEnergy() {
		return reserveEnergy;
	}
	public void setReserveEnergy(int reserveEnergy) {
		this.reserveEnergy = reserveEnergy;
	}
	public double getSubsystemStarDatesToRepair(String subSystem){
		return this.subSystems.get(subSystem).getStarDatesToRepair();
	}

	public void setDocked(boolean isDocked) {
		this.isDocked = isDocked;
	}

	public boolean isDocked() {
		return isDocked;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
