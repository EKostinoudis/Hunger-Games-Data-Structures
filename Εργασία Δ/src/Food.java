/**
 * Class that represents every supply
 */
public class Food {
	private int id; // id of the supply
	private int x; // x coordinate of the supply
	private int y; // y coordinate of the supply
	private int points; // points that the player wins when he eats the supply

	/**
	 * id setter
	 * @param id id of the supply
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * x setter
	 * @param x x coordinate of the supply
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/** 
	 * y setter
	 * @param y y coordinate of the supply
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * points setter
	 * @param points points that the player wins when he eats the supply
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * id getter
	 * @return id of the supply
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * x getter
	 * @return x coordinate of the supply
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * y getter
	 * @return y coordinate of the supply
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * points getter
	 * @return points that the player wins when he eats the supply
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Food constructor with each variable as argument
	 * @param id id of the Food
	 * @param x x coordinate of the supply
	 * @param y y coordinate of the supply
	 * @param points points that the player wins when he eats the supply
	 */
	public Food(int id, int x, int y, int points) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.points = points;
	}
	
	/**
	 * Food constructor with Food class argument
	 * @param food Food object
	 */
	public Food(Food food) {
		this.id = food.getId();
		this.x = food.getX();
		this.y = food.getY();
		this.points = food.getPoints();
	}
}
