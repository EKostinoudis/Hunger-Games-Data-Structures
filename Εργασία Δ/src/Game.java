import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;  

public class Game {
	int round;
	
	Game() {
		round = 0;
	}
	
	void setRound(int round) {
		this.round = round;
	}
	
	int getRound() {
		return this.round;
	}
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Hunger Games");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout()); 
	    f.add(p);
	    
	    JPanel pBoard;
	    Dimension boardSize = new Dimension(600, 600);
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
	    
	    JPanel bottomPane = new JPanel();
	    bottomPane.setLayout(new FlowLayout());
	    p.add(bottomPane, BorderLayout.PAGE_END);
	    
		// Combo boxes
	    String players[] = {"Random Player", "Heuristic Player", "MinMax Player"};
	    JComboBox cb1 = new JComboBox(players);
	    JComboBox cb2 = new JComboBox(players);
	    
	    // Buttons
	    JButton bGenBoard = new JButton("Generate Board");
	    JButton bPlay = new JButton("Play");
	    bPlay.setEnabled(false);
	    JButton bQuit = new JButton("Quit");
	    bQuit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            f.dispose();
	        }
	    });
	    
	    
	    bottomPane.add(cb1);
	    bottomPane.add(bGenBoard);
	    bottomPane.add(bPlay);
	    bottomPane.add(bQuit);
	    bottomPane.add(cb2);
	    
	    // Add round
	    JLabel round = new JLabel("Round: 0", JLabel.CENTER);
	    round.setFont(new Font(round.getFont().getName(), Font.PLAIN, 20));
    	p.add(round, BorderLayout.PAGE_START);
    	
    	// Player 1 stats
    	JLabel p1Label = new JLabel("<html>Player A<br/>Move Score: 0<br/>Total Score: 0</html>", JLabel.CENTER);
    	p1Label.setFont(new Font(round.getFont().getName(), Font.PLAIN, 20));
    	p1Label.setForeground(Color.blue);
    	p.add(p1Label, BorderLayout.LINE_START);
	    
    	// Player 2 stats
    	JLabel p2Label = new JLabel("<html>Player B<br/>Move Score: 0<br/>Total Score: 0</html>", JLabel.CENTER);
    	p2Label.setFont(new Font(round.getFont().getName(), Font.PLAIN, 20));
    	p2Label.setForeground(Color.red);
    	p.add(p2Label, BorderLayout.LINE_END);
    	
	    // Generate board button action
	    bGenBoard.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	        	Game game = new Game();
	        	
	    		// Create a 20x20 board with 6 guns, 10 supplies and 8 traps
	    		Board b = new Board(20, 20, 6, 10, 8);
	    		
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
	    		Player p1;
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
	    		
	    		Player p2;
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
	    		for (Food f : food) {
					int x = f.getX();
					int y = f.getY();
					x = x >= 0? x-1: x;
					x += 10;
					y = y >= 0? y-1: y;
					y += 10;
					
					int index = y * 20 + x;
					
					JPanel panel = (JPanel)pBoard.getComponent(index);
		    		JLabel piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/apple.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
		    		panel.add(piece);
		    		JLabel points = new JLabel("+" + String.valueOf(f.getPoints()), JLabel.CENTER);
		    		points.resize((int) (panel.getSize().width), (int) (panel.getSize().height));
		    		piece.add(points);
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
	    		for (Weapon w: weapons) {
					int x = w.getX();
					int y = w.getY();
					x = x >= 0? x-1: x;
					x += 10;
					y = y >= 0? y-1: y;
					y += 10;
					
					int index = y * 20 + x;
					
					String type = w.getType();
					
					JPanel panel = (JPanel)pBoard.getComponent(index);
					JLabel piece;
					if(type == "sword") {
			    		piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/sword.jpg").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
					} else if(type == "bow") {
			    		piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/bow.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
					} else {
			    		piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/pistol.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.8), (int) (panel.getSize().height * 0.8), Image.SCALE_DEFAULT)), JLabel.CENTER);
					}
					
		    		panel.add(piece);
		    		JLabel points = new JLabel(String.valueOf(w.getPlayerID()), JLabel.NORTH_EAST);
		    		points.resize((int) (panel.getSize().width * 0.6), (int) (panel.getSize().height * 0.6));
		    		piece.add(points);
				}
	    		
	    		int x = p1.getX();
				int y = p1.getY();
				x = x >= 0? x-1: x;
				x += 10;
				y = y >= 0? y-1: y;
				y += 10;
				int index = y * 20 + x;
				
				JPanel panel = (JPanel)pBoard.getComponent(index);
	    		JLabel piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/p1.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.5), (int) (panel.getSize().height * 0.7), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
	    		panel.add(piece, BorderLayout.LINE_START);
	    		
//	    		JLabel points = new JLabel("+" + String.valueOf(f.getPoints()), JLabel.CENTER);
//	    		points.resize((int) (panel.getSize().width), (int) (panel.getSize().height));
//	    		piece.add(points);
	    		
	    		x = p2.getX();
				y = p2.getY();
				x = x >= 0? x-1: x;
				x += 10;
				y = y >= 0? y-1: y;
				y += 10;
				index = y * 20 + x;
				
				panel = (JPanel)pBoard.getComponent(index);
	    		piece = new JLabel(new ImageIcon(new ImageIcon("./src/files/p2.png").getImage().getScaledInstance((int) (panel.getSize().width * 0.5), (int) (panel.getSize().height * 0.7), Image.SCALE_DEFAULT)), SwingConstants.CENTER);
	    		panel.add(piece, BorderLayout.LINE_END);
	    		
	    		// Disable generate board button
	    		bGenBoard.setEnabled(false);
	    		
	    		// re-paint
	    		SwingUtilities.updateComponentTreeUI(f);
