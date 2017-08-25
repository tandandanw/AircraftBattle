package aircraftbattle.stuff;

import java.io.IOException;

import aircraftbattle.game.GameParameter;
import aircraftbattle.game.GameParameter.BulletType;
import aircraftbattle.util.GameUtil;


public class Boss extends Enemy {

	public Boss(String path) throws IOException {
		super(path, 4);
		this.setHealth(10000);
		speedX = GameParameter.BOSS_SPEED_X;
		speedY = GameParameter.BOSS_SPEED_Y;
	}

	
	public void move() {
		if(x > GameParameter.FRAME_WIDTH || x < 0) speedX= (-speedX);//·´µ¯
		if(y > GameParameter.FRAME_HIGHT/2 || y < 0) speedY= (-speedY);//·´µ¯
		y += speedY;
		x += speedX;
	}


	public Bullet directionalAttack(int targetX, int targetY) throws IOException {
		String path = GameUtil.getTypedBulletPath(BulletType.ENEMY);
		Bullet bullet = new Bullet(
				x + image.getWidth(null) / 2 - GameParameter.BULLET_IMAGE_WIDTH / 2, 
				y + image.getHeight(null)/2, path, BulletType.ENEMY, targetX, targetY);

		return bullet;
	}


	
	
}
