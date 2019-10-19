
/**
 * Class that represents every weapon
 */
public class Weapon {
	private int id; // id of the weapon
	private int x; // x coordinate of the weapon's position
	private int y; // y coordinate of the weapon's position
	private int  playerID; // id of the player who own's the weapon
	private String type; // type of weapon (pistol, bow, sword)
	
	/**
	 * id setter
	 * @param id id of the weapon
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * x setter
	 * @param x x coordinate of the weapon's position
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * y setter
	 * @param y y coordinate of the weapon's position
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * playerID setter
	 * @param playerID id of the player who own's the weapon
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	/**
	 * type setter (check if we have a correct type)
	 * @param type type of weapon (pistol, bow, sword)
	 */
	public void setType(String type) {
		if(type == "pistol" || type == "bow" || type == "sword") {
			this.type = type;
		}
	}
	
	/**
	 * id getter
	 * @return id of the weapon
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * x getter
	 * @return x coordinate of the weapon's position
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * y getter
	 * @return y coordinate of the weapon's position
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * playerID getter
	 * @return id of the player who own's the weapon
	 */
	public int getPlayerID() {
		return this.playerID;
	}
	
	/**
	 * type getter
	 * @return type of weapon (pistol, bow, sword)
	 */
	public String getType() {
		return this.type;
	}
	
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
