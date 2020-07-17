/* 
 * The actual puzzle link is represented as a boolean.
 * false = outer link
 * true = inner link
 */

public class PuzzlePiece {
	
	private Link top, left, right, bottom;
	private int pieceNum;

	public PuzzlePiece(Link top, Link right, Link bottom, Link left, int pieceNum) {
		setTop(top);
		setRight(right);
		setBottom(bottom);
		setLeft(left);
		setPieceNum(pieceNum);
	}

	public void rotate() {
		Link temp1, temp2;
		
		temp1 = this.top;
		this.top = this.left;
		
		temp2 = this.right;
		this.right = temp1;
		
		temp1 = this.bottom;
		this.bottom = temp2;
		
		temp2 = this.left;
		this.left = temp1;
	}
	
	public int getPieceNum() {
		return pieceNum;
	}

	public void setPieceNum(int pieceNum) {
		this.pieceNum = pieceNum;
	}
	
	public Link getTop() {
		return top;
	}

	public void setTop(Link top) {
		this.top = top;
	}

	public Link getLeft() {
		return left;
	}

	public void setLeft(Link left) {
		this.left = left;
	}

	public Link getRight() {
		return right;
	}

	public void setRight(Link right) {
		this.right = right;
	}

	public Link getBottom() {
		return bottom;
	}

	public void setBottom(Link bottom) {
		this.bottom = bottom;
	}
	
}
