import StarTrek.Galaxy;
import StarTrek.Game;
import Untouchables.WebGadget;
import io.ConsoleReader;


public class StartGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleReader console = new ConsoleReader(System.in);
		Game game = new Game();
		String input;
		do {
			System.out.println("");
			System.out.println("(q=quit, a=attack, d=defend, t=Shield Transfer, s=scan");
			System.out.println("> ");
			input = console.readLine();
			
			if (input.equalsIgnoreCase("a")) {
				System.out.println("Attack");
				
			} else if (input.equalsIgnoreCase("d")) {
				System.out.println("Defend");
				String command = "defend";
				WebGadget webContext = new WebGadget(command, command, null);
				game.defendKlingonAttack();
			} else if (input.equalsIgnoreCase("t")) {
				System.out.println("Amount Energy to Transfer to Shield? >");
				String amount = console.readLine();
				String command = "shieldTransfer";
				WebGadget webContext = new WebGadget(command, amount, null);
				game.transferEnergyToShields(amount);
			} else if (input.equalsIgnoreCase("r")) {
				System.out.println("Rest");
			} else if (input.equalsIgnoreCase("m")) {
				System.out.println("Move");
			} else if (input.equalsIgnoreCase("s")) {
				System.out.println("Scan");
				String command = "scan";
				WebGadget webContext = new WebGadget(command, command, null);
				game.scanQuadrant();
			}

		} while (!input.equalsIgnoreCase("q"));
		
		System.out.println("Goodbye");
	}

}
