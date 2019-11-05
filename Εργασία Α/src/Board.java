/**
 * Board of the game
 */
public class Board {
	private int N, M; // dimensions of the board NxM
	private int W; // number of weapons
	private int F; // number of supplies
	private int T; // number of traps
	private int[][] weaponAreaLimits = new int[4][2]; // weapon area
	private int[][] foodAreaLimits = new int[4][2]; // food area
	private int[][] trapAreaLimits = new int[4][2]; // trap area
	private Weapon[] weapons; // array of Weapon objects
	private Food[] food; // array of Food objects
	private Trap[] traps; // array of Trap objects
	
	/**
	 * N setter
	 * @param N y dimension of the board
	 */
	public void setN(int N) {
		this.N = N;
	}
	
	/**
	 * M setter
	 * @param M x dimension of the board
	 */
	public void setM(int M) {
		this.M = M;
	}
	
	/**
	 * W setter
	 * @param W number of weapons
	 */
	public void setW(int W) {
		this.W = W;
	}
	
	/**
	 * F setter
	 * @param F number of supplies
	 */
	public void setF(int F) {
		this.F = F;
	}
	
	/**
	 * T setter
	 * @param T number of traps
	 */
	public void setT(int T) {
		this.T = T;
	}
	
	/**
	 * weaponAeaLimits setter
	 * @param weaponAreaLimits
	 */
	public void setWeaponAreaLimits(int[][] weaponAreaLimits) {
		for(int i=0;i<this.weaponAreaLimits.length;i++) {
			for(int j=0;j<this.weaponAreaLimits[i].length;j++) {
				this.weaponAreaLimits[i][j] = weaponAreaLimits[i][j];
			}
		}
	}
	
	/**
	 * foodAreaLimits setter
	 * @param foodAreaLimits
	 */
	public void setFoodAreaLimits(int[][] foodAreaLimits) {
		for(int i=0;i<this.foodAreaLimits.length;i++) {
			for(int j=0;j<this.foodAreaLimits[i].length;j++) {
				this.foodAreaLimits[i][j] = foodAreaLimits[i][j];
			}
		}
	}
	
	/**
	 * trapAreaLimits setter
	 * @param trapAreaLimits
	 */
	public void setTrapAreaLimits(int[][] trapAreaLimits) {
		for(int i=0;i<this.trapAreaLimits.length;i++) {
			for(int j=0;j<this.trapAreaLimits[i].length;j++) {
				this.trapAreaLimits[i][j] = trapAreaLimits[i][j];
			}
		}
	}
	
	/**
	 * weapons setter
	 * @param weapons
	 */
	public void setWeapons(Weapon[] weapons) {
		this.weapons = new Weapon[weapons.length];
		for(int i=0;i<weapons.length;i++) {
			this.weapons[i] = new Weapon(weapons[i]);
		}
	}
	
	/**
	 * food setter
	 * @param food
	 */
	public void setFood(Food[] food) {
		this.food = new Food[food.length];
		for(int i=0;i<food.length;i++) {
			this.food[i] = new Food(food[i]);
		}
	}
	
	/**
	 * traps setter
	 * @param traps
	 */
	public void setTraps(Trap[] traps) {
		this.traps = new Trap[traps.length];
		for(int i=0;i<traps.length;i++) {
			this.traps[i] = new Trap(traps[i]);
		}
	}

	/**
	 * N getter
	 * @return N
	 */
	public int getN() {
		return this.N;
	}
	
	/**
	 * M getter
	 * @return
	 */
	public int getM() {
		return this.M;
	}
	
	/**
	 * W getter
	 * @return W
	 */
	public int getW() {
		return this.W;
	}
	
	/**
	 * F getter
	 * @return F
	 */
	public int getF() {
		return this.F;
	}
	
	/**
	 * T getter
	 * @return T
	 */
	public int getT() {
		return this.T;
	}
	
	/**
	 * weaponAreaLimits getter
	 * @return foodAreaLimits
	 */
	public int[][] getWeaponAreaLimits() {
		return this.weaponAreaLimits;
	}
	
	/**
	 * foodAreaLimits getter
	 * @return foodAreaLimits
	 */
	public int[][] getFoodAreaLimits() {
		return this.foodAreaLimits;
	}
	
	/**
	 * trapAreaLimits getter
	 * @return trapAreaLimits
	 */
	public int[][] getTrapAreaLimits() {
		return this.trapAreaLimits;
	}
	
	/**
	 * weapons getter
	 * @return weapons
	 */
	public Weapon[] getWeapons() {
		return this.weapons;
	}
	
