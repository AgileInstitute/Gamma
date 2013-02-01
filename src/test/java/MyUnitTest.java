package test.java;

import main.java.Engine;
import main.java.Phaser;
import main.java.Subsystem;

import org.junit.*;

public class MyUnitTest {

	@Test
	public void engineIsRepairedAfterAStarDate() {
		Subsystem engine = new Engine();
		int energyHit = 300;
		engine.takesDamage(energyHit);
		int currentEngineTimeToRepair = engine.timeToRepair();
		int starDate = 1;
		engine.repair(starDate);
		Assert.assertEquals(currentEngineTimeToRepair - 1, engine.timeToRepair());		
	
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