//	    		f.invalidate();
//	    		f.validate();
//	    		f.repaint();
	        }
	    });
	    
	    
	    
	    f.pack();
	    f.setResizable(true);
	    f.setLocationRelativeTo( null );
//	    f.setLayout(null);
//	    f.setSize(600, 600);
	    f.setVisible(true);
		
	    
		
		
//    	Game game = new Game();
//		
//		// Create a 20x20 board with 6 guns, 10 supplies and 8 traps
//		Board b = new Board(20, 20, 6, 10, 8);
//
//		// Create 2 players with starting positions at the top left 
//		// and bottom right and starting score 20
//		MinMaxPlayer p1 = new MinMaxPlayer(1, "Player 1", b, 15, 10, 10);
//		Player p2 = new Player(2, "Player 2", b, 15, 10, 10);
//		
//		// Set r
//		p1.setR(100);
//		
//		// Add all the areas to the board
//		int[][] area = {{-2, 2}, {2, -2}, {-2, -2}, {2, 2}};
//		b.setWeaponAreaLimits(area);
//		area = new int[][] {{-3, 3}, {3, -3}, {-3, -3}, {3, 3}};
//		b.setFoodAreaLimits(area);
//		area = new int[][] {{-4, 4}, {4, -4}, {-4, -4}, {4, 4}};
//		b.setTrapAreaLimits(area);
//		
//		// Create all the weapons, supplies and traps
//		b.createBoard(p1, p2);
//		
//		// Play the game until board has size 4x4
//		// of a player dies
//		boolean dead1 = false;
//		boolean dead2 = false;
//		boolean negScore1 = false;
//		boolean negScore2 = false;
//		do {
//			int[] move1, move2;
//			
//			game.setRound(game.getRound() + 1);
//			
//			// Player 1 and 2 move
//			move1 = p1.getNextMove(p1.getX(), p1.getY(), p2.getX(), p2.getY());
//			dead2 = HeuristicPlayer.kill(p1, p2, 2) && p1.getPistol() != null;
//			negScore1 = p1.getScore() < 0;
//			
//			// If player2 is dead print player1 move and end the game
//			if(dead2 || negScore1) {
//				// Print round
//				System.out.println("Round: " + game.getRound());			
//				// Print the moves of the players
//				System.out.println(p1.getName() + " moved to (" + move1[0] + "," + move1[1] +")");
//				p1.statistics();
//				System.out.println();
//				break;
//			}
//			
//			move2 = p2.move();
//			dead1 = MinMaxPlayer.kill(p2, p1, 2) && p2.getPistol() != null;
//			negScore2 = p2.getScore() < 0;
//			
//			// Print round
//			System.out.println("Round: " + game.getRound());			
//			
//			// Get the string representation of the board
//			String[][] rep = b.getStringRepresentation();
//			
//			// Print board
//			for (int i = 0; i < rep.length; i++) {
//				for (int j = 0; j < rep[i].length; j++) {
//					System.out.print(rep[i][j] + " ");
//				}
//				System.out.println();
//			}
//			
//			// Print the moves of the players
//			System.out.println(p1.getName() + " moved to (" + move1[0] + "," + move1[1] +")");
//			p1.statistics();
//
//			System.out.println(p2.getName() + " moved to (" + move2[0] + "," + move2[1] +
//					   ") Picked: " + move2[2] + " weapons, " + move2[3] + " supplies, " +
//						"Traped: " + move2[4] + " times.");
//			
//			
//			System.out.println();
//			
//			// Resize board every 3 rounds
//			if(game.getRound() % 3 == 2) {
//				b.resizeBoard(p1, p2);
//			}
//		} while(b.getN() > 4 && b.getM() > 4 && !dead1 && !dead2 && !negScore1 && !negScore2);
//		
//		// Print final score
//		System.out.println("-------------------------------------------");
//		System.out.println("End of game. Rounds played: " + game.getRound());
//		System.out.println();
//		System.out.println(p1.getName() + " score: " + p1.getScore());
//		System.out.println(p2.getName() + " score: " + p2.getScore());
//		System.out.println();
//		
//		if(negScore1) {
//			System.out.println(p1.getName() + " has negative points.");
//			System.out.println(p2.getName() + " wins.");
//		} else if(negScore2) {
//			System.out.println(p2.getName() + " has negative points.");
//			System.out.println(p1.getName() + " wins.");
//		}
//		else if(dead1) {
//			System.out.println(p2.getName() + " killed " + p1.getName() + ".");
//			System.out.println(p2.getName() + " wins.");
//		} else if(dead2) {
//			System.out.println(p1.getName() + " killed " + p2.getName() + ".");
//			System.out.println(p1.getName() + " wins.");
//		} else {
//			System.out.println("Reached minimale board size.");
//			if(p1.getScore() > p2.getScore()) {
//				System.out.println(p1.getName() + " wins.");
//			} else if(p2.getScore() > p1.getScore()) {
//				System.out.println(p2.getName() + " wins.");
//			} else {
//				System.out.println("Draw.");
//			}
//		}
	}
}
