/**
 * Class that represents every supply
 */
public class Food {
	private int id; // id of the Food
	private int x; // x coordinate of the supply
	private int y; // y coordinate of the supply
	private int points; // point that the player wins when he eats the supply

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
	
	public void setPoints(int points) {
		this.points = points;
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
	
	public int getPoints() {
		return this.points;
	}
	
	// End of getters
	//////////////////////////////////
	/**
	 * Food constructor with each variable as argument
	 * @param id id of the Food
	 * @param x x coordinate of the supply
	 * @param y y coordinate of the supply
	 * @param points point that the player wins when he eats the supply
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
