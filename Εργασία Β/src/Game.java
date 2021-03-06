
public class Game {
	int round;
	
	Game() {
		round = 0;
	}
	
	void setRound(int round) {
		this.round = round;
	}
	
	int getRound() {
		return this.round;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		// Create a 20x20 board with 6 guns, 10 supplies and 8 traps
		Board b = new Board(20, 20, 6, 10, 8);

		// Create 2 players with starting positions at the top left 
		// and bottom right and starting score 20
		HeuristicPlayer p1 = new HeuristicPlayer(1, "Player 1", b, 0, -10, -10);
		Player p2 = new Player(2, "Player 2", b, 0, 10, 10);
		
		// Set r
		p1.setR(3);
		
		// Add all the areas to the board
		int[][] area = {{-2, 2}, {2, -2}, {-2, -2}, {2, 2}};
		b.setWeaponAreaLimits(area);
		area = new int[][] {{-3, 3}, {3, -3}, {-3, -3}, {3, 3}};
		b.setFoodAreaLimits(area);
		area = new int[][] {{-4, 4}, {4, -4}, {-4, -4}, {4, 4}};
		b.setTrapAreaLimits(area);
		
		// Create all the weapons, supplies and traps
		b.createBoard(p1, p2);
		
		// Play the game until board has size 4x4
		// of a player dies
		boolean dead1 = false;
		boolean dead2 = false;
		do {
			int[] move1, move2;
			
			game.setRound(game.getRound() + 1);
			
			// Player 1 and 2 move
			move1 = p1.move(p2);
			dead2 = HeuristicPlayer.kill(p1, p2, 2);
			
			// If player2 is dead print player1 move and end the game
			if(dead2) {
				// Print round
				System.out.println("Round: " + game.getRound());			
				// Print the moves of the players
				System.out.println(p1.getName() + " moved to (" + move1[0] + "," + move1[1] +")");
				p1.statistics();
				System.out.println();
				break;
			}
			
			move2 = p2.move();
			dead1 = HeuristicPlayer.kill(p2, p1, 2);
			
			// Print round
			System.out.println("Round: " + game.getRound());			
			
			// Get the string representation of the board
			String[][] rep = b.getStringRepresentation();
			
			// Print board
			for (int i = 0; i < rep.length; i++) {
				for (int j = 0; j < rep[i].length; j++) {
					System.out.print(rep[i][j] + " ");
				}
				System.out.println();
			}
			
			// Print the moves of the players
			System.out.println(p1.getName() + " moved to (" + move1[0] + "," + move1[1] +")");
			p1.statistics();

			System.out.println(p2.getName() + " moved to (" + move2[0] + "," + move2[1] +
					   ") Picked: " + move2[2] + " weapons, " + move2[3] + " supplies, " +
						"Traped: " + move2[4] + " times.");
			
			System.out.println();
			
			// Resize board every 3 rounds
			if(game.getRound() % 3 == 2) {
				b.resizeBoard(p1, p2);
			}
		} while(b.getN() > 4 && b.getM() > 4 && !dead1 && !dead2);
		
		// Print final score
		System.out.println("-------------------------------------------");
		System.out.println("End of game. Rounds played: " + game.getRound());
		System.out.println();
		System.out.println(p1.getName() + " score: " + p1.getScore());
		System.out.println(p2.getName() + " score: " + p2.getScore());
		System.out.println();
		
		if(dead1) {
			System.out.println(p2.getName() + " killed " + p1.getName() + ".");
			System.out.println(p2.getName() + " wins.");
		} else if(dead2) {
			System.out.println(p1.getName() + " killed " + p2.getName() + ".");
			System.out.println(p1.getName() + " wins.");
		} else {
			System.out.println("Reached minimale board size.");
			if(p1.getScore() > p2.getScore()) {
				System.out.println(p1.getName() + " wins.");
			} else if(p2.getScore() > p1.getScore()) {
				System.out.println(p2.getName() + " wins.");
			} else {
				System.out.println("Draw.");
			}
		}
	}
}
