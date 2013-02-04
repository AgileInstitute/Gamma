package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {

	private int energy = 10000;
	private Shield shield = new Shield();
	private int minimumEnergy = 2000;
	private List<Subsystem> subsystems = new ArrayList<Subsystem>();

	public Ship() {
		subsystems.add(shield);
	}

	public Ship(List<Subsystem> subsystems) {
		subsystems.add(shield);
		this.subsystems  = subsystems;
	}


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

	public Subsystem takesHit(int energyHit) {
		int shieldEnergy = getShield().getEnergy();
		int remainEnergy = energyHit - shieldEnergy;
		
		getShield().takesHit(energyHit);
		
		if(getShield().isDown() && remainEnergy > 0)
		{
			Subsystem subSys = getRandomSubsystem();
			subSys.takesDamage(remainEnergy);
			return subSys;
		}
		return null;
	}
	

	public List<Subsystem> getAllSubsystems() {
		return this.subsystems;
	}

	public Subsystem getSubsystemByNumber(int i) {
		return this.subsystems.get(i-1);
	}

	public int getSubsystemRandomNumber()
	{
		int subSysSize = getAllSubsystems().size();		
		return new Random().nextInt(subSysSize-1)+1;		
	}
	
	public Subsystem getRandomSubsystem()
	{
		return getSubsystemByNumber(getSubsystemRandomNumber());
	}

	public void rest(int starDates) {
		for (Subsystem ss : this.subsystems) {
			ss.repair(starDates);
		} 
	}

}
