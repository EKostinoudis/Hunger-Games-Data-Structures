
/**
 * Player of the game
 */
public class Player {
	int id; // player's id
	String name; // player's name
	Board board; // board of the game
	int score; // player's score
	int x; // x coordinate of the player's position
	int y; // y coordinate of the player's position
	Weapon bow; 
	Weapon pistol;
	Weapon sword;
	
	/**
	 * Player constructor all except Weapon arguments 
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 */
	Player(int id, String name, Board board, int score, int x, int y) {
		this.id = id;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Player constructor with each variable as argument
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
	Player(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword) {
		this.id = id;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
		this.bow = bow;
		this.pistol = pistol;
		this.sword = sword;
	}
	
	/**
	 * Player constructor with Player class argument
	 * @param player Player object
	 */
	Player(Player player) {
		this.id = player.id;
		this.name = player.name;
		this.board = player.board;
		this.score = player.score;
		this.x = player.x;
		this.y = player.y;
		this.bow = player.bow;
		this.pistol = player.pistol;
		this.sword = player.sword;
	}
	
	void setId(int id) {
		this.id = id;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setBoard(Board board) {
		this.board = board;
	}
	
	void setScore(int score) {
		this.score = score;
	}
	
	void setX(int x) {
		this.x = x;
	}
	
	void setY(int y) {
		this.y = y;
	}
	
	void setBow(Weapon bow) {
		this.bow = bow;
	}
	
	void setPistol(Weapon pistol) {
		this.pistol = pistol;
	}
	
	void setSword(Weapon sword) {
		this.sword = sword;
	}
	
	int getId() {
		return this.id;
	}
	
	String getName() {
		return name;
	}
	
	Board getBoard() {
		return this.board;
	}
	
	int getScore() {
		return this.score;
	}
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
	
	Weapon getBow() {
		return this.bow;
	}
	
	Weapon getPistol() {
		return this.pistol;
	}
	
	Weapon getSword() {
		return this.sword;
	}
	
	/**
	 * Generates a random valuable move
	 * @return random new position in format (x,y)
	 */
	int[] getRandomMove() {
		// N = M
		int halfN = this.board.getN()/2;
		
		// Available moves
		boolean up, down, left, right;
		
		up = this.y == -halfN ? false : true;
		down = this.y == halfN ? false : true;
		left = this.x == -halfN ? false : true;
		right = this.x == halfN ? false : true;
		
		
		// Generate a random valuable move
		boolean valuable;
		int move;
		do {
			valuable = true;
			
			move = (int) (Math.random() * 8) + 1;
			
			if(!up && (move == 1 || move == 8 ||move == 2)) {
				valuable = false;
			} else if(!down && (move == 6 || move == 5 ||move == 4)) {
				valuable = false;
			} else if(!left && (move == 8 || move == 7 ||move == 6)) {
				valuable = false;
			} else if(!right && (move == 2 || move == 3 ||move == 4)) {
				valuable = false;
			}
		} while(!valuable);
		
		// Calculate the next position of the player
		int newX=this.x, newY=this.y;
		// up
		if(move == 1 || move == 8 ||move == 2) {
			if(this.y-1 == 0) {
				newY = -1;
			} else {
				newY = this.y - 1;
			}
		}
		
		// down
		if(move == 6 || move == 5 ||move == 4) {
			if(this.y+1 == 0) {
				newY = 1;
			} else {
				newY = this.y + 1;
			}
		}
		
		// left
		if(move == 6 || move == 7 ||move == 8) {
			if(this.x-1 == 0) {
				newX = -1;
			} else {
				newX = this.x - 1;
			}
		}
		
		// right
		if(move == 2 || move == 3 ||move == 4) {
			if(this.x+1 == 0) {
				newX = 1;
			} else {
				newX = this.x + 1;
			}
		}
				
		int[] ret = new int[2];
		ret[0] = newX;
		ret[1] = newY;
		return ret;
	}
	
	/**
	 * Makes a move for the player. Also checks if the he is 
	 * at a trap, a weapon or a supply.
	 * @return integer array with format (x position of the player, y position of the player, number of weapons collected, number of food collected, number of traps fell in)
	 */
	int[] move() {
		int[] ret = new int[] {0, 0, 0, 0, 0};
		
		int[] nextPosition;
		// Get a move
		nextPosition = this.getRandomMove();
		
		// Move player
		this.x = nextPosition[0];
		this.y = nextPosition[1];
		ret[0] = this.x;
		ret[1] = this.y;
		
		// Check if the space is a weapon
		// if it is add to the player if he hasn't this type of weapon
		Weapon[] weapons = this.board.getWeapons();
		for(int i = 0; i < weapons.length; i++) {
			if(weapons[i].getX() == this.x && weapons[i].getY() == this.y) {
				// Check if the weapon is for this player
				if(weapons[i].getPlayerID() == this.id) {
					String type = weapons[i].getType();
					switch(type) {
					case "pistol":
						if(this.pistol == null) {
							this.pistol = new Weapon(weapons[i]);
							
							// Remove from the board
							weapons[i].setX(0);
							weapons[i].setY(0);
							
							System.out.println(this.name + " picked a pistol. Weapon id: " + weapons[i].getId());
							ret[2] = 1;
						}
						break;
					case "bow":
						if(this.bow == null) {
							this.bow = new Weapon(weapons[i]);
							
							// Remove from the board
							weapons[i].setX(0);
							weapons[i].setY(0);
							
							System.out.println(this.name + " picked a bow. Weapon id: " + weapons[i].getId());
							ret[2] = 1;
						}
						break;
					case "sword":
						if(this.sword == null) {
							this.sword = new Weapon(weapons[i]);
							
							// Remove from the board
							weapons[i].setX(0);
							weapons[i].setY(0);
							
							System.out.println(this.name + " picked a sword. Weapon id: " + weapons[i].getId());
							ret[2] = 1;
						}
						break;
					}
				}
				
			}
		}
		
		// Check if the space is a food
		Food[] food = this.board.getFood();
		for(int i = 0; i < food.length; i++) {
			if(food[i].getX() == this.x && food[i].getY() == this.y) {
				// Add score to the player
				this.score += food[i].getPoints();
				
				// Remove from the board
				food[i].setX(0);
				food[i].setY(0);
				
				System.out.println(this.name + " picked a food with " +  food[i].getPoints() + " points. Food id: " + food[i].getId());
				ret[3] = 1;
			}
		}
		
		// Check if the space is a trap
		Trap[] traps = this.board.getTraps();
		for(int i = 0; i < traps.length; i++) {
			if(traps[i].getX() == this.x && traps[i].getY() == this.y) {
				// Check if the player has the weapon to get away without loosing points
				if(traps[i].getType() == "animals") {
					if(this.bow == null) {
						this.score += traps[i].getPoints();
						System.out.println(this.name + " traped by animals. Lost  " + -traps[i].getPoints() + " points. Trap id: " + traps[i].getId());
						ret[4] = 1;
					} else {
						System.out.println(this.name + " escaped animals trap with bow. Trap id: " + traps[i].getId());
					}
				}
				
				if(traps[i].getType() == "ropes") {
					if(this.sword == null) {
						this.score += traps[i].getPoints();
						System.out.println(this.name + " traped by ropes. Lost  " + -traps[i].getPoints() + " points. Trap id: " + traps[i].getId());
						ret[4] = 1;
					} else {
						System.out.println(this.name + " escaped ropes trap with sword. Trap id: " + traps[i].getId());
					}
				}
			}
		}
		
		return ret;
	}
}
