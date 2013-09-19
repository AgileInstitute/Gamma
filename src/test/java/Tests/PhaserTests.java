package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.Enterprise;
import StarTrek.Game;


public class PhaserTests {

	Game game;
	
    @Before
    public void setUp() {
        game = new Game();
    }

	@Test
	public void testTakeDamageWithSheild (){
		
		game.getEnterprise().setSheildEnergy(1000);
		game.defendKlingonAttack();
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();

		Assert.assertEquals("After defend",endSheildEnergy, 800);

	}
}
