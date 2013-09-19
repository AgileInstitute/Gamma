package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.Game;


public class DockTest {
	
	Game game;
	
    @Before
    public void setUp() {
        game = new Game();
    }


	@Test
	public void testIsDocked (){
		MockEnterprise mockEnterprise = new MockEnterprise();
		mockEnterprise.setDocked(false);
		game.setEnterprise(mockEnterprise);
		
		Assert.assertEquals("Should retun false if moving",false, game.getEnterprise().isDocked());
	}

}
