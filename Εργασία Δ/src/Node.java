import java.util.ArrayList;

/**
 * A node of the MinMax tree
 */
public class Node {
	private Node parent; // Node parent
	private ArrayList<Node> children; // Node children's
	private int nodeDepth; // Depth of the node on MinMax tree
	private int[] nodeMove; // Move that represents the node (contains x,y position and dice)
	private Board nodeBoard; // Board of the game at the node move
	private double nodeEvaluation; // Evaluation value of the node
	
	/**
	 * parent setter
	 * @param parent
	 */
	void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * children setter
	 * @param children
	 */
	void setChildren(ArrayList<Node> children) {
		this.children = new ArrayList<Node>(children);
	}
	
	/**
	 * nodeDepth setter
	 * @param nodeDepth
	 */
	void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	
	/**
	 * nodeMove setter
	 * @param nodeMove
	 */
	void setNodeMove(int[] nodeMove) {
		this.nodeMove = new int[nodeMove.length];
		for (int i = 0; i < nodeMove.length; i++) {
			this.nodeMove[i] = nodeMove[i];
		}
	}
	
	/**
	 * nodeBoard setter
	 * @param nodeBoard
	 */
	void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = new Board(nodeBoard);
	}
	
	/**
	 * nodeEvaluation setter
	 * @param nodeEvaluation
	 */
	void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
	/**
	 * parent getter
	 * @return
	 */
	Node getParent() {
		return parent;
	}
	
	/**
	 * children getter
	 * @return
	 */
	ArrayList<Node> getChildren() {
		return children;
	}
	
	/**
	 * nodeDepth getter
	 * @return
	 */
	int getNodeDepth() {
		return nodeDepth;
	}
	
	/**
	 * nodeMove getter
	 * @return
	 */
	int[] getNodeMove() {
		return nodeMove;
	}
	
	/**
	 * nodeBoard getter
	 * @return
	 */
	Board getNodeBoard() {
		return nodeBoard;
	}
	
	/**
	 * nodeEvaluation getter
	 * @return
	 */
	double getNodeEvaluation() {
		return nodeEvaluation;
	}
	
	/**
	 * Empty constructor
	 */
	Node() {
		this.children = new ArrayList<Node>();
	}
	
	/**
	 * Constructor with arguments all Node variables except children
	 * @param parent
	 * @param nodeDepth
	 * @param nodeMove
	 * @param nodeBoard
	 * @param nodeEvaluation
	 */
	Node(Node parent, int nodeDepth, int[] nodeMove, Board nodeBoard, double nodeEvaluation) {
		this.children = new ArrayList<Node>();
		this.parent = parent;
		this.nodeDepth = nodeDepth;
		this.nodeBoard = new Board(nodeBoard);
		this.nodeEvaluation = nodeEvaluation;
		
		this.nodeMove = new int[nodeMove.length];
		for (int i = 0; i < nodeMove.length; i++) {
			this.nodeMove[i] = nodeMove[i];
		}
	}
	
	/**
	 * Constructor with arguments all Node variables
	 * @param parent
	 * @param children
	 * @param nodeDepth
	 * @param nodeMove
	 * @param nodeBoard
	 * @param nodeEvaluation
	 */
	Node(Node parent, ArrayList<Node> children, int nodeDepth, int[] nodeMove, Board nodeBoard, double nodeEvaluation) {
		this.parent = parent;
		this.children = new ArrayList<Node>(children);
		this.nodeDepth = nodeDepth;
		this.nodeBoard = new Board(nodeBoard);
		this.nodeEvaluation = nodeEvaluation;
		
		this.nodeMove = new int[nodeMove.length];
		for (int i = 0; i < nodeMove.length; i++) {
			this.nodeMove[i] = nodeMove[i];
		}
	}
	
	/**
	 * Add child to the children list
	 * @param child 
	 */
	void addChild(Node child) {
		this.children.add(child);
	}
}
