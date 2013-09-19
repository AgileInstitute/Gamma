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
		
		Enterprise enterprise = game.getEnterprise();
		enterprise.transferEnergyToShields(energyTransfer);
		
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
}
