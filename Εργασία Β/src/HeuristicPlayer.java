import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A player who plays with strategy
 */
public class HeuristicPlayer extends Player{
	private ArrayList<Integer[]> path; // Informations about the players past moves
	
	/**
	 * path getter
	 * @return path
	 */
	ArrayList<Integer[]> getPath() {
		return path;
	}
	
	/**
	 * path sette
	 * @param path
	 */
	void setPath(ArrayList<Integer[]> path) {
		this.path = path;
	}
	
	/**
	 * Empty constructor
	 */
	HeuristicPlayer() {
		super();
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * HeuristicPlayer constructor with initial path list and empty Player (superclass) constructor
	 * @param path informations about the players past moves
	 */
	HeuristicPlayer(ArrayList<Integer[]> path) {
		super();
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * HeuristicPlayer constructor with all except Weapon arguments (for the player superclass)
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 */
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y) {
		super(id, name, board, score, x, y);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * HeuristicPlayer constructor with all except Weapon arguments (for the player superclass)
	 * and initial path list
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 * @param path informations about the players past moves
	 */
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y, ArrayList<Integer[]> path) {
		super(id, name, board, score, x, y);
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * HeuristicPlayer constructor with each variable of Player class as argument
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 * @param bow bow of the player
	 * @param pistol pistol of the player
	 * @param sword sword of the player
	 */
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword) {
		super(id, name, board, score, x, y, bow, pistol, sword);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * HeuristicPlayer constructor with each variable of Player class as argument
	 * and initial path list
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 * @param bow bow of the player
	 * @param pistol pistol of the player
	 * @param sword sword of the player
	 * @param path informations about the players past moves
	 */
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword, ArrayList<Integer[]> path) {
		super(id, name, board, score, x, y, bow, pistol, sword);
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * HeuristicPlayer constructor with player object as argument
	 * @param player Player object
	 */
	HeuristicPlayer(Player player) {
		super(player);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * HeuristicPlayer constructor with player object as argument
	 * and initial path list
	 * @param player Player object
	 * @param path informations about the players past moves
	 */
	HeuristicPlayer(Player player, ArrayList<Integer[]> path) {
		super(player);
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * Calculates the Euclidean distance between this player
	 * and the player p
	 * @param p player from who we want to calculate the distance
	 * @return Euclidean distance between the two players
	 */
	float playersDistance(Player p) {
		int currentX = this.x < 0 ? this.x+1 : this.x;
		int currentY = this.y < 0 ? this.y+1 : this.y;
		int opponentX = p.x < 0 ? p.x+1 : p.x;
		int opponentY = p.y < 0 ? p.y+1 : p.y;
		
		return (float) Math.sqrt((currentX - opponentX) * (currentX - opponentX) +
								 (currentY - opponentY) * (currentY - opponentY));
	
//		int dx = Math.abs(currentX - opponentX);
//		int dy = Math.abs(currentY - opponentY);
//		
//		return Math.max((float) dx, (float) dy);
	}
	
	/**
	 * Calculate a score for a move based on the formula
	 * if has a pistol takes 10 / dist points
	 * if takes a pistol takes 5 points
	 * if takes a bow or a sword takes 1 point
	 * if wins K points from food take 0.5 * K points
	 * if losses K points from a trap looses 0.5 * K points
	 * @param dice next move
	 * @param p opponent
	 * @return score for the next move
	 */
	double evaluate(int dice, Player p) {
		// Calculate the next position of the player
		int newX=this.x, newY=this.y;
		// up
		if(dice == 1 || dice == 8 ||dice == 2) {
			if(this.y-1 == 0) {
				newY = -1;
			} else {
				newY = this.y - 1;
			}
		}
		
		// down
		if(dice == 6 || dice == 5 ||dice == 4) {
			if(this.y+1 == 0) {
				newY = 1;
			} else {
				newY = this.y + 1;
			}
		}
		
		// left
		if(dice == 6 || dice == 7 ||dice == 8) {
			if(this.x-1 == 0) {
				newX = -1;
			} else {
				newX = this.x - 1;
			}
		}
		
		// right
		if(dice == 2 || dice == 3 ||dice == 4) {
			if(this.x+1 == 0) {
				newX = 1;
			} else {
				newX = this.x + 1;
			}
		}
			
		
		// Check if the space is a weapon
		boolean pistolBlock = false;
		boolean bowBlock = false;
		boolean swordBlock = false;
		Weapon[] weapons = this.board.getWeapons();
		for(int i = 0; i < weapons.length; i++) {
			if(weapons[i].getX() == newX && weapons[i].getY() == newY) {
				// Check if the weapon is for this player
				if(weapons[i].getPlayerID() == this.id) {
					String type = weapons[i].getType();
					switch(type) {
					case "pistol":
						if(this.pistol == null) {
							pistolBlock = true;
						}
						break;
					case "bow":
						if(this.bow == null) {
							bowBlock = true;
						}
						break;
					case "sword":
						if(this.sword == null) {
							swordBlock = true;
						}
						break;
					}
				}
			}
		}
		
		// Check if the space is a food
		int foodScore = 0;
		Food[] food = this.board.getFood();
		for(int i = 0; i < food.length; i++) {
			if(food[i].getX() == newX && food[i].getY() == newY) {
				foodScore = food[i].getPoints();
			}
		}
		
		// Check if the space is a trap
		int trapScore = 0;
		Trap[] traps = this.board.getTraps();
		for(int i = 0; i < traps.length; i++) {
			if(traps[i].getX() == newX && traps[i].getY() == newY) {
				// Check if the player has the weapon to get away without loosing points
				if(traps[i].getType() == "animals") {
					if(this.bow == null) {
						 trapScore = traps[i].getPoints();
					} 
				}
				
				if(traps[i].getType() == "ropes") {
					if(this.sword == null) {
						trapScore = traps[i].getPoints();
					}
				}
			}
		}
		
		// Check if player has a gun
		double ret; 
		if(super.pistol != null) {
			float dist = this.playersDistance(p);
			ret = 10 / dist;
		} else {
			ret = 0;
		}
		
		if(bowBlock || swordBlock) {
			ret += 1;
		} else if(pistolBlock) {
			ret += 5;
		}
		
		
		ret += 0.5 * (trapScore + foodScore);
		
		return ret;
	}
	
	/**
	 * Check if the distance between 2 players are smaller than d
	 * if yes return true else false
	 * @param player1 
	 * @param player2
	 * @param d smallest distance that a player can't kill
	 * @return if a player can kill
	 */
	static boolean kill(Player player1, Player player2, float d) {
		int p1X = player1.getX() < 0 ? player1.getX()+1 : player1.getX();
		int p1Y = player1.getY() < 0 ? player1.getY()+1 : player1.getY();
		int p2X = player2.getX() < 0 ? player2.getX()+1 : player2.getX();
		int p2Y = player2.getY() < 0 ? player2.getY()+1 : player2.getY();
		
		return d > Math.sqrt((p1X - p2X) * (p1X - p2X) + (p1Y - p2Y) * (p1Y - p2Y));
	}
	
	/**
	 * Picks the best move, moves the player adds the new move to the path
	 * new value of path is an int[8] which:
	 * [0]: dice
	 * [1]: win-lost points on the round
	 * [2]: took pistol
	 * [3]: took bow
	 * [4]: tool sword
	 * [5]: found supply
	 * [6]: fell on animals trap
	 * [7]: fell on ropes trap
	 * @param p opponent
	 * @return new position of the player
	 */
	int[] move(Player p) {
		// N = M
		int halfN = super.board.getN()/2;
		
		// Available moves
		boolean up, down, left, right;
		
		up = super.y == -halfN ? false : true;
		down = super.y == halfN ? false : true;
		left = super.x == -halfN ? false : true;
		right = super.x == halfN ? false : true;
				
		Map<Integer, Double> moves = new HashMap<Integer, Double>();
		
		for (int move = 0; move < 8; move++) {
			boolean valuable = true;
			
			if(!up && (move == 1 || move == 8 ||move == 2)) {
				valuable = false;
			} else if(!down && (move == 6 || move == 5 ||move == 4)) {
				valuable = false;
			} else if(!left && (move == 8 || move == 7 ||move == 6)) {
				valuable = false;
			} else if(!right && (move == 2 || move == 3 ||move == 4)) {
				valuable = false;
			}
			
			if(valuable) {
				moves.put(move, this.evaluate(move, p));
			}
		}
		
		Set<Integer> dices = moves.keySet();
		int maxDice=0;
		double max = Double.NEGATIVE_INFINITY;
		for(Integer i: dices) {
			double check = moves.get(i);
			if(check > max) {
				max = check;
				maxDice = i;
			}
		}
		
		
		// Calculate the next position of the player
		int newX=super.x, newY=super.y;
		
		// down
		if(maxDice == 6 || maxDice == 5 ||maxDice == 4) {
			if(super.y+1 == 0) {
				newY = 1;
			} else {
				newY = super.y + 1;
			}
		}
		
		// left
		if(maxDice == 6 || maxDice == 7 ||maxDice == 8) {
			if(super.x-1 == 0) {
				newX = -1;
			} else {
				newX = super.x - 1;
			}
		}
		
		// right
		if(maxDice == 2 || maxDice == 3 ||maxDice == 4) {
			if(super.x+1 == 0) {
				newX = 1;
			} else {
				newX = super.x + 1;
			}
		}

		// Move Player
		super.x = newX;
		super.y = newY;
		
		Integer[] currentPath = new Integer[8];
		currentPath[0] = maxDice;
		
		Weapon[] weapons = super.board.getWeapons();
		for(int i = 0; i < weapons.length; i++) {
			if(weapons[i].getX() == super.x && weapons[i].getY() == super.y) {
				// Check if the weapon is for this player
				if(weapons[i].getPlayerID() == super.id) {
					String type = weapons[i].getType();
					switch(type) {
					case "pistol":
						if(super.pistol == null) {
							super.pistol = new Weapon(weapons[i]);
							
							// Remove from the board
							weapons[i].setX(0);
							weapons[i].setY(0);
							
							currentPath[2] = 1;
						}
						break;
					case "bow":
						if(super.bow == null) {
							super.bow = new Weapon(weapons[i]);
							
							// Remove from the board
							weapons[i].setX(0);
							weapons[i].setY(0);
							
							currentPath[3] = 1;
						}
						break;
					case "sword":
						if(super.sword == null) {
							super.sword = new Weapon(weapons[i]);
							
							// Remove from the board
							weapons[i].setX(0);
							weapons[i].setY(0);
							
							currentPath[4] = 1;
						}
						break;
					}
				}
				
			}
		}
		
		// Check if the space is a food
		Food[] food = super.board.getFood();
		for(int i = 0; i < food.length; i++) {
			if(food[i].getX() == super.x && food[i].getY() == super.y) {
				// Add score to the player
				super.score += food[i].getPoints();
				currentPath[1] = food[i].getPoints();
				
				// Remove from the board
				food[i].setX(0);
				food[i].setY(0);
				
				currentPath[5] = 1;
			}
		}
		
		// Check if the space is a trap
		Trap[] traps = super.board.getTraps();
		for(int i = 0; i < traps.length; i++) {
			if(traps[i].getX() == super.x && traps[i].getY() == super.y) {
				// Check if the player has the weapon to get away without loosing points
				if(traps[i].getType() == "animals") {
					if(super.bow == null) {
						super.score += traps[i].getPoints();
						currentPath[1] = traps[i].getPoints();
						currentPath[6] = 1;
					}
				}
				
				if(traps[i].getType() == "ropes") {
					if(super.sword == null) {
						super.score += traps[i].getPoints();
						currentPath[1] = traps[i].getPoints();
						currentPath[7] = 1;
					}
				}
			}
		}

		// Add values to the path
		this.path.add(currentPath);
		
		int[] ret = new int[2];
		ret[0] = newX;
		ret[1] = newY;
		return ret;
	}
	
	/**
	 * Prints the last move of the player to the console
	 */
	 void statistics() {
		 Integer[] lastPath = path.get(path.size() - 1);
		 
		 System.out.print(super.name + " selected dice: " + lastPath[0]);
		 if(lastPath[2] != 0) {
			 System.out.println(", picked a pistol.");
		 } else if(lastPath[3] != 0) {
			 System.out.println(", picked a bow.");
		 } else if(lastPath[4] != 0) {
			 System.out.println(", picked a sword.");
		 } else if(lastPath[5] != 0) {
			 System.out.println(", picked a supply and won " + lastPath[1] + " points.");
		 } else if(lastPath[6] != 0) {
			 System.out.println(", fell on an animanls trap and lost " + -lastPath[1] + " points.");
		 } else if(lastPath[7] != 0) {
			 System.out.println(", fell on a ropes trap and lost " + -lastPath[1] + " points.");
		 } else {
			 System.out.println(".");
		 }
	 }
}
