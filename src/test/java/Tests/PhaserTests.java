package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.Enterprise;
import StarTrek.Game;
import StarTrek.Klingon;
import StarTrek.SubSystem;


public class PhaserTests {

	Game game;
	
    @Before
    public void setUp() {
        game = new Game();
    }

	@Test
	public void testTakeDamageWithSheild (){
		
		Klingon klingon = new Klingon();
		klingon.setAttackDamage(3000);
		
		game.setKlingon(klingon);
		game.setEnterprise(new MockEnterprise());
		game.getEnterprise().setSheildEnergy(1000);
		game.getEnterprise().getSubSystems().get("phasers").setEnergy(1000);
		
		game.defendKlingonAttack();

		SubSystem phasers = game.getEnterprise().getSubSystems().get("phasers");

		Assert.assertEquals("After defend", phasers.getEnergy(), -1000);
	}
}
