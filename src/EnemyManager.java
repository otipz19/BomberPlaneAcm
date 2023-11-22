import acm.util.RandomGenerator;

public class EnemyManager {
	private static final double MIN_SPEED = 10;
	private static final double MAX_SPEED = 20;
	private static final double ENEMY_WIDTH = 100;
	private static final double STATIC_ENEMY_HEIGHT = 100;
	private static final double DYNAMIC_ENEMY_HEIGHT = 50;
	
	private static final int ENEMIES_MAX = 5;
	private static RandomGenerator rng = RandomGenerator.getInstance();
	
	private StaticEnemy s0;
	private DynamicEnemy d1;
	private DynamicEnemy d2;
	private DynamicEnemy d3;
	private DynamicEnemy d4;
	
	private int enemiesCount = ENEMIES_MAX;
	
	public EnemyManager(Game game){
		double dynamicY = Game.SCENE_BOTTOM - DYNAMIC_ENEMY_HEIGHT;
		double staticY = Game.SCENE_BOTTOM - STATIC_ENEMY_HEIGHT;
		double border = game.SCENE_HORIZONTAL_BORDER;
		s0 = new StaticEnemy(getRandomX(0, border), staticY, ENEMY_WIDTH, STATIC_ENEMY_HEIGHT);
		d1 = new DynamicEnemy(getRandomX(0,  border / 4), dynamicY, ENEMY_WIDTH, DYNAMIC_ENEMY_HEIGHT, getRandomSpeed(), getRandomDirection());
		d2 = new DynamicEnemy(getRandomX(border / 4, border / 2), dynamicY, ENEMY_WIDTH, DYNAMIC_ENEMY_HEIGHT, getRandomSpeed(), getRandomDirection());
		d3 = new DynamicEnemy(getRandomX(border / 2, border * 0.75), dynamicY, ENEMY_WIDTH, DYNAMIC_ENEMY_HEIGHT, getRandomSpeed(), getRandomDirection());
		d4 = new DynamicEnemy(getRandomX(border * 0.75, border), dynamicY, ENEMY_WIDTH, DYNAMIC_ENEMY_HEIGHT, getRandomSpeed(), getRandomDirection());
		for(int i = 0; i < enemiesCount; i++){
			game.add(getEnemy(i));
		}
	}
	
	public boolean anyEnemiesPresent(){
		return enemiesCount > 0;
	}
	
	public void act(){
		for(int i = 0; i < ENEMIES_MAX; i++){
			if(getEnemy(i).isActing()){
				getEnemy(i).act();
			}
		}
	}
	
	public void checkPlaneCollisions(Plane plane){
		for(int i = 0; i < ENEMIES_MAX; i++){
			if(plane.getCollider().checkCollision(getEnemy(i).getCollider())){
				plane.onCollision();
			}
		}
	}
	
	public void checkBombCollisions(Bomb bomb){
		if(bomb == null || bomb.isDying()){
			return;
		}
		
		for(int i = 0; i < ENEMIES_MAX; i++){
			if(getEnemy(i).isAlive() && bomb.getCollider().checkCollision(getEnemy(i).getCollider())){
				bomb.onCollision();
				getEnemy(i).onCollision();
				enemiesCount--;
			}
		}
	}
	
	private double getRandomX(double from, double to){
		return rng.nextDouble(from, to - ENEMY_WIDTH);
	}
	
	private double getRandomSpeed(){
		return rng.nextDouble(MIN_SPEED, MAX_SPEED);
	}
	
	private Direction getRandomDirection(){
		return rng.nextBoolean() ? Direction.LEFT : Direction.RIGHT;
	}
	
	private Enemy getEnemy(int i){
		switch(i){
			case 0:
				return s0;
			case 1:
				return d1;
			case 2:
				return d2;
			case 3:
				return d3;
			case 4:
				return d4;
			default:
				return null;
		}
	}
}
