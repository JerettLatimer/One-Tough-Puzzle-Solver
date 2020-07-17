import java.util.ArrayList;

public class Main {
	
	private final static int TOP = 0;
	private final static int RIGHT = 1;
	private final static int BOTTOM = 2;
	private final static int LEFT = 3;
	
	
	private final static String CIRCLE = "HC";
	private final static String SQUARE = "SQ";
	private final static String SINGLE_TRIANGLE = "ST";
	private final static String DOUBLE_TRIANGLE = "DT";
	

	public static void main(String[] args) {
		long start = System.nanoTime();
		PuzzlePiece[][] puzzle = new PuzzlePiece[3][3];
		ArrayList<PuzzlePiece> list = createPieces();
		int i = 0;
		int j = 0;
		boolean result = solvePuzzle(list, puzzle, i, j);
		if(result) {
			long end = System.nanoTime();
			double time = (end - start) / 1000000.0;
			System.out.println("Solved in: " + time + " Milliseconds\n");
			printBoard(puzzle);
		}
		else {
			System.out.println("Not Solved");
		}
	}

	
	private static void printBoard(PuzzlePiece[][] puzzle) {
		System.out.println("DT = Double Triangle \n"
				+ "ST = Single Triangle \n"
				+ "HC = Half Circle \n"
				+ "SQ = Square \n"
				+ "(in) = Female Link \n"
				+ "(out) = Male Link\n\n"
				+ "PUZZLE ID NUMBER: e0f84ebc \n\n\n"
				+ "Solved Puzzle: \n");
		System.out.println("--------------------------------------------------------------------------"
				+ "----------");
		int j = 0;
		for(int i = 0; i < puzzle.length; i++) {
			System.out.println("|          " + puzzle[i][j].getTop().getName()
					+ printInOut(puzzle[i][j].getTop().inOut)
					+ "          |           " + puzzle[i][j + 1].getTop().getName()
					+ printInOut(puzzle[i][j + 1].getTop().inOut)
					+ "          |           " + puzzle[i][j + 2].getTop().getName()
					+ printInOut(puzzle[i][j].getTop().inOut) + "          |");
			
			System.out.println("| " + puzzle[i][j].getLeft().getName()
					+ printInOut(puzzle[i][j].getLeft().inOut)
					+ " (Piece#" + puzzle[i][j].getPieceNum()
					+ ") " + puzzle[i][j].getRight().getName()
					+ printInOut(puzzle[i][j].getRight().inOut)
					+ " |  " + puzzle[i][j + 1].getLeft().getName()
					+ printInOut(puzzle[i][j + 1].getLeft().inOut)
					+ " (Piece#" + puzzle[i][j + 1].getPieceNum()
					+ ") " + puzzle[i][j + 1].getRight().getName()
					+ printInOut(puzzle[i][j + 1].getRight().inOut)
					+ " |  " + puzzle[i][j + 2].getLeft().getName()
					+ printInOut(puzzle[i][j + 2].getLeft().inOut)
					+ " (Piece#" + puzzle[i][j + 2].getPieceNum()
					+ ") " + puzzle[i][j + 2].getRight().getName()
					+ printInOut(puzzle[i][j + 2].getRight().inOut)
					+" |");
			
			System.out.println("|          " + puzzle[i][j].getBottom().getName()
					+ printInOut(puzzle[i][j].getBottom().inOut)
					+ "         |           " + puzzle[i][j + 1].getBottom().getName()
					+ printInOut(puzzle[i][j + 1].getBottom().inOut)
					+ "         |           " + puzzle[i][j + 2].getBottom().getName() 
					+ printInOut(puzzle[i][j + 2].getBottom().inOut)
					+ "         |  ");
			System.out.println("--------------------------------------------------------------------------"
					+ "----------");
		}
	}
	
	private static String printInOut(boolean inOut) {
		String result = "";
		if(inOut) {
			return result = "(in)";
		}
		return result = "(out)";
	}

	private static boolean solvePuzzle(ArrayList<PuzzlePiece> list, PuzzlePiece[][] puzzle, 
										int i, int j) {
		if(j == puzzle[i].length) {
			j = 0;
			i++;
		}
		if(puzzle[2][2] != null) {
			return true;
		}
		int loopVar = 0;
		for(PuzzlePiece p = list.get(loopVar); loopVar < list.size(); loopVar++) {
			p = list.get(loopVar);
			if(canPlace(puzzle, p, i, j)) {
				puzzle[i][j] = p;
				list.remove(p);
				if(solvePuzzle(list, puzzle, i, j + 1)) {
					return true;
				}
				else {
					puzzle[i][j] = null;
					list.add(0,p);
				}
			}
			else {
				int x = 0;
				while(x < 3) {
					p.rotate();
					if(canPlace(puzzle, p, i, j)) {
						puzzle[i][j] = p;
						list.remove(p);
						if(solvePuzzle(list, puzzle, i, j + 1)) {
							return true;
						}
						else {
							puzzle[i][j] = null;
							list.add(0,p);
						}
					}
					x++;
				}
			}
		}
		return false;
	}

