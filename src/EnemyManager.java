import acm.util.RandomGenerator;


public class EnemyManager {
	private static final int ENEMIES_MAX = 5;
	private static RandomGenerator rng = RandomGenerator.getInstance();
	private Game game;
	
	private StaticEnemy s0;
	private DynamicEnemy d1;
	private DynamicEnemy d2;
	private DynamicEnemy d3;
	private DynamicEnemy d4;
	
	private int enemiesCount = ENEMIES_MAX;
	
	public EnemyManager(){
		s0 = new StaticEnemy(getRandomX(100), Game.SCENE_BOTTOM - 100, 100, 100);
		d1 = new DynamicEnemy(getRandomX(100), Game.SCENE_BOTTOM - 50, 100, 50, 10, getRandomDirection());
		d2 = new DynamicEnemy(getRandomX(100), Game.SCENE_BOTTOM - 50, 100, 50, 10, getRandomDirection());
		d3 = new DynamicEnemy(getRandomX(100), Game.SCENE_BOTTOM - 50, 100, 50, 10, getRandomDirection());
		d4 = new DynamicEnemy(getRandomX(100), Game.SCENE_BOTTOM - 50, 100, 50, 10, getRandomDirection());
		for(int i = 0; i < enemiesCount; i++){
			game.add(getEnemy(i));
		}
	}
	
	private double getRandomX(double width){
		return rng.nextDouble(0, Game.SCENE_HORIZONTAL_BORDER - width);
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
