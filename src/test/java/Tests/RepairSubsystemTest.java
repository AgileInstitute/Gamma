package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.AbstractSubSystem;
import StarTrek.Game;
import StarTrek.Klingon;


public class RepairSubsystemTest {
	Game game;
	
    @Before
    public void setUp() {
        game = new Game();
    }

	@Test
	public void testRepairSubSystemDoecked (){
		
		MockEnterprise mockEnterprise = new MockEnterprise();
		mockEnterprise.setDocked(true);
		
		Klingon klingon = new Klingon();
		klingon.setAttackDamage(3000);
		
		game.setKlingon(klingon);
		game.setEnterprise(mockEnterprise);
		game.getEnterprise().setSheildEnergy(1000);
		
		game.defendKlingonAttack();

		AbstractSubSystem phasers = game.getEnterprise().getSubSystems().get("phasers");

		String starDates = phasers.getStarDatesToRepair() + "";
		Assert.assertEquals("After defend", "10.0", starDates);
		
		game.rest(10);

		starDates = phasers.getStarDatesToRepair() + "";
		Assert.assertEquals("After defend", "0.0" , starDates);
	}

}
