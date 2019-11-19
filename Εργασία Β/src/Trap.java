/**
 * Class that represents every trap
 */
public class Trap {
	private int id; // id of the trap
	private int x; // x coordinate of the trap
	private int y; // y coordinate of the trap
	private String type; // type of the trap (ropes, animals)
	private int points; // points that the player loses when he gets to the trap


	/**
	 * id setter
	 * @param id id of the trap
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * x setter
	 * @param x x coordinate of the trap
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * y setter
	 * @param y y coordinate of the trap
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * type setter, checking for valuable input
	 * @param type type of the trap (ropes, animals)
	 */
	public void setType(String type) {
		if(type == "ropes" || type == "animals") {
			this.type = type;
		}
	}
	
	/**
	 * points setter
	 * @param points points that the player loses when he gets to the trap
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * id getter
	 * @return id of the trap
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * x getter
	 * @return x coordinate of the trap
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * y getter
	 * @return y coordinate of the trap
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * type getter
	 * @return type of the trap (ropes, animals)
	 */
	public String getType() {
		return this.type;
	}
	
	/** 
	 * points getter
	 * @return points that the player loses when he gets to the trap
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Trap constructor with each variable as argument
	 * @param id id of the trap
	 * @param x x coordinate of the trap
	 * @param y y coordinate of the trap
	 * @param type type of the trap (ropes, animals)
	 * @param points points that the player loses when he gets to the trap
	 */
	public Trap(int id, int x, int y, String type, int points) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.points = points;
		if(type == "ropes" || type == "animals") {
			this.type = type;
		}
	}
	
	/**
	 * Trap constructor with Trap class argument
	 * @param trap Trap object
	 */
	public Trap(Trap trap) {
		this.id = trap.getId();
		this.x = trap.getX();
		this.y = trap.getY();
		this.points = trap.getPoints();	
		this.type = trap.getType();
	}
}
