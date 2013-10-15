import java.util.Random;


public class Computer extends Player{

	public Computer(GraphicsPanel graphics){
		super(graphics);
	}

	@Override
	public char chooseDirection() {
		char newDirection = ' ';
		Location current = getLocation();
		//while newDirection is null, check each direction
		newDirection = checkDirection('U', current);
		if (newDirection == ' ') {
			newDirection = checkDirection('R', current);
		}
		if (newDirection == ' ') {
			newDirection = checkDirection('D', current);
		}
		if (newDirection == ' ') {
			newDirection = checkDirection('L', current);
		}
		//if it still hasn't been set, choose at random
		if (newDirection == ' ') {
			newDirection = randomDirection();
		}
		return newDirection;
	}
	
	public char checkDirection(char direction, Location location) {
		//if character is stopped, return S to avoid extra executions
		if (direction == 'S') {
			return direction;
		}
		int xChange = 0;
		int yChange = 0;
		int bound = 0;
		//determine which way the player is moving and set deltas accordingly
		switch(direction) {
			case 'U':
				xChange = 0;
				yChange = -1;
				bound = -1;
				break;
			case 'R':
				xChange = 1;
				yChange = 0;
				bound = 10;
				break;
			case 'D':
				xChange = 0;
				yChange = 1;
				bound = 10;
				break;
			case 'L':
				xChange = -1;
				yChange = 0;
				bound = -1;
				break;
		}
		int xPos = location.getX();
		int yPos = location.getY();
		while ((xPos != bound) && (yPos != bound)) {
			xPos += xChange;
			yPos += yChange;
			//if the spot is blocked return null
			if(graphics.isBlocked(new Location(xPos, yPos), true)) {
				return ' ';
			}
			//if the spot is a target, return the current direction
			if(graphics.isTarget(new Location(xPos, yPos))) {
				return direction;
			}
		}
		//if xPos or yPos = bound, return null
		if ((xPos == bound) || (yPos == bound)) {
			return ' ';
		}
		return direction;
	}

	@Override
	public void initializePosition() {
		Random gen = new Random();
		int x = gen.nextInt(10);
		int y = gen.nextInt(10);
		Location start = new Location(x,y);
		setLocation(start);	
	}
	
	private char randomDirection() {
		Location currentLoc = getLocation();
		String validDirs = "";
		//check if each direction is blocked, if not add character to validDirs
		//right
		if((currentLoc.getX() < 9) && (!graphics.isBlocked(new Location(currentLoc.getX() + 1, currentLoc.getY()), true))) {
			validDirs += "R";
		}
		//left
		if((currentLoc.getX() >= 0) && (!graphics.isBlocked(new Location(currentLoc.getX() - 1, currentLoc.getY()), true))) {
			validDirs += "L";
		}
		//down
		if((currentLoc.getY() < 9) && (!graphics.isBlocked(new Location(currentLoc.getX(), currentLoc.getY() + 1), true))) {
			validDirs += "D";
		}
		//up
		if((currentLoc.getX() >= 0) && (!graphics.isBlocked(new Location(currentLoc.getX(), currentLoc.getY() - 1), true))) {
			validDirs += "U";
		}
		
		
		if(validDirs.length() == 0) {
			return 'S';
		}
		else {
			Random gen = new Random();
			int index = gen.nextInt(validDirs.length());
			return validDirs.charAt(index);
		}
	}

}
