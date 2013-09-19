package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.Enterprise;
import StarTrek.Game;

public class ShieldsTests {

	Game game;
	
    @Before
    public void setUp() {
        game = new Game();
    }

	@Test
	public void testTransferEnergyToShields (){
		int energyTransfer = 200;
		game.getEnterprise().setSheildEnergy(1000);
		game.getEnterprise().setReserveEnergy(1000);
		
		game.transferEnergyToShields(energyTransfer);
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();
		int endReserveEnergy = game.getEnterprise().getReserveEnergy();
		
		Assert.assertEquals("After energy transfer",endSheildEnergy, 1200);
		Assert.assertEquals("After energy transfer",endReserveEnergy, 800);
	}
	
	@Test
	public void testTakeDamageWithSheild (){
		
		game.getEnterprise().setSheildEnergy(1000);
		game.defendKlingonAttack();
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();

		Assert.assertEquals("After defend",endSheildEnergy, 800);
	}
	
	@Test
	public void testTakeDamageWithSheildDown (){
		game.setKlingon(new MockSuperKlingon());
		
		game.getEnterprise().setSheildEnergy(1000);
		game.defendKlingonAttack();
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();

		Assert.assertEquals("After defend",endSheildEnergy, 0);
	}
	@Test
	public void testTakeDamageWithSubsystem (){
		MockEnterprise mockEnterprise = new MockEnterprise();
		mockEnterprise.setSubSystemsName("engines");
		
		game.setEnterprise(mockEnterprise);
		game.getEnterprise().setSheildEnergy(1000);
		
		mockEnterprise.takeDamage(1100);
		double starDaysToRepair = game.getEnterprise().getSubsystemStarDatesToRepair("engines");
		Assert.assertEquals("After defend",true, starDaysToRepair > 0);
	}
	
	@Test
	public void testTakeDamageMultiple (){
		MockEnterprise mockEnterprise = new MockEnterprise();
		mockEnterprise.setSubSystemsName("engines");
		game.setEnterprise(mockEnterprise);
		game.getEnterprise().setSheildEnergy(1000);
		for (int i = 0; i <6 ; i ++){
			mockEnterprise.takeDamage(200);
		}
		double starDaysToRepair = game.getEnterprise().getSubsystemStarDatesToRepair("engines");

		Assert.assertEquals("After defend",true, starDaysToRepair > 0);
	}

}
