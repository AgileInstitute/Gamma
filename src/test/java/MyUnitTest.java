package test.java;

import main.java.Phaser;

import org.junit.*;

public class MyUnitTest {
<<<<<<< HEAD

=======
		
>>>>>>> First
	@Test
	public void phaserIsUndamagedAtCreation() {
		Phaser phaser = new Phaser();
		Assert.assertEquals(0, phaser.timeToRepair());		
	
	}


	@Test
<<<<<<< HEAD
		public void testSecondTest() {
			Assert.assertEquals(2 + 2, 4);

	}
=======
	public void phaserTakesDamage() {
		Phaser phaser = new Phaser();
		int energyHit = 300;
		phaser.takesDamage(energyHit);
		Assert.assertEquals(3, phaser.timeToRepair());		
	
	}
	
>>>>>>> First
}
