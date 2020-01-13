import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;  

public class Game {
	private int round;
	private JFrame f;
	private JPanel p;
	private Player p1, p2;
	private JLabel p1Panel;
	private JLabel p2Panel;
	private JLabel[] weaponPanels;
	private boolean[] weaponPanelsDone;
	private JLabel[] foodPanels;
	private boolean[] foodPanelsDone;
	private JLabel roundPanel;
	private JLabel p1Label, p2Label;
	private JPanel p1Collection, p2Collection;
	private JPanel pBoard;
	private JComboBox cb1, cb2;
	private JButton bGenBoard, bPlay;
	private JPanel p1InfoPanel, p2InfoPanel;
	private Board b;
	
	/**
	 * Creates the main JFrame
	 */
	private void genarateFrame() {
		f = new JFrame("Hunger Games");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Initialize Board of the game.
	 * Not the players, food, guns and traps.
	 */
	private void initMainBoard() {
		// Remove collected items if exists
		if(p1Collection != null) {
			p1InfoPanel.remove(p1Collection);
		}
		if(p2Collection != null) {
			p2InfoPanel.remove(p2Collection);
		}
		
		// Create collections
		p1Collection = new JPanel(new GridLayout(0, 4));
    	p1InfoPanel.add(p1Collection, BorderLayout.NORTH);
    	p2Collection = new JPanel(new GridLayout(0, 4));
    	p2InfoPanel.add(p2Collection, BorderLayout.NORTH);

		// Remove previous board if exists
    	Dimension boardSize;
		if(pBoard != null) {
			boardSize = new Dimension(pBoard.getWidth(), pBoard.getHeight());
			p.remove(pBoard);
		} else {
			boardSize = new Dimension(600, 600);
		}
		pBoard = new JPanel();
		p.add(pBoard, BorderLayout.CENTER);
	    pBoard.setLayout(new GridLayout(20, 20));
	    pBoard.setPreferredSize(boardSize);
	    pBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	   
	    for(int i = -10; i < 10; i++) {
	    	for(int j = -10; j < 10; j++) {
		    	JPanel square = new JPanel(new BorderLayout());
		    	square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				pBoard.add(square);
				if(i >= -2 && i < 2 && j >= -2 && j < 2) {
					square.setBackground(Color.white);
				} else if(i >= -3 && i < 3 && j >= -3 && j < 3) {
					square.setBackground(Color.gray);
				} else if(i >= -4 && i < 4 && j >= -4 && j < 4) {
					square.setBackground(Color.blue);
				} else {
					square.setBackground(Color.red);
				}			
	    	}
	    }
	    
	    p.validate();
		p.repaint();
	}
	
	/**
	 * Open a window with the gui of the game
	 */
	void startGUI() {
		genarateFrame();
			
		p = new JPanel();
	    p.setLayout(new BorderLayout()); 
	    f.add(p);
	    
	    JPanel bottomPane = new JPanel();
	    bottomPane.setLayout(new FlowLayout());
	    p.add(bottomPane, BorderLayout.PAGE_END);
	    
		// Combo boxes
	    String players[] = {"Random Player", "Heuristic Player", "MinMax Player"};
	    cb1 = new JComboBox(players);
	    cb2 = new JComboBox(players);
	    
	    // Buttons
	    bGenBoard = new JButton("Generate Board");
	    bPlay = new JButton("Play");
	    bPlay.setEnabled(false);
	    JButton bQuit = new JButton("Quit");
	    bQuit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
//	            f.dispose();
	            System.exit(0);
	        }
	    });
	    
	    bottomPane.add(cb1);
	    bottomPane.add(bGenBoard);
	    bottomPane.add(bPlay);
	    bottomPane.add(bQuit);
	    bottomPane.add(cb2);
	    
	    // Add round
	    roundPanel = new JLabel("Round: 0", JLabel.CENTER);
	    roundPanel.setFont(new Font(roundPanel.getFont().getName(), Font.PLAIN, 20));
    	p.add(roundPanel, BorderLayout.PAGE_START);
    	
    	// Player 1 status
    	p1InfoPanel = new JPanel(new BorderLayout());
    	p.add(p1InfoPanel, BorderLayout.LINE_START);
    	p1Label = new JLabel("<html>Player A<br/>Move Score: 0<br/>Total Score: 0</html>", JLabel.CENTER);
    	p1Label.setFont(new Font(p1Label.getFont().getName(), Font.PLAIN, 20));
    	p1Label.setForeground(Color.blue);
    	p1InfoPanel.add(p1Label, BorderLayout.SOUTH);
	    
    	// Player 2 status
    	p2InfoPanel = new JPanel(new BorderLayout());
    	p.add(p2InfoPanel, BorderLayout.LINE_END);
    	p2Label = new JLabel("<html>Player B<br/>Move Score: 0<br/>Total Score: 0</html>", JLabel.CENTER);
    	p2Label.setFont(new Font(p2Label.getFont().getName(), Font.PLAIN, 20));
    	p2Label.setForeground(Color.red);
    	p2InfoPanel.add(p2Label, BorderLayout.SOUTH);
    	
	    // Generate board button action
	    bGenBoard.addActionListener(e -> generateBoard());
	    
	    // Play button action
	    bPlay.addActionListener(e -> playTheGame());
	    
	    // Create main board
	    initMainBoard();
	    
	    f.pack();
	    f.setResizable(true);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	}
	
	/**
	 * Creates board, players and puts them at the GUI
	 */
	private void generateBoard(){
		// Set and show round
		round = 0;
		roundPanel.setText("Round: " + round);
		
		initMainBoard();
		
		// Create a 20x20 board with 6 guns, 10 supplies and 8 traps
		b = new Board(20, 20, 6, 10, 8);
		
		// Add all the areas to the board
		int[][] area = {{-2, 2}, {2, -2}, {-2, -2}, {2, 2}};
		b.setWeaponAreaLimits(area);
		area = new int[][] {{-3, 3}, {3, -3}, {-3, -3}, {3, 3}};
		b.setFoodAreaLimits(area);
		area = new int[][] {{-4, 4}, {4, -4}, {-4, -4}, {4, 4}};
		b.setTrapAreaLimits(area);
		
		bPlay.setEnabled(true);
		
		int pos1 = cb1.getSelectedIndex();
		int pos2 = cb2.getSelectedIndex();

		switch(pos1) {
		case 0:
			p1 = new Player(1, "Player 1", b, 15, 10, 10);
			break;
		case 1:
			p1 = new HeuristicPlayer(1, "Player 1", b, 15, 10, 10);
			((HeuristicPlayer) p1).setR(3);
			break;
		case 2:
			p1 = new MinMaxPlayer(1, "Player 1", b, 15, 10, 10);
			((MinMaxPlayer) p1).setR(100);
			break;
		default:
			p1 = new Player(1, "Player 1", b, 15, 10, 10);
		}
		
		switch(pos2) {
		case 0:
			p2 = new Player(2, "Player 2", b, 15, 10, 10);
			break;
		case 1:
			p2 = new HeuristicPlayer(2, "Player 2", b, 15, 10, 10);
			((HeuristicPlayer) p2).setR(3);
			break;
		case 2:
			p2 = new MinMaxPlayer(2, "Player 2", b, 15, 10, 10);
			((MinMaxPlayer) p2).setR(100);
			break;
		default:
			p2 = new Player(2, "Player 2", b, 15, 10, 10);
		}
		
		// Create and add all the weapons, supplies and traps
		b.createBoard(p1, p2);
		
		Food[] food = b.getFood();
		foodPanels = new JLabel[food.length];
		foodPanelsDone = new boolean[food.length];
		int count = 0;
		for (Food f : food) {
			foodPanelsDone[count] = false;
			
			int x = f.getX();
			int y = f.getY();
			x = x >= 0? x-1: x;
			x += 10;
			y = y >= 0? y-1: y;
			y += 10;
			
			int index = y * 20 + x;
			
			JPanel panel = (JPanel)pBoard.getComponent(index);
			foodPanels[count] = new JLabel(new ImageIcon(new ImageIcon("./src/files/apple.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
    		panel.add(foodPanels[count]);
    		JLabel points = new JLabel("+" + String.valueOf(f.getPoints()), JLabel.CENTER);
    		points.resize((int) (panel.getSize().width), (int) (panel.getSize().height));
    		foodPanels[count].add(points);
    		
    		count++;
		}
		
		Trap[] traps = b.getTraps();
		for (Trap t: traps) {
			int x = t.getX();
			int y = t.getY();
			x = x >= 0? x-1: x;
			x += 10;
			y = y >= 0? y-1: y;
			y += 10;
			
			int index = y * 20 + x;
			
			String type = t.getType();
			
			JPanel panel = (JPanel)pBoard.getComponent(index);
			JLabel piece;
			if(type == "ropes") {
	    		piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/rope.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.6), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
			} else {
	    		piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/animal.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.6), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
			}
			
    		panel.add(piece);
    		JLabel points = new JLabel(String.valueOf(t.getPoints()), JLabel.NORTH_EAST);
    		points.setForeground(Color.white);
    		points.resize((int) (panel.getSize().width * 0.6), (int) (panel.getSize().height * 0.6));
    		piece.add(points);
		}
		
		Weapon[] weapons = b.getWeapons();
		weaponPanels = new JLabel[weapons.length];
		weaponPanelsDone = new boolean[weapons.length];
		count = 0;
		for (Weapon w: weapons) {
			weaponPanelsDone[count] = false;
			int x = w.getX();
			int y = w.getY();
			x = x >= 0? x-1: x;
			x += 10;
			y = y >= 0? y-1: y;
			y += 10;
			
			int index = y * 20 + x;
			
			String type = w.getType();
			
			JPanel panel = (JPanel)pBoard.getComponent(index);
			if(type == "sword") {
				weaponPanels[count] = new JLabel(new ImageIcon(new ImageIcon("./src/files/sword.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
			} else if(type == "bow") {
				weaponPanels[count] = new JLabel(new ImageIcon(new ImageIcon("./src/files/bow.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
			} else {
				weaponPanels[count] = new JLabel(new ImageIcon(new ImageIcon("./src/files/pistol.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
			}
			
    		panel.add(weaponPanels[count]);
    		JLabel points = new JLabel(String.valueOf(w.getPlayerID()), JLabel.NORTH_EAST);
    		points.resize((int) (panel.getSize().width * 0.6), (int) (panel.getSize().height * 0.6));
    		weaponPanels[count].add(points);
    		
    		count++;
		}
		
		int x = p1.getX();
		int y = p1.getY();
		x = x >= 0? x-1: x;
		x += 10;
		y = y >= 0? y-1: y;
		y += 10;
		int index = y * 20 + x;
		
		JPanel panel = (JPanel)pBoard.getComponent(index);
		p1Panel = new JLabel(new ImageIcon(new ImageIcon("./src/files/p1.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.5), (int) (panel.getSize().height * 0.7), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
		panel.add(p1Panel, BorderLayout.WEST);

		x = p2.getX();
		y = p2.getY();
		x = x >= 0? x-1: x;
		x += 10;
		y = y >= 0? y-1: y;
		y += 10;
		index = y * 20 + x;
		
		panel = (JPanel)pBoard.getComponent(index);
		p2Panel = new JLabel(new ImageIcon(new ImageIcon("./src/files/p2.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.5), (int) (panel.getSize().height * 0.7), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
		panel.add(p2Panel, BorderLayout.EAST);
		
		// Add players new status
	    p1Label.setText("<html>Player A<br/>Move Score: 0<br/>Total Score: " + p1.getScore() + "</html>");
	    p2Label.setText("<html>Player B<br/>Move Score: 0<br/>Total Score: " + p2.getScore() + "</html>");
		
		// re-paint
		SwingUtilities.updateComponentTreeUI(f);
    }
	
	/**
	 * Plays the game if user selects a player to start.
	 */
    private void playTheGame() {
    	Thread t = new Thread(new Runnable() {
            public void run () {
				// Create an option pane for the player who plays first
				boolean p1Move = true;
				int result = JOptionPane.showConfirmDialog(null, "Do you want Player A play first? (No for Player B)");
				switch (result) {
					case JOptionPane.YES_OPTION:
						p1Move = true;
						break;
					case JOptionPane.NO_OPTION:
						p1Move = false;
						break;
					case JOptionPane.CANCEL_OPTION:
						// No action
						return;
					case JOptionPane.CLOSED_OPTION:
						// No action
						return;
				}
				
				// Disable generate board button, combo boxes and play button
				bGenBoard.setEnabled(false);
				cb1.setEnabled(false);
				cb2.setEnabled(false);
				bPlay.setEnabled(false);
				
				int[] status;
				int moveCounter = 2;
				while(true) {
					if(p1Move) {
						// Remove from board
						Container parent = p1Panel.getParent();
						parent.remove(p1Panel);
						parent.validate();
						parent.repaint();
						
						// Get next move
						status = playerMove(p1, p2);
						
						// Update status text
						p1Label.setText("<html>Player A<br/>Move Score: " + status[0] + "<br/>Total Score: " + p1.getScore() + "</html>");
					
						// Add new position to the board
						int x = p1.getX();
						int y = p1.getY();
						x = x >= 0? x-1: x;
						x += 10;
						y = y >= 0? y-1: y;
						y += 10;
						int index = y * 20 + x;
						JPanel panel = (JPanel)pBoard.getComponent(index);
			    		panel.add(p1Panel, BorderLayout.WEST);
			    		
			    		p1Move = false;
			    		moveCounter++;
					} else {
						// Remove from board
						Container parent = p2Panel.getParent();
						parent.remove(p2Panel);
						parent.validate();
						parent.repaint();
						
						// Get next move
						status = playerMove(p2, p1);
						
						// Update status text
						p2Label.setText("<html>Player B<br/>Move Score: " + status[0] + "<br/>Total Score: " + p2.getScore() + "</html>");
						
						// Add new position to the board
						int x = p2.getX();
						int y = p2.getY();
						x = x >= 0? x-1: x;
						x += 10;
						y = y >= 0? y-1: y;
						y += 10;
						int index = y * 20 + x;
						JPanel panel = (JPanel)pBoard.getComponent(index);
						panel.add(p2Panel, BorderLayout.EAST);
						
						p1Move = true;
						moveCounter++;
					}
					
					// Update round
					if(moveCounter >= 2) {
						round++;
						moveCounter = 0;
						
						roundPanel.setText("Round: " + round);
					}
					
					// Resize board every 3 rounds
					if(round % 3 == 1 && moveCounter == 0) {
						boolean resized = b.resizeBoard(p1, p2);
						
						if(resized) {
							// Change color to the resized blocks
							int layer = (b.getN() / 2) + 1;
							
							int index1 = 10 - layer;
							int index2 = layer + 9;
							
							for(int i = 0; i < 20; i++) {
								for(int j = 0; j < 20; j++) {
									if(i == index1 || i == index2 || j == index1 || j == index2) {
										int index = i *20 + j;
										JPanel panel = (JPanel)pBoard.getComponent(index);
										panel.setBackground(Color.DARK_GRAY);
									}
								}
							}
							
						}
					}
		
					// repaint
					f.validate();
		            f.pack();
		            f.repaint();
		            
		
		            try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		            
		            if(b.getN() <= 4 || b.getM() <= 4) {
		            	if(p1.getScore() > p2.getScore()) {
		            		endGame("Reached minimal board size. Player A wins!");
		            	} else if(p1.getScore() < p2.getScore()) {
		            		endGame("Reached minimal board size. Player B wins!");
		            	} else {
		            		endGame("Reached minimal board size. Draw!");
		            	}
		            	
		            	break;
		            }
		            
					// Player killed the other
					if(status[1] == 1) {
						if(p1Move) {
							killP1();
							endGame("Player B killed Player A. Player B wins!");
						} else {
							killP2();
							endGame("Player A killed Player B. Player A wins!");
						}
						
						break;
					}
			
					// Player has negative points
					if(status[2] == 1) {
						if(p1Move) {
							killP2();
							endGame("Player B have negative points. Player A wins!");
						} else {
							killP1();
							endGame("Player A have negative points. Player B wins!");
						}
						
						break;
					}
				}
            }
		});
    	
    	// Start thread
    	t.start();
	}
    
	/**
	 * Make a move
	 * @param p player
	 * @param op opponent
	 * @return integer array with (move score, opponent kill, negative score)
	 */
	private int[] playerMove(Player p, Player op) {
		// make the move and take the move score
		int[] move;
		int score;
		if(p.getClass() == Player.class) {
			move = p.move();
			score = move[3] + move[4];
		} else if(p.getClass() == HeuristicPlayer.class) {
			move = ((HeuristicPlayer) p).move(op);
			score = ((HeuristicPlayer) p).getPath().get(((HeuristicPlayer) p).getPath().size() - 1)[1];
		} else {
			move = ((MinMaxPlayer) p).getNextMove(p.getX(), p.getY(), op.getX(), op.getY());
			score = ((MinMaxPlayer) p).getPath().get(((MinMaxPlayer) p).getPath().size() - 1)[1];
		}
		
		// Check if player picked food and add it to the players list
		Food[] food = b.getFood();
		for(int i = 0; i < food.length; i++) {
			Food f = food[i];
			if(f.getX() == 0 && f.getY() == 0 && !foodPanelsDone[i]) {
				// Remove from board
				Container parent = foodPanels[i].getParent();
				parent.remove(foodPanels[i]);
				parent.validate();
				parent.repaint();
				
				if(p.getId() == 1) {
					p1Collection.add(foodPanels[i]);
				} else {
					p2Collection.add(foodPanels[i]);
				}
				
				foodPanelsDone[i] = true;
			}
		}
		
		// Check if player picked a weapon and add it to the players list
		Weapon[] weapons = b.getWeapons();
		for(int i = 0; i < weapons.length; i++) {
			Weapon w = weapons[i];
			if(w.getX() == 0 && w.getY() == 0 && !weaponPanelsDone[i]) {
				// Remove from board
				Container parent = weaponPanels[i].getParent();
				parent.remove(weaponPanels[i]);
				parent.validate();
				parent.repaint();
				
				if(p.getId() == 1) {
					p1Collection.add(weaponPanels[i]);
				} else {
					p2Collection.add(weaponPanels[i]);
				}
				
				weaponPanelsDone[i] = true;
			}
		}
		
		
		boolean deadOp,negScore;
		deadOp = HeuristicPlayer.kill(p, op, 2) && p.getPistol() != null;
		negScore = p.getScore() < 0;
		
		int[] ret = {score, 0, 0};
		if(deadOp) ret[1] = 1;
		if(negScore) ret[2] = 1;
		
		return ret;
	}
	
	/**
	 * Rotates P1 90 degrees
	 */
	private void killP1() {
		Container parent = p1Panel.getParent();
		parent.remove(p1Panel);
		
		// Add new position to the board
		int x = p1.getX();
		int y = p1.getY();
		x = x >= 0? x-1: x;
		x += 10;
		y = y >= 0? y-1: y;
		y += 10;
		int index = y * 20 + x;
		JPanel panel = (JPanel)pBoard.getComponent(index);
		
		BufferedImage original;
		BufferedImage rotated90;
		try {
			original = ImageIO.read(new File("./src/files/p1.png"));
			
			rotated90 = rotate(original, 90.0d);
		    
			p1Panel = new JLabel(new ImageIcon(new ImageIcon(rotated90).getImage().getScaledInstance((int) (panel.getSize().width * 0.5), (int) (panel.getSize().height * 0.7), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
			panel.add(p1Panel, BorderLayout.WEST);
		}
	    catch (IOException e) {

			e.printStackTrace();
		}
		
		parent.validate();
		parent.repaint();
	}
	
	/**
	 * Rotates P2 -90 degrees
	 */
	private void killP2() {
		Container parent = p2Panel.getParent();
		parent.remove(p2Panel);
		
		// Add new position to the board
		int x = p2.getX();
		int y = p2.getY();
		x = x >= 0? x-1: x;
		x += 10;
		y = y >= 0? y-1: y;
		y += 10;
		int index = y * 20 + x;
		JPanel panel = (JPanel)pBoard.getComponent(index);
		
		BufferedImage original;
		BufferedImage rotated90;
		try {
			original = ImageIO.read(new File("./src/files/p2.png"));
			
			rotated90 = rotate(original, -90.0d);
		    
			p2Panel = new JLabel(new ImageIcon(new ImageIcon(rotated90).getImage().getScaledInstance((int) (panel.getSize().width * 0.5), (int) (panel.getSize().height * 0.7), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
			panel.add(p2Panel, BorderLayout.EAST);
		}
	    catch (IOException e) {

			e.printStackTrace();
		}
		
		parent.validate();
		parent.repaint();
	}
	
	/**
	 * Puts message at an option pane
	 * @param message
	 */
	private void endGame(String message) {
		JOptionPane.showMessageDialog(f, message, "End Game", JOptionPane.INFORMATION_MESSAGE);
		
		// Enable combo boxes and generate board button
		cb1.setEnabled(true);
		cb2.setEnabled(true);
		bGenBoard.setEnabled(true);
	}
	
	Game() {
		round = 0;
	}
	
	void setRound(int round) {
		this.round = round;
	}
	
	int getRound() {
		return this.round;
	}
	
	JFrame getFrame() {
		return f;
	}

	/**
	 * Rotates an image
	 * @param image image to rotate
	 * @param degrees degrees to rotate
	 * @return rotated image
	 */
	private BufferedImage rotate(BufferedImage image, Double degrees) {
	    // Calculate the new size of the image based on the angle of rotaion
	    double radians = Math.toRadians(degrees);
	    double sin = Math.abs(Math.sin(radians));
	    double cos = Math.abs(Math.cos(radians));
	    int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
	    int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

	    // Create a new image
	    BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = rotate.createGraphics();
	    
	    // Calculate the "anchor" point around which the image will be rotated
	    int x = (newWidth - image.getWidth()) / 2;
	    int y = (newHeight - image.getHeight()) / 2;
	    
	    // Transform the origin point around the anchor point
	    AffineTransform at = new AffineTransform();
	    at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
	    at.translate(x, y);
	    g2d.setTransform(at);
	    
	    // Paint the original image
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();
	    
	    return rotate;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.startGUI();
	}
}
