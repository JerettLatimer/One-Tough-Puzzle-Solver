
public class Link {
	
	boolean inOut;
	private String name;
	private int position;

	// false = out true = in
	public Link(boolean inOut, String name, int position) {
		setInOut(inOut);
		setName(name);
		setPosition(position);
	}

	public boolean isInOut() {
		return inOut;
	}

	public void setInOut(boolean inOut) {
		this.inOut = inOut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
