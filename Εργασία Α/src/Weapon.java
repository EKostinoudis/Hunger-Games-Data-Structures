
/**
 * Class that represents every weapon
 */
public class Weapon {
	private int id; // id of the weapon
	private int x; // x coordinate of the weapon's position
	private int y; // y coordinate of the weapon's position
	private int  playerID; // player's who own's the weapon id
	private String type; // type of weapon (pistol, bow, sword)
	
	//////////////////////////////////
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	// type setter (check if we have a correct type)
	public void setType(String type) {
		if(type == "pistol" || type == "bow" || type == "sword") {
			this.type = type;
		}
	}
	
	// End of setters
	//////////////////////////////////
	
	//////////////////////////////////
	// Getters
	
	public int getId() {
		return this.id;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getPlayerID() {
		return this.playerID;
	}
	
	public String getType() {
		return this.type;
	}
	
	// End of getters
	//////////////////////////////////
	
	/**
	 * Weapon constructor with each variable as argument
	 * @param id id of the weapon
	 * @param x x coordinate of the weapon's position
	 * @param y y coordinate of the weapon's position
	 * @param playerID player's who own's the weapon id
	 * @param type type of weapon (pistol, bow, sword)
	 */
	public Weapon(int id, int x, int y, int playerID, String type) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.playerID = playerID;
		if(type == "pistol" || type == "bow" || type == "sword") {
			this.type = type;
		}
	}
	
	/**
	 * Weapon constructor with Weapon class argument
	 * @param weapon Weapon object
	 */
	public Weapon(Weapon weapon) {
		this.id = weapon.getId();
		this.x = weapon.getX();
		this.y = weapon.getY();
		this.playerID = weapon.getPlayerID();
		this.type = weapon.getType();
	}
}
