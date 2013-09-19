package Tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.Game;
import StarTrek.Location;

public class MoveTest {
	
	Game game;
	
    @Before
    public void setUp() {
        game = new Game();
    }
    
	@Test
	public void moveSector() throws Exception {
		game.moveEnterprise(4,4);
		Location location = game.getEnterprise().getLocation();
		
		game.scanSector(location.getQuadrantX(), location.getQuadrantY());

		Assert.assertTrue(location.getSectorX() == 4);
		Assert.assertTrue(location.getSectorY() == 4);
	}
	
	@Test
	public void moveQuadrantSector() throws Exception {
		game.moveEnterprise(2,2,4,4);
		game.scanSector(2, 2);
		Location location = game.getEnterprise().getLocation();

		Assert.assertTrue(location.getSectorX() == 4);
		Assert.assertTrue(location.getSectorY() == 4);
	}

}
