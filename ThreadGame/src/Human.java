
public class Human extends Player{

	public Human(GraphicsPanel graphics) {
		super(graphics);
	}

	@Override
	public char chooseDirection() {
		return getDirection();
	}

	@Override
	public void initializePosition() {
		setLocation(new Location(0,0));
	}

}