	private static boolean canPlace(PuzzlePiece[][] puzzle, PuzzlePiece p, int i, int j) {
		if(i == 0 && j == 0) {
			return true;
		}
		if(checkLeft(puzzle, p, i, j) && checkRight(puzzle, p, i, j) && checkTop(puzzle, p, i, j)
			&& checkBottom(puzzle, p, i, j)) {
			return true;
		}
		return false;
	}


	private static boolean checkBottom(PuzzlePiece[][] puzzle, PuzzlePiece p, int i, int j) {
		if(i == 2 || puzzle[i + 1][j] == null) {
			return true;
		}
		if(p.getBottom().getName().equals(puzzle[i + 1][j].getTop().getName()) 
			&& p.getBottom().inOut != puzzle[i + 1][j].getTop().inOut) {
			return true;
		}
		return false;
	}


	private static boolean checkTop(PuzzlePiece[][] puzzle, PuzzlePiece p, int i, int j) {
		if(i == 0) {
			return true;
		}
		if(p.getTop().getName().equals(puzzle[i - 1][j].getBottom().getName()) 
			&& p.getTop().inOut != puzzle[i - 1][j].getBottom().inOut) {
			return true;
		}
		return false;
	}


	private static boolean checkRight(PuzzlePiece[][] puzzle, PuzzlePiece p, int i, int j) {
		if(j == 2 || puzzle[i][j + 1] == null) {
			return true;
		}
		if(p.getRight().getName().equals(puzzle[i][j + 1].getLeft().getName()) 
			&& p.getRight().inOut != puzzle[i][j + 1].getLeft().inOut) {
			return true;
		}
		return false;
	}


	private static boolean checkLeft(PuzzlePiece[][] puzzle, PuzzlePiece p, int i, int j) {
		if(j == 0) {
			return true;
		}
		if(p.getLeft().getName().equals(puzzle[i][j - 1].getRight().getName()) 
			&& p.getLeft().inOut != puzzle[i][j - 1].getRight().inOut) {
			return true;
		}
		return false;
	}


	public static final ArrayList<PuzzlePiece> createPieces() {
		ArrayList<PuzzlePiece> list = new ArrayList<PuzzlePiece>();
		
		list.add(new PuzzlePiece(new Link(false, CIRCLE, TOP),
								new Link(false, SQUARE, RIGHT),
								new Link(true, CIRCLE, BOTTOM),
								new Link(true, SQUARE, LEFT), 0));
		
		list.add(new PuzzlePiece(new Link(false, DOUBLE_TRIANGLE, TOP),
								new Link(false, SINGLE_TRIANGLE, RIGHT),
								new Link(true, DOUBLE_TRIANGLE, BOTTOM),
								new Link(true, SINGLE_TRIANGLE, LEFT), 1));
		
		list.add(new PuzzlePiece(new Link(false, SQUARE, TOP),
								new Link(false, CIRCLE, RIGHT),
								new Link(true, CIRCLE, BOTTOM),
								new Link(true, SINGLE_TRIANGLE, LEFT), 2));

		list.add(new PuzzlePiece(new Link(false, SQUARE, TOP),
								new Link(false, CIRCLE, RIGHT),
								new Link(true, CIRCLE, BOTTOM),
								new Link(true, SQUARE, LEFT), 3));
		
		list.add(new PuzzlePiece(new Link(false, CIRCLE, TOP),
								new Link(false, CIRCLE, RIGHT),
								new Link(true, CIRCLE, BOTTOM),
								new Link(true, SQUARE, LEFT), 4));
		
		list.add(new PuzzlePiece(new Link(false, DOUBLE_TRIANGLE, TOP),
								new Link(false, CIRCLE, RIGHT),
								new Link(true, SINGLE_TRIANGLE, BOTTOM),
								new Link(true, CIRCLE, LEFT), 5));

		list.add(new PuzzlePiece(new Link(false, SINGLE_TRIANGLE, TOP),
								new Link(false, SQUARE, RIGHT),
								new Link(true, CIRCLE, BOTTOM),
								new Link(true, CIRCLE, LEFT), 6));
		
		list.add(new PuzzlePiece(new Link(false, CIRCLE, TOP),
								new Link(false, SQUARE, RIGHT),
								new Link(true, SINGLE_TRIANGLE, BOTTOM),
								new Link(true, CIRCLE, LEFT), 7));

		list.add(new PuzzlePiece(new Link(false, DOUBLE_TRIANGLE, TOP),
								new Link(false, SQUARE, RIGHT),
								new Link(true, SQUARE, BOTTOM),
								new Link(true, SQUARE, LEFT), 8));
		
		
		return list;
	}
}
