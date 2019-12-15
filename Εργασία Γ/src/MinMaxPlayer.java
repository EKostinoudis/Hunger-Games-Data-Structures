import java.util.ArrayList;

/**
 * Represents the player who plays with MinMax algorithm
 */
public class MinMaxPlayer extends Player {
	private ArrayList<Integer[]> path; // Informations about the players past moves
	private static int r; // The visible area radius of the player
	
	/**
	 * r getter
	 * @return r
	 */
	int getR() {
		return r;
	}
	
	/**
	 * path getter
	 * @return path
	 */
	ArrayList<Integer[]> getPath() {
		return path;
	}
	
	/**
	 * r setter
	 * @param r
	 */
	void setR(int r) {
		MinMaxPlayer.r = r;
	}
	
	/**
	 * path setter
	 * @param path
	 */
	void setPath(ArrayList<Integer[]> path) {
		this.path = path;
	}
	
	/**
	 * Empty constructor
	 */
	MinMaxPlayer() {
		super();
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * MinMaxPlayer constructor with initial path list and empty Player (superclass) constructor
	 * @param path informations about the players past moves
	 */
	MinMaxPlayer(ArrayList<Integer[]> path) {
		super();
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * MinMaxPlayer constructor with all except Weapon arguments (for the player superclass)
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 */
	MinMaxPlayer(int id, String name, Board board, int score, int x, int y) {
		super(id, name, board, score, x, y);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * MinMaxPlayer constructor with all except Weapon arguments (for the player superclass)
	 * and initial path list
	 * @param id player's id
	 * @param name player's name
	 * @param board board of the game
	 * @param score player's score
	 * @param x x coordinate of the player's position
	 * @param y y coordinate of the player's position
	 * @param path informations about the players past moves
	 */
	MinMaxPlayer(int id, String name, Board board, int score, int x, int y, ArrayList<Integer[]> path) {
		super(id, name, board, score, x, y);
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * MinMaxPlayer constructor with each variable of Player class as argument
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
	MinMaxPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword) {
		super(id, name, board, score, x, y, bow, pistol, sword);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * MinMaxPlayer constructor with each variable of Player class as argument
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
	MinMaxPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword, ArrayList<Integer[]> path) {
		super(id, name, board, score, x, y, bow, pistol, sword);
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * MinMaxPlayer constructor with player object as argument
	 * @param player Player object
	 */
	MinMaxPlayer(Player player) {
		super(player);
		path = new ArrayList<Integer[]>();
	}
	
	/**
	 * minMaxPlayer constructor with player object as argument
	 * and initial path list
	 * @param player Player object
	 * @param path informations about the players past moves
	 */
	MinMaxPlayer(Player player, ArrayList<Integer[]> path) {
		super(player);
		this.path = new ArrayList<Integer[]>(path);
	}
	
	/**
	 * Constructor with minMaxPlayer object as argument
	 * @param minMaxPlayer minMaxPlayer object
	 */
	MinMaxPlayer(MinMaxPlayer minMaxPlayer) {
		super(minMaxPlayer.getId(), minMaxPlayer.getName(), 
			  minMaxPlayer.getBoard(), minMaxPlayer.getScore(), 
			  minMaxPlayer.getX(), minMaxPlayer.getY(), 
			  minMaxPlayer.getBow(), minMaxPlayer.getPistol(), 
			  minMaxPlayer.getSword()
			 );
		this.path = new ArrayList<Integer[]>(minMaxPlayer.path);
	}
	
	/**
	 * Calculates the Euclidean distance between this player
	 * and the player p (if he can see the opponent)
	 * @param p player from who we want to calculate the distance
	 * @return Euclidean distance between the two players
	 */
	float playersDistance(Player p) {
		int currentX = this.x < 0 ? this.x+1 : this.x;
		int currentY = this.y < 0 ? this.y+1 : this.y;
		int opponentX = p.x < 0 ? p.x+1 : p.x;
		int opponentY = p.y < 0 ? p.y+1 : p.y;
		
		float dist = (float) Math.sqrt((currentX - opponentX) * (currentX - opponentX) +
				 					   (currentY - opponentY) * (currentY - opponentY));

		int dx = Math.abs(currentX - opponentX);
		int dy = Math.abs(currentY - opponentY);
		
		int blockDist =  dx > dy ? dx : dy;
		
		if(blockDist <= r) {
			return dist;
		} else {
			return -1f;
		}
	}
	
	/**
	 * Calculate a score for a move based on the formula
	 * if has a pistol takes
	 * 		if he can see the player 100 / dist points
	 * if takes a pistol takes 10 points
	 * if takes a bow or a sword takes 3 points
	 * if wins K points from food take K points
	 * if losses K points from a trap looses K points
	 * @param dice next move
	 * @param p opponent
	 * @return score for the next move
	 */
	double evaluate(int dice, int x, int y, Player opponent) {
		// Calculate the next position of the player
		int newX=x, newY=y;
		// up
		if(dice == 1 || dice == 8 ||dice == 2) {
			if(y-1 == 0) {
				newY = -1;
			} else {
				newY = y - 1;
			}
		}
		
		// down
		if(dice == 6 || dice == 5 ||dice == 4) {
			if(y+1 == 0) {
				newY = 1;
			} else {
				newY = y + 1;
			}
		}
		
		// left
		if(dice == 6 || dice == 7 ||dice == 8) {
			if(x-1 == 0) {
				newX = -1;
			} else {
				newX = x - 1;
			}
		}
		
		// right
		if(dice == 2 || dice == 3 ||dice == 4) {
			if(x+1 == 0) {
				newX = 1;
			} else {
				newX = x + 1;
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
		double ret = 0; 
		if(super.pistol != null) {
			int p1X = super.getX() < 0 ? super.getX()+1 : super.getX();
			int p1Y = super.getY() < 0 ? super.getY()+1 : super.getY();
			int p2X = opponent.getX() < 0 ? opponent.getX()+1 : opponent.getX();
			int p2Y = opponent.getY() < 0 ? opponent.getY()+1 : opponent.getY();
			
			int dx = Math.abs(p1X - p2X);
			int dy = Math.abs(p1Y - p2Y);
			
			int dist = (dx > dy ? dx : dy);
			
			ret += 100 / dist;
		}
		
		if(bowBlock || swordBlock) {
			ret += 3;
		} else if(pistolBlock) {
			ret += 10;
		}
		
		
		ret += trapScore + foodScore;
		
		return ret;
	}
	
	/**
	 * Check if the distance between 2 players are smaller than d
	 * if yes return true else false
	 * @param player1 
	 * @param player2
	 * @param d smallest distance that a player can't kill
	 * @return 
	 * @return if a player can kill
	 */
	static boolean kill(Player player1, Player player2, float d) {
		int p1X = player1.getX() < 0 ? player1.getX()+1 : player1.getX();
		int p1Y = player1.getY() < 0 ? player1.getY()+1 : player1.getY();
		int p2X = player2.getX() < 0 ? player2.getX()+1 : player2.getX();
		int p2Y = player2.getY() < 0 ? player2.getY()+1 : player2.getY();
		
		int dx = Math.abs(p1X - p2X);
		int dy = Math.abs(p1Y - p2Y);
		
		return d > (dx > dy ? dx : dy);
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
	 
	 /**
	  * Adds all the available moves to the MinMax tree
	  * @param root root node
	  * @param depth depth of MinMax tree
	  * @param xCurrentPos 
	  * @param yCurrentPos
	  * @param xOpponentCurrentPos
	  * @param yOpponentCurrentPos
	  */
	 void createMySubtree(Node root, int depth, int xCurrentPos, int yCurrentPos, int xOpponentCurrentPos, int yOpponentCurrentPos) {
		// Find the number of available movements.
		// N = M
		int halfN = root.getNodeBoard().getN()/2;
		
		// Available moves
		boolean up, down, left, right;
		
		up = super.y == -halfN ? false : true;
		down = super.y == halfN ? false : true;
		left = super.x == -halfN ? false : true;
		right = super.x == halfN ? false : true;
		
		for (int move = 1; move <= 8; move++) {
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
				// Copy board
				Board childBoard = new Board(root.getNodeBoard());
				
				// Calculate the next position of the player
				int newX=xCurrentPos, newY=yCurrentPos;
				
				// up
				if(move == 1 || move == 8 ||move == 2) {
					if(yCurrentPos-1 == 0) {
						newY = -1;
					} else {
						newY = yCurrentPos - 1;
					}
				}
				
				// down
				if(move == 6 || move == 5 ||move == 4) {
					if(yCurrentPos+1 == 0) {
						newY = 1;
					} else {
						newY = yCurrentPos + 1;
					}
				}
				
				// left
				if(move == 6 || move == 7 ||move == 8) {
					if(xCurrentPos-1 == 0) {
						newX = -1;
					} else {
						newX = xCurrentPos - 1;
					}
				}
				
				// right
				if(move == 2 || move == 3 ||move == 4) {
					if(xCurrentPos+1 == 0) {
						newX = 1;
					} else {
						newX = xCurrentPos + 1;
					}
				}
				
				Weapon[] weapons = childBoard.getWeapons();
				for(int i = 0; i < weapons.length; i++) {
					if(weapons[i].getX() == newX && weapons[i].getY() == newY) {
						// Check if the weapon is for this player
						if(weapons[i].getPlayerID() == super.id) {
							String type = weapons[i].getType();
							switch(type) {
							case "pistol":
								if(super.pistol == null) {
									// Remove from the board
									weapons[i].setX(0);
									weapons[i].setY(0);
								}
								break;
							case "bow":
								if(super.bow == null) {
									// Remove from the board
									weapons[i].setX(0);
									weapons[i].setY(0);
								}
								break;
							case "sword":
								if(super.sword == null) {
									// Remove from the board
									weapons[i].setX(0);
									weapons[i].setY(0);
								}
								break;
							}
						}
						
					}
				}
				
				// Check if the space is a food
				Food[] food = childBoard.getFood();
				for(int i = 0; i < food.length; i++) {
					if(food[i].getX() == newX && food[i].getY() == newY) {
						// Remove from the board
						food[i].setX(0);
						food[i].setY(0);
					}
				}

				int[] nodeMove = {newX, newY, move};
				
				// Create a Player object so we can call evaluate 
				// cause someone didn't thought to pass it here
				Player op = new Player(-1, "Op", board, 0, xOpponentCurrentPos, yOpponentCurrentPos);
				double eval = this.evaluate(move, xCurrentPos, yCurrentPos, op);
				
				Node child = new Node(root, depth, nodeMove, childBoard, eval);
				
				ArrayList<Node> childNodes = root.getChildren();
				childNodes.add(child);
				
				createOpponentSubtree(child, depth + 1, newX, newY, xOpponentCurrentPos, yOpponentCurrentPos);
			}
		}
	 }
	 
	 /**
	  * Adds the opponents moves to the MinMax tree
	  * @param parent parent Node
	  * @param depth
	  * @param xCurrentPos
	  * @param yCurrentPos
	  * @param xOpponentCurrentPos
	  * @param yOpponentCurrentPos
	  */
	 void createOpponentSubtree(Node parent, int depth, int xCurrentPos, int yCurrentPos, int xOpponentCurrentPos, int yOpponentCurrentPos) {
		// Find the number of available movements.
		// N = M
		int halfN = parent.getNodeBoard().getN()/2;
		
		// Available moves
		boolean up, down, left, right;
		
		up = super.y == -halfN ? false : true;
		down = super.y == halfN ? false : true;
		left = super.x == -halfN ? false : true;
		right = super.x == halfN ? false : true;
		
		for (int move = 1; move <= 8; move++) {
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
				// Copy board
				Board childBoard = new Board(parent.getNodeBoard());
				
				// Calculate the next position of the player
				int newX=xOpponentCurrentPos, newY=xOpponentCurrentPos;
				
				// up
				if(move == 1 || move == 8 ||move == 2) {
					if(yOpponentCurrentPos-1 == 0) {
						newY = -1;
					} else {
						newY = yOpponentCurrentPos - 1;
					}
				}
				
				// down
				if(move == 6 || move == 5 ||move == 4) {
					if(yOpponentCurrentPos+1 == 0) {
						newY = 1;
					} else {
						newY = yOpponentCurrentPos + 1;
					}
				}
				
				// left
				if(move == 6 || move == 7 ||move == 8) {
					if(xOpponentCurrentPos-1 == 0) {
						newX = -1;
					} else {
						newX = xOpponentCurrentPos - 1;
					}
				}
				
				// right
				if(move == 2 || move == 3 ||move == 4) {
					if(xOpponentCurrentPos+1 == 0) {
						newX = 1;
					} else {
						newX = xOpponentCurrentPos + 1;
					}
				}
				
				Weapon[] weapons = childBoard.getWeapons();
				for(int i = 0; i < weapons.length; i++) {
					if(weapons[i].getX() == newX && weapons[i].getY() == newY) {
						// Check if the weapon is for this player
						if(weapons[i].getPlayerID() == super.id) {
							String type = weapons[i].getType();
							switch(type) {
							case "pistol":
								if(super.pistol == null) {
									// Remove from the board
									weapons[i].setX(0);
									weapons[i].setY(0);
								}
								break;
							case "bow":
								if(super.bow == null) {
									// Remove from the board
									weapons[i].setX(0);
									weapons[i].setY(0);
								}
								break;
							case "sword":
								if(super.sword == null) {
									// Remove from the board
									weapons[i].setX(0);
									weapons[i].setY(0);
								}
								break;
							}
						}
						
					}
				}
				
				// Check if the space is a food
				Food[] food = childBoard.getFood();
				for(int i = 0; i < food.length; i++) {
					if(food[i].getX() == newX && food[i].getY() == newY) {
						// Remove from the board
						food[i].setX(0);
						food[i].setY(0);
					}
				}

				int[] nodeMove = {newX, newY, move}; 
				
				// Create a Player object so we can call evaluate 
				// cause someone didn't thought to pass it here
				MinMaxPlayer copy = new MinMaxPlayer(-1, "Op", board, 0, xOpponentCurrentPos, yOpponentCurrentPos);
				double eval = copy.evaluate(move, xOpponentCurrentPos, yOpponentCurrentPos, this);
				
				Node child = new Node(parent, depth, nodeMove, childBoard, eval);
				
				ArrayList<Node> childNodes = parent.getChildren();
				childNodes.add(child);
			}
		}
	 }


	 /**
	  * Choose the best move for the MinMax tree
	  * @param root root of the MinMax tree
	  * @return index of the best move
	  */
	 int chooseMinMaxMove(Node root) {
		ArrayList<Node> children = root.getChildren();
		
		// Find the minimum for each movement (for each node at 1st level at the tree)
		double[] mins = new double[children.size()];
		for(int i = 0; i < children.size(); i++) {
			ArrayList<Node> children2 = children.get(i).getChildren();
			
			double min = Double.POSITIVE_INFINITY;
			for(int j = 0; j < children2.size(); j++) {
				if(children2.get(j).getNodeEvaluation() < min) {
					min = children2.get(j).getNodeEvaluation();
				}
			}
			
			mins[i] = min;
		}
		
		// Find the index of the best move
		double max = Double.NEGATIVE_INFINITY;
		int maxIndex = -1;
		for(int i = 0; i < mins.length; i++) {
			if(mins[i] > max) {
				max = mins[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}


	/**
	 * Finds and makes the best move based on a 2 level MinMax tree
	 * @param xCurrentPos x coordinate of the player
	 * @param yCurrentPos y coordinate of the player
	 * @param xOpponentCurrentPos x coordinate of the opponent
	 * @param yOpponentCurrentPos y coordinate of the opponent
	 * @return (x,y) new position of the player
	 */
    int[] getNextMove(int xCurrentPos, int yCurrentPos, int xOpponentCurrentPos, int yOpponentCurrentPos) {
        Node root = new Node();
        root.setNodeBoard(super.getBoard());
        root.setNodeDepth(0);
        createMySubtree(root, 1, xCurrentPos, yCurrentPos, xOpponentCurrentPos, yOpponentCurrentPos);
        int moveIndex = chooseMinMaxMove(root);
        int[] bestDice = root.getChildren().get(moveIndex).getNodeMove();
        
        int newX = bestDice[0];
        int newY = bestDice[1];
        int maxDice = bestDice[2];
        
        // Move Player
 		super.x = newX;
 		super.y = newY;
 		
 		Integer[] currentPath = new Integer[8];
 		currentPath[0] = maxDice;
 		for(int i = 1; i < currentPath.length; i++) {
 			currentPath[i] = 0;
 		}
 		
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
}
