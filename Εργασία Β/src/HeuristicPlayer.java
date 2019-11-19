import java.util.ArrayList;
/**
 * A player who plays with strategy
 */
public class HeuristicPlayer extends Player{
	ArrayList<Integer[]> path; // Informations about the players past moves
	
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
	
	
}