	/**
	 * food getter
	 * @return food
	 */
	public Food[] getFood() {
		return this.food;
	}
	
	/**
	 * traps getter
	 * @return traps
	 */
	public Trap[] getTraps() {
		return this.traps;
	}
	
	/**
	 * Empty constructor
	 */
	public Board() {}
	
	/**
	 * Board constructor with size and number of weapons, supplies and traps
	 * as arguments. If N != M we don't initialize any value.
	 * @param N y dimension of the board
	 * @param M x dimension of the board
	 * @param W number of weapons
	 * @param F number of supplies
	 * @param T number of traps
	 */
	public Board(int N, int M, int W, int F, int T) {
		if(N != M) {
			System.out.println("N must equal to M.");
			System.out.println("Created an empty object.");
		}
		else {
			this.N = N;
			this.M = M;
			this.W = W;
			this.F = F;
			this.T = T;
		}
	}
	
	/**
	 * Board constructor with Board object argument
	 * @param board Board object
	 */
	public Board(Board board) {
		this.N = board.N;
		this.M = board.M;
		this.W = board.W;
		this.F = board.F;
		this.T = board.T;
		
		// copy weaponAreaLimits
		for(int i=0;i<this.weaponAreaLimits.length;i++) {
			for(int j=0;j<this.weaponAreaLimits[i].length;j++) {
				this.weaponAreaLimits[i][j] = board.weaponAreaLimits[i][j];
			}
		}
		
		// copy foodAreaLimits
		for(int i=0;i<this.foodAreaLimits.length;i++) {
			for(int j=0;j<this.foodAreaLimits[i].length;j++) {
				this.foodAreaLimits[i][j] = board.foodAreaLimits[i][j];
			}
		}
		
		// copy trapAreaLimits
		for(int i=0;i<this.trapAreaLimits.length;i++) {
			for(int j=0;j<this.trapAreaLimits[i].length;j++) {
				this.trapAreaLimits[i][j] = board.trapAreaLimits[i][j];
			}
		}
		
		// Copy weapons
		if(board.weapons != null) {
			this.weapons = new Weapon[board.weapons.length];
			for(int i=0;i<board.weapons.length;i++) {
				this.weapons[i] = new Weapon(board.weapons[i]);
			}
		}
		
		// copy food
		if(board.food != null) {
			this.food = new Food[board.food.length];
			for(int i=0;i<board.food.length;i++) {
				this.food[i] = new Food(board.food[i]);
			}
		}
		
		// copy traps
		if(board.traps != null) {
			this.traps = new Trap[board.traps.length];
			for(int i=0;i<board.traps.length;i++) {
				this.traps[i] = new Trap(board.traps[i]);
			}
		}
	}
	
	/**
	 * Initialize weapons array
	 * @param p1 player 1
	 * @param p2 player 2
	 */
	public void createRandomWeapon(Player p1, Player p2) {
		this.weapons = new Weapon[this.W];
		
		int minY = this.weaponAreaLimits[0][0];
		int maxY = this.weaponAreaLimits[0][0];
		int minX = this.weaponAreaLimits[0][1];
		int maxX = this.weaponAreaLimits[0][1];
		
		for(int i=1; i<this.weaponAreaLimits.length;i++) {
			if(this.weaponAreaLimits[i][0] < minY) {
				minY = this.weaponAreaLimits[i][0];
			}
			
			if(this.weaponAreaLimits[i][0] > maxY) {
				maxY = this.weaponAreaLimits[i][0];
			}
			
			if(this.weaponAreaLimits[i][1] < minX) {
				minX = this.weaponAreaLimits[i][1];
			}
			
			if(this.weaponAreaLimits[i][1] > maxX) {
				maxX = this.weaponAreaLimits[i][1];
			}
		}
		
		// Find a random place for the weapon
		 for(int i=0;i<this.W;i++) {
			 int posX, posY;
			 boolean notAvailable;
			 
			 do {
				 notAvailable = false;
				 do {
					posX = (int) (Math.random() * (maxY - minY + 1)) + minY;
				 } while(posX != 0);
				
				 do {
					posY = (int) (Math.random() * (maxX - minX + 1)) + minX;
				 } while(posY != 0);
				 
				 // Check if there is a weapon with the same position
				 for(int j=0;j<this.weapons.length;j++) {
					 if(this.weapons[i].getX() == posX && this.weapons[i].getY() == posY) {
						 notAvailable = true;
					 }
				 }
			 } while(notAvailable);
			 
			 // Add the weapon to the array
			 if(i%2 == 0) {
				 int type = (int) (Math.random() * 3);
				 switch(type) {
				 case 0:
					 this.weapons[i] = new Weapon(i, posX, posY, p1.getId(), "pistol");
					 break;
				 case 1:
					 this.weapons[i] = new Weapon(i, posX, posY, p1.getId(), "bow");
					 break;
				 case 2:
					 this.weapons[i] = new Weapon(i, posX, posY, p1.getId(), "sword");
					 break;
				 }
			 } else {
				 int type = (int) (Math.random() * 3);
				 switch(type) {
				 case 0:
					 this.weapons[i] = new Weapon(i, posX, posY, p2.getId(), "pistol");
					 break;
				 case 1:
					 this.weapons[i] = new Weapon(i, posX, posY, p2.getId(), "bow");
					 break;
				 case 2:
					 this.weapons[i] = new Weapon(i, posX, posY, p2.getId(), "sword");
					 break;
				 }
			 }
		 }
	}
	
