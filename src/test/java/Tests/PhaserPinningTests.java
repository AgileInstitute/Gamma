package Tests;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import StarTrek.Game;
import StarTrek.Weapon;

public class PhaserPinningTests {
    private Game game;
    private MockGalaxy context;

    private static final int EnergyInNewGame = 10000;

    @After
    public void removeTheMockRandomGeneratorForOtherTests_IReallyWantToRefactorThatStaticVariableSoon() {
        Weapon.generator = new Random();
    }

    @Before
    public void setUp() {
        game = new Game();
        context = new MockGalaxy();
        context.setValueForTesting("command", "phaser");
    }

    @Test
    public void PhasersFiredWithInsufficientEnergy() {
        context.setValueForTesting("amount", (EnergyInNewGame + 1) + "");
        game.executeCommand(context);
        Assert.assertEquals("Insufficient energy to fire phasers! || ",
            context.getAllOutput());
    }

    @Test
    public void PhasersFiredWhenKlingonOutOfRange_AndEnergyExpendedAnyway() {
        int maxPhaserRange = 4000;
        int outOfRange = maxPhaserRange + 1;
        context.setValueForTesting("amount", "1000");
        context.setValueForTesting("target", new MockKlingon(outOfRange));
        game.executeCommand(context);
        Assert.assertEquals("Klingon out of range of phasers at " + outOfRange + " sectors... || ",
            context.getAllOutput());
        Assert.assertEquals(EnergyInNewGame - 1000, game.EnergyRemaining());
    }

    @Test
    public void PhasersFiredKlingonDestroyed() {
        MockKlingon klingon = new MockKlingon(2000, 200);
        context.setValueForTesting("amount", "1000");
        context.setValueForTesting("target", klingon);
        Weapon.generator = new MockRandom();
        game.executeCommand(context);
        System.out.println(context.getAllOutput());
        Assert.assertEquals("Phasers hit Klingon at 2000 sectors with 400 units || Klingon destroyed! || ",
            context.getAllOutput());
        Assert.assertEquals(EnergyInNewGame - 1000, game.EnergyRemaining());
        Assert.assertTrue(klingon.deleteWasCalled());
    }

    @Test
    public void PhasersDamageOfZeroStillHits_AndNondestructivePhaserDamageDisplaysRemaining() {
        String minimalFired = "0";
        String minimalHit = "1";
        context.setValueForTesting("amount", minimalFired);
        context.setValueForTesting("target", new MockKlingon(2000, 200));
        Weapon.generator = new MockRandom();
        game.executeCommand(context);
        Assert.assertEquals("Phasers hit Klingon at 2000 sectors with " +
            minimalHit + " units || Klingon has 199 remaining || ",
            context.getAllOutput());
        // Isn't this also a bug?  I *ask* to fire zero, and I still hit?
        // Acknowledge it, log it, but don't fix it yet!
    }

}

