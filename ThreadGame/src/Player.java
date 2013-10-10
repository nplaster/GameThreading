
public abstract class Player implements Runnable{
	protected static GraphicsPanel graphics;
	private Thread playertheard;
	private Location location;
	private char direction;
	private int points;
	
	public Player(GraphicsPanel graphics){
		direction = 'S';
		initializePosition();
		this.graphics = graphics;
	}
	public void run(){
		//infinite loop
		while (Thread.currentThread() == playertheard){
			//player moves
			move();
			//then sleeps
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void move() {
		setDirection(chooseDirection());
		Location nextLocation = null;
		
		switch (getDirection()){
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
		//deal with graphics?
				
	}
	
	public char getDirection() {
		return this.direction;
	}
	public abstract char chooseDirection();
	public abstract void initializePosition();
	
	public void setLocation(Location location){
		this.location = location;
	}
	
	public Location getLocation() {
		return this.location = location;
	}

	public void start() {
		playertheard = new Thread();
		playertheard.start();
	}

	public void stopPlaying() {
		playertheard = null;	
	}

	public int getPoints() {
		return this.points;
	}
	
	public void addPoints(){
		this.points +=1;
	}

	public void setDirection(char c) {
		c = direction;
	}

}
