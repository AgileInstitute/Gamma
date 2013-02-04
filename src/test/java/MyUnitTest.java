package test.java;

import main.java.Engine;
import main.java.Phaser;
import main.java.Ship;
import main.java.Subsystem;

import org.junit.Assert;
import org.junit.Test;

public class MyUnitTest {
	
	@Test
	public void strongHitBringsShieldDown() {
		Ship ship = new Ship();
		int energy = 1000;
		ship.tranferEnergyToShield(energy);
		int energyHit = 100000;
		ship.takesHit(energyHit);
		Assert.assertTrue(ship.getShield().isDown());
	}

	
	@Test
	public void shieldTakesDamageAndDrainsSomeOfItsEnergy() {
		Ship ship = new Ship();
		int energy = 1000;
		ship.tranferEnergyToShield(energy);
		int energyHit = 500;
		int shipEnergy = ship.getEnergy();
		int shieldEnergy = ship.getShield().getEnergy();
		ship.takesHit(energyHit);
		Assert.assertEquals(shipEnergy, ship.getEnergy());		
		Assert.assertEquals(shieldEnergy - energyHit, ship.getShield().getEnergy());		
	}


	@Test
	public void attemptToTransferMoreEnergyToShieldThanShipCanTransfer() {
		Ship ship = new Ship();
		int energy = 100000;
		ship.tranferEnergyToShield(energy);
		Assert.assertTrue(ship.getMinimumEnergy() == ship.getEnergy());		
		
	}

	@Test
	public void energyIsTransferedFromShipToShield() {
		Ship ship = new Ship();
		int energy = 1000;
		ship.tranferEnergyToShield(energy);
		Assert.assertEquals(1000, ship.getShield().getEnergy());		
		
	}

	@Test
	public void engineIsRepairedAfterAStarDate() {
		Subsystem engine = new Engine();
		int energyHit = 300;
		engine.takesDamage(energyHit);
		int currentEngineTimeToRepair = engine.timeToRepair();
		int starDate = 10;
		engine.repair(starDate);
		Assert.assertTrue(currentEngineTimeToRepair - starDate <= engine.timeToRepair());		
	
	}
	
	@Test
	public void shieldsDownTest()
	{
		Ship ship = new Ship();
		ship.tranferEnergyToShield(200);
		Subsystem subSys = ship.takesHit(300);		
		Assert.assertTrue(ship.getShield().getEnergy() == 0);
		Assert.assertTrue(subSys.getDamageInStarDates() >= 1);
	}

	@Test
	public void engineTakesDamage() {
		Subsystem engine = new Engine();
		int energyHit = 300;
		engine.takesDamage(energyHit);
		Assert.assertEquals(5, engine.timeToRepair());		
	
	}

	@Test
	public void phaserIsUndamagedAtCreation() {
		Phaser phaser = new Phaser();
		Assert.assertEquals(0, phaser.timeToRepair());		
	
	}

	@Test
	public void phaserTakesDamage() {
		Phaser phaser = new Phaser();
		int energyHit = 300;
		phaser.takesDamage(energyHit);
		Assert.assertEquals(3, phaser.timeToRepair());		
	
	}
	
	@Test
	public void phaserTakesMoreDamage() {
		Phaser phaser = new Phaser();
		int energyHit = 300;
		phaser.takesDamage(energyHit);
		energyHit = 200;
		phaser.takesDamage(energyHit);
		Assert.assertEquals(5, phaser.timeToRepair());		
	
	}
	
	
}
