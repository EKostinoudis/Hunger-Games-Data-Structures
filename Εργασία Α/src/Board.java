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
	 * @return weaponAreaLimits
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
	
	
}
