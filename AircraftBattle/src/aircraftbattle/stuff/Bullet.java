package aircraftbattle.stuff;

import java.io.IOException;

import aircraftbattle.game.GameParameter;
import aircraftbattle.game.GameParameter.BulletType;

public class Bullet extends GameComponent {

	private boolean isAlive;
	private BulletType type;

	// 如果是敌人子弹，有定向点
	private int targetX;
	private int targetY;

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getTargetY() {
		return targetY;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}

	public int getTargetX() {
		return targetX;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public Bullet(int x, int y, String path, BulletType type) throws IOException {
		super(x, y, GameParameter.BULLET_SPEED_X, GameParameter.BULLET_SPEED_Y, path);
		isAlive = true;
		this.type = type;
	}

	public Bullet(int x, int y, String path, BulletType type, int targetX, int targetY) throws IOException {
		super(x, y, GameParameter.BULLET_SPEED_X, GameParameter.BULLET_SPEED_Y, path);
		isAlive = true;
		this.type = type;
		this.targetX = targetX;
		this.targetY = targetY;

		// 计算相对速度
		int t = (targetY - y) / speedY;
		if (t != 0)
			speedX = Math.abs((targetX - x) / t);
	}

	@Override
	public void move() {
		if (y < 0 || y > GameParameter.FRAME_HIGHT) {
			isAlive = false;
			return;
		}

		if (type == BulletType.PLAYER)
			y -= speedY;
		else
			directionalFly();
	}

	public void directionalFly() {
		if (x < targetX)
			x += speedX;
		if (x > targetX)
			x -= speedX;
		if (y > targetY)
			y -= speedY;
		if (y < targetY)
			y += speedY;

		if ((x > targetX - speedX && x < targetX + speedX) || (y > targetY - speedY && y < targetY + speedY))// 在目标附近
			isAlive = false;
	}
}