	/**
	 * Initialize food array
	 */
	public void createRandomFood() {
		this.food = new Food[this.F];
		
		// Find the food and weapon area limits 
		int minY = this.foodAreaLimits[0][0];
		int maxY = this.foodAreaLimits[0][0];
		int minX = this.foodAreaLimits[0][1];
		int maxX = this.foodAreaLimits[0][1];
		
		for(int i=1; i<this.foodAreaLimits.length;i++) {
			if(this.foodAreaLimits[i][0] < minY) {
				minY = this.foodAreaLimits[i][0];
			}
			
			if(this.foodAreaLimits[i][0] > maxY) {
				maxY = this.foodAreaLimits[i][0];
			}
			
			if(this.foodAreaLimits[i][1] < minX) {
				minX = this.foodAreaLimits[i][1];
			}
			
			if(this.foodAreaLimits[i][1] > maxX) {
				maxX = this.foodAreaLimits[i][1];
			}
		}
		
		int WminY = this.weaponAreaLimits[0][0];
		int WmaxY = this.weaponAreaLimits[0][0];
		int WminX = this.weaponAreaLimits[0][1];
		int WmaxX = this.weaponAreaLimits[0][1];
		
		for(int i=1; i<this.weaponAreaLimits.length;i++) {
			if(this.weaponAreaLimits[i][0] < WminY) {
				WminY = this.weaponAreaLimits[i][0];
			}
			
			if(this.weaponAreaLimits[i][0] > WmaxY) {
				WmaxY = this.weaponAreaLimits[i][0];
			}
			
			if(this.weaponAreaLimits[i][1] < WminX) {
				WminX = this.weaponAreaLimits[i][1];
			}
			
			if(this.weaponAreaLimits[i][1] > WmaxX) {
				WmaxX = this.weaponAreaLimits[i][1];
			}
		}
		
		// Find a random place for the supply
		for(int i=0;i<this.F;i++) {
			int posX, posY;
			boolean notAvailable;
			 
			do {
				notAvailable = false;
				posX = (int) (Math.random() * (maxX - WmaxX - (minX - WminX)));
				posY = (int) (Math.random() * (maxY - WmaxY - (minY - WminY)));
				 
				posX = posX < (WminX - minX) ? minX + posX : posX - (WminX - minX) + WmaxX + 1;
				posY = posY < (WminY - minY) ? minY + posY : posY - (WminY - minY) + WmaxY + 1;
				 
				// Check if there is a supply with the same position
				for(int j=0;j<this.food.length;j++) {
					if(this.food[i].getX() == posX && this.food[i].getY() == posY) {
						notAvailable = true;
					}
				}
			} while(notAvailable);
			 
			 
			int points = (int) (Math.random() * 10) + 1;
			this.food[i] = new Food(i, posX, posY, points);
		}	
	}
	
