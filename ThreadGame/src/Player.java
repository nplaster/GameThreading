
public abstract class Player implements Runnable {
	
	protected static GraphicsPanel graphics;
	private Thread playerthread;
	private Location location;
	private char direction;
	private int points;
	
	public Player(GraphicsPanel graphics) {
		direction = 'S';
		initializePosition();
		this.graphics = graphics;
	}

	public void run() {
		//infinite loop
		while (Thread.currentThread() == playerthread) {
			//player moves
			move();
			//then sleeps
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void move() {
		setDirection(chooseDirection());
		Location nextLocation = null;
		
		switch (getDirection()) {
		case 'R':
			nextLocation = new Location(getLocation().getX() + 1, getLocation().getY());
			break;
		case 'L':
			nextLocation = new Location(getLocation().getX() - 1, getLocation().getY());
			break;
		case 'U':
			nextLocation = new Location(getLocation().getX(), getLocation().getY() - 1);
			break;
		case 'D':
			nextLocation = new Location(getLocation().getX(), getLocation().getY() + 1);
			break;
		case 'S':
			nextLocation = getLocation();
			break;
		default:
			nextLocation = new Location(0,0);
		}
		//check if spot is blocked
		if(graphics.isBlocked(nextLocation, true)) {
			setDirection('S');
		}
		//if it isn't set location
		else {
			setLocation(nextLocation);
		}
		
		//check for point
		if(graphics.checkForPoint(getLocation())) {
			addPoints();
		}
	}
	
	public char getDirection() {
		return this.direction;
	}
	
	public abstract char chooseDirection();
	
	public abstract void initializePosition();
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return this.location = location;
	}

	public void start() {
		playerthread = new Thread(this);
		playerthread.start();
	}

	public void stopPlaying() {
		playerthread = null;	
	}

	public int getPoints() {
		return this.points;
	}
	
	public void addPoints() {
		this.points +=1;
	}

	public void setDirection(char c) {
		direction = c;
	}
	
	public void stopThread() {
		this.playerthread = null;
	}

}
