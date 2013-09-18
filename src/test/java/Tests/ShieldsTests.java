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
		int energyTransfer = 2000;

		int startSheildEnergy = game.getEnterprise().getSheildEnergy();
		int startReserveEnergy = game.getEnterprise().getReserveEnergy();
		
		game.transferEnergyToShields(energyTransfer);
		
		Enterprise enterprise = game.getEnterprise();
		enterprise.transferEnergyToShields(energyTransfer);
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();
		int endReserveEnergy = game.getEnterprise().getReserveEnergy();
		
		Assert.assertEquals("After energy transfer",false, endSheildEnergy - startSheildEnergy == energyTransfer);
		Assert.assertEquals("After energy transfer",false, startReserveEnergy - endReserveEnergy == energyTransfer);
	}
	
	@Test
	public void testTakeDamageWithSheild (){
		
		int startSheildEnergy = game.getEnterprise().getSheildEnergy();
		int startEngineEnergy = game.getEnterprise().getSubSystems().get("engines").getEnergy();
		
		game.defendKlingonAttack();
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();
		int endEngineEnergy = game.getEnterprise().getSubSystems().get("engines").getEnergy();
		
//		System.out.println("startSheildEnergy="  + startSheildEnergy);
//		System.out.println("endSheildEnergy="  + endSheildEnergy);
//		
//		System.out.println("startEngineEnergy="  + startEngineEnergy);
//		System.out.println("endEngineEnergy="  + endEngineEnergy);
		
		Assert.assertEquals("After defend",true, startSheildEnergy - endSheildEnergy == 200);
		Assert.assertEquals("After defend",true, startEngineEnergy  == endEngineEnergy);
	}
	
	@Test
	public void testTakeDamageWithSheildDown (){
		game.setKlingon(new MockSuperKlingon());
		
		int startSheildEnergy = game.getEnterprise().getSheildEnergy();
		int startEngineEnergy = game.getEnterprise().getSubSystems().get("engines").getEnergy();
		
		game.defendKlingonAttack();
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();
		int endEngineEnergy = game.getEnterprise().getSubSystems().get("engines").getEnergy();
//		
//		System.out.println("startSheildEnergy="  + startSheildEnergy);
//		System.out.println("endSheildEnergy="  + endSheildEnergy);
//		
//		System.out.println("startEngineEnergy="  + startEngineEnergy);
//		System.out.println("endEngineEnergy="  + endEngineEnergy);
		
		Assert.assertEquals("After defend",true, endSheildEnergy == 0);
		Assert.assertEquals("After defend",true, endEngineEnergy  <0);
	}
}