	/**
	 * Initialize traps array
	 */
	public void createRandomTrap() {
		this.traps = new Trap[this.T];
		
		// Find the trap and food area limits 
		int minY = this.trapAreaLimits[0][0];
		int maxY = this.trapAreaLimits[0][0];
		int minX = this.trapAreaLimits[0][1];
		int maxX = this.trapAreaLimits[0][1];
		
		for(int i=1; i<this.trapAreaLimits.length;i++) {
			if(this.trapAreaLimits[i][0] < minY) {
				minY = this.trapAreaLimits[i][0];
			}
			
			if(this.trapAreaLimits[i][0] > maxY) {
				maxY = this.trapAreaLimits[i][0];
			}
			
			if(this.trapAreaLimits[i][1] < minX) {
				minX = this.trapAreaLimits[i][1];
			}
			
			if(this.trapAreaLimits[i][1] > maxX) {
				maxX = this.trapAreaLimits[i][1];
			}
		}
		
		int FminY = this.foodAreaLimits[0][0];
		int FmaxY = this.foodAreaLimits[0][0];
		int FminX = this.foodAreaLimits[0][1];
		int FmaxX = this.foodAreaLimits[0][1];
		
		for(int i=1; i<this.foodAreaLimits.length;i++) {
			if(this.foodAreaLimits[i][0] < FminY) {
				FminY = this.foodAreaLimits[i][0];
			}
			
			if(this.foodAreaLimits[i][0] > FmaxY) {
				FmaxY = this.foodAreaLimits[i][0];
			}
			
			if(this.foodAreaLimits[i][1] < FminX) {
				FminX = this.foodAreaLimits[i][1];
			}
			
			if(this.foodAreaLimits[i][1] > FmaxX) {
				FmaxX = this.foodAreaLimits[i][1];
			}
		}
		
		// Find a random place for the trap
		for(int i=0;i<this.T;i++) {
			int posX, posY;
			boolean notAvailable;
			 
			do {
				notAvailable = false;
				posX = (int) (Math.random() * (maxX - FmaxX - (minX - FminX)));
				posY = (int) (Math.random() * (maxY - FmaxY - (minY - FminY)));
				 
				posX = posX < (FminX - minX) ? minX + posX : posX - (FminX - minX) + FmaxX + 1;
				posY = posY < (FminY - minY) ? minY + posY : posY - (FminY - minY) + FmaxY + 1;
				 
				// Check if there is a supply with the same position
				for(int j=0;j<this.traps.length;j++) {
					if(this.traps[i].getX() == posX && this.traps[i].getY() == posY) {
						notAvailable = true;
					}
				}
			} while(notAvailable);
			 
			 
			int points = (int) (Math.random() * 10) + 1; // Random points
			int type = (int) (Math.random() * 2); // Random type of trap
			
			switch(type) {
			case 0:
				this.traps[i] = new Trap(i, posX, posY, "ropes", -points);
				break;
			case 1:
				this.traps[i] = new Trap(i, posX, posY, "animals", -points);
				break;
			}
		}	
	}
	
	/**
	 * Initialize board
	 * @param p1 player 1
	 * @param p2 player 2
	 */
	public void createBoard(Player p1, Player p2) {
		this.createBoard(p1, p2);
		this.createRandomFood();
		this.createRandomTrap();
	}
	
	/**
	 * Resize board if players are not at the edge
	 * @param p1 player 1
	 * @param p2 player 2
	 */
	void resizeBoard(Player p1, Player p2) {
		// N = M 
		int halfN = this.N/2;
		
		// Take the position of the players
		int posX1, posX2, posY1, posY2;
		posX1 = p1.getX();
		posX2 = p2.getX();
		posY1 = p1.getY();
		posY2 = p2.getY();
		
		// absolute values of the positions
		posX1 = posX1 > 0 ? posX1 : -posX1;
		posX2 = posX2 > 0 ? posX2 : -posX2;
		posY1 = posY1 > 0 ? posY1 : -posY1;
		posY2 = posY2 > 0 ? posY2 : -posY2;
		
		// Check if a player if at the edge of the board
		if(posX1 != halfN && posX2 != halfN && posY1 != halfN && posY2 != halfN) {
			this.N = this.N - 2;
			this.M = this.M - 2;
		}
	}
	
	public String[][] getStringRepresentation() {
		String[][] boardView = new String[this.N][this.M];
		
		for(int i=0;i<this.weapons.length;i++) {
			boardView[weapons[i].getY()][weapons[i].getX()] = "W" + weapons[i].getPlayerID() + weapons[i].getId();
		}
		
		for(int i=0;i<this.food.length;i++) {
			boardView[food[i].getY()][food[i].getX()] = "F" + food[i].getId();
		}
		
		for(int i=0;i<this.traps.length;i++) {
			boardView[traps[i].getY()][traps[i].getX()] = "T" + traps[i].getId();
		}
		
		// Fill the empty spaces
		for(int i=0;i<this.N;i++) {
			for(int j=0;j<this.M;j++) {
				if(boardView[i][j] == null) {
					boardView[i][j] = "___";
				}
			}
		}
		
		return boardView;
	}
}
