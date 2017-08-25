package aircraftbattle.stuff;

import java.io.IOException;

import aircraftbattle.game.GameParameter;
import aircraftbattle.game.GameParameter.BulletType;
import aircraftbattle.util.GameUtil;

public class Enemy extends GameComponent {

	private int health;
	private int bulletsNum;
	protected boolean isAlive;
	
	private Bullet currentBullet;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBulletsNum() {
		return bulletsNum;
	}

	public void setBulletsNum(int bulletsNum) {
		this.bulletsNum = bulletsNum;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Bullet getCurrentBullet() {
		return currentBullet;
	}

	public void setCurrentBullet(Bullet currentBullet) {
		this.currentBullet = currentBullet;
	}

	
	public Enemy(String path, int level) throws IOException {
		super(GameUtil.getRandomNum(GameParameter.FRAME_WIDTH), 0, GameParameter.ENEMY_SPEED_X,
				GameParameter.ENEMY_SPEED_Y[level], path);
		this.setAlive(true);
		this.bulletsNum = GameParameter.ENEMY_BULLETS[level];
		this.health = GameParameter.ENEMY_HEALTH;
		this.currentBullet=null;
	}

	@Override
	public void move() {
		if (y < 0 || y > GameParameter.FRAME_HIGHT)
			isAlive = false;
		else {
			y += speedY;
		}
	}

	public Bullet directionalAttack(int targetX, int targetY) throws IOException {
		if (bulletsNum != 0)
			bulletsNum--;// 子弹数量减少

		String path = GameUtil.getTypedBulletPath(BulletType.ENEMY);
		Bullet bullet = new Bullet(
				x + GameParameter.AIRCRAFTE_IMAGE_LENGTH / 2 - GameParameter.BULLET_IMAGE_WIDTH / 2 + 4, // 4是个修正值
				y + GameParameter.AIRCRAFTE_IMAGE_LENGTH, path, BulletType.ENEMY, targetX, targetY);

		return bullet;
	}

	public void hurt(int hurtPoint){
		health -= hurtPoint;
	}
	

}
