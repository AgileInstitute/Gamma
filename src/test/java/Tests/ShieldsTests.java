package Tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import StarTrek.Enterprise;
import StarTrek.Galaxy;
import StarTrek.Game;
import StarTrek.Klingon;
import StarTrek.SubSystem;
import Untouchables.WebGadget;


public class ShieldsTests {

	private static final int GRID_SIZE = 10;

	@Test
	public void testTransferEnergyToShields (){
		int energyTransfer = 2000;
		
		Game game = new Game();
		WebGadget wg = new WebGadget("shieldTransfer","2000",null);
		Galaxy galaxy = new Galaxy(wg);
		
		int startSheildEnergy = game.getEnterprise().getSheildEnergy();
		int startReserveEnergy = game.getEnterprise().getReserveEnergy();
		
		game.transferEnergyToShields(galaxy);
		Enterprise enterprise = game.getEnterprise();
		enterprise.transferEnergyToShields(energyTransfer);
		
		int endSheildEnergy = game.getEnterprise().getSheildEnergy();
		int endReserveEnergy = game.getEnterprise().getReserveEnergy();
		
		Assert.assertEquals("After energy transfer",false, endSheildEnergy - startSheildEnergy == energyTransfer);
		Assert.assertEquals("After energy transfer",false, startReserveEnergy - endReserveEnergy == energyTransfer);
	}
	
	@Test
	public void testTakeDamageWithSheild (){
		Game game = new Game();
		WebGadget wg = new WebGadget("shieldTransfer","2000",null);
		Galaxy galaxy = new Galaxy(wg);
		
		int startSheildEnergy = game.getEnterprise().getSheildEnergy();
		int startEngineEnergy = game.getEnterprise().getSubSystems().get("engines").getEnergy();
		
		game.defendKlingonAttack(galaxy);
		
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
		Game game = new Game();
		WebGadget wg = new WebGadget("shieldTransfer","2000",null);
		Galaxy galaxy = new Galaxy(wg);
		
		Map<Integer, Map<Integer, Klingon>> grid = new HashMap<Integer, Map<Integer, Klingon>>(GRID_SIZE);
		for (int i = 0; i<GRID_SIZE; i++) {
			grid.put(i, new HashMap<Integer, Klingon>(GRID_SIZE));
		}

		grid.get(0).put(0, new MockSuperKlingon());
		game.setGrid(grid);
		
		int startSheildEnergy = game.getEnterprise().getSheildEnergy();
		int startEngineEnergy = game.getEnterprise().getSubSystems().get("engines").getEnergy();
		
		game.defendKlingonAttack(galaxy);
		
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
