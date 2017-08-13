package aircraftbattle.game;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import aircraftbattle.game.GameParameter.MagicType;
import aircraftbattle.stuff.Boss;
import aircraftbattle.stuff.Bullet;
import aircraftbattle.stuff.Enemy;
import aircraftbattle.stuff.Explosion;
import aircraftbattle.stuff.Magic;
import aircraftbattle.stuff.Music;
import aircraftbattle.stuff.Player;
import aircraftbattle.util.GameUtil;

public class GameService {

	Player player;// 玩家
	ArrayList<Bullet> playerBullets;// 玩家子弹

	ArrayList<Enemy> enemies;// 敌人列表
	ArrayList<Bullet> enemiesBullets;// 敌人子弹列表

	ArrayList<Magic> magics;// 掉落物品列表
	ArrayList<Explosion> explosions;// 爆炸效果列表

	public Player getPlayer() {
		return player;
	}

	public GameService() {
		super();
		GameParameter.toTalScore = GameParameter.START_SCORE;// 开始计分
		playerBullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		enemiesBullets = new ArrayList<Bullet>();
		magics = new ArrayList<Magic>();
		explosions = new ArrayList<Explosion>();

		// 背景音乐
		new Music("background.mp3", true).start();
	}

	void paint(Graphics g) throws IOException {
		// 画玩家
		if (player != null)
			player.draw(g);
		// 画玩家子弹
		if (!playerBullets.isEmpty()) {
			for (int i = 0; i < playerBullets.size(); i++) {
				if (playerBullets.get(i).isAlive())
					playerBullets.get(i).draw(g);
			}
		}
		// 画敌人
		if (!enemies.isEmpty()) {
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).isAlive())
					enemies.get(i).draw(g);
			}
		}
		// 画敌人子弹
		if (!enemiesBullets.isEmpty()) {
			for (int i = 0; i < enemiesBullets.size(); i++) {
				if (enemiesBullets.get(i).isAlive())
					enemiesBullets.get(i).draw(g);
			}
		}
		// 画魔法物品
		if (!magics.isEmpty()) {
			for (int i = 0; i < magics.size(); i++) {
				if (magics.get(i).isAlive())
					magics.get(i).draw(g);
			}
		}
		// 画爆炸效果
		if (!explosions.isEmpty()) {
			for (int i = 0; i < explosions.size(); i++) {
				if (explosions.get(i).isAlive())
					explosions.get(i).draw(g);
			}
		}
	}

	void clear() {
		playerBullets.clear();
		enemies.clear();
		enemiesBullets.clear();
		magics.clear();
	}

	void generatePlayer() throws IOException {
		player = new Player(GameUtil.getTypedPlayerPath(GameParameter.isWisdom), GameParameter.currentLevel);
	}

	void playerMove() {
		if (player.isAlive())
			player.move();
	}

	void generateEnemy() throws IOException {
		// 最后一关只有一个BOSS
		if (GameParameter.currentLevel == 4) {
			if (enemies.isEmpty())
				enemies.add(new Boss("img\\Boss.png"));
			return;
		}
		// 其他关卡
		if (enemies.isEmpty() || enemies.get(enemies.size() - 1).getY() > 200)// 上一个敌人移动超过200
			enemies.add(new Enemy(GameUtil.getLevelEnemyPath(GameParameter.currentLevel), GameParameter.currentLevel));
	}

	void othersMove() {
		//敌人移动
		if (!enemies.isEmpty()) {
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).isAlive())
					enemies.get(i).move();
			}
		}
		//敌人子弹移动
		if (!enemiesBullets.isEmpty()) {
			for (int i = 0; i < enemiesBullets.size(); i++) {
				if (enemiesBullets.get(i).isAlive())
					enemiesBullets.get(i).move();
			}
		}
		//魔法物品移动
		if (!magics.isEmpty()) {
			for (int i = 0; i < magics.size(); i++) {
				if (magics.get(i).isAlive())
					magics.get(i).move();
			}
		}
		
	}

	void playerBulletsGenerate() throws IOException {
		if (GameParameter.isSpace && player.getBulletsNum() != 0) {
			GameParameter.isSpace = false;// 不加这句会连续发出子弹

			Bullet playerBullet = player.attack();
			playerBullets.add(playerBullet);
			// 子弹发射音效
			new Music("fire.mp3", false).start();
		}
	}

	void playerBulletsMove() {
		if (!playerBullets.isEmpty()) {
			for (int i = 0; i < playerBullets.size(); i++) {
				if (playerBullets.get(i).isAlive())
					playerBullets.get(i).move();
			}
		}
	}

	void enemyBulletsGenerate() throws IOException {
		for (int i = 0; i < enemies.size(); i++) {
			boolean flag = false;// 能否发射子弹

			if (enemies.get(i).getBulletsNum() != 0) {// 还有子弹
				if (GameParameter.currentLevel == 4)// BOSS无限发射子弹
					flag = true;
				else if (enemies.get(i).getCurrentBullet() == null)// 第一颗子弹
					flag = true;
				else { // 不是第一颗子弹
					if (enemies.get(i).getCurrentBullet().getY() > GameUtil.getRandomNum(500, 600))// 前一颗子弹Y过了一个随机数
						flag = true;
				}
			}
			// 可以发射子弹
			if (flag) {
				Bullet enemyBullet = enemies.get(i).directionalAttack(player.getX(), player.getY());
				enemies.get(i).setCurrentBullet(enemyBullet);
				enemiesBullets.add(enemyBullet);
			}
		}

	}

	void collisionDetecte() throws IOException {
		// 敌人碰撞
		if (!enemies.isEmpty()) {
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).isAlive()) {
					// 敌人与玩家碰撞
					if (GameUtil.isCrossing(enemies.get(i).getRectangle(), player.getRectangle())) {
						player.hurt(GameParameter.AIRCRAFT_HURT_POINT);// 玩家受到伤害
						enemies.get(i).hurt(GameParameter.AIRCRAFT_HURT_POINT);// 敌人受到伤害
					}
					// 敌人与玩家子弹碰撞
					if (!playerBullets.isEmpty()) {
						for (int j = 0; j < playerBullets.size(); j++) {
							if (GameUtil.isCrossing(playerBullets.get(j).getRectangle(),
									enemies.get(i).getRectangle())) {
								playerBullets.get(j).setAlive(false);// 玩家子弹死亡
								enemies.get(i).hurt(GameParameter.BULLET_HURT_POINT);// 敌人受到伤害
							}
						}
					}
					// 判断敌人是否死亡
					if (enemies.get(i).getHealth() <= 0) {
						enemies.get(i).setAlive(false);// 敌人死亡
						GameParameter.toTalScore += GameParameter.ENEMY_HURT_SCORE;// 加分
						// 是否掉落魔法物品
						if (GameUtil.isMagicFall()) {
							Magic magic = new Magic(enemies.get(i).getX(), enemies.get(i).getX(),
									GameUtil.getRandomMagicType());
							magics.add(magic);
						}
						// 播放爆炸效果
						Explosion explosion = new Explosion(enemies.get(i).getX(), enemies.get(i).getY());
						explosions.add(explosion);
					}
				}
			}
		}
		// 敌人子弹碰撞
		if (!enemiesBullets.isEmpty()) {
			for (int i = 0; i < enemiesBullets.size(); i++) {
				if (enemiesBullets.get(i).isAlive()) {
					// 敌人子弹与玩家碰撞
					if (GameUtil.isCrossing(enemiesBullets.get(i).getRectangle(), player.getRectangle())) {
						player.hurt(GameParameter.BULLET_HURT_POINT);// 玩家受到伤害
						enemiesBullets.get(i).setAlive(false);// 敌人子弹死亡
					}
					// 敌人子弹与玩家子弹碰撞
					if (!playerBullets.isEmpty()&&enemiesBullets.get(i).isAlive()) {
						for (int j = 0; j < playerBullets.size(); j++) {
							if (GameUtil.isCrossing(playerBullets.get(j).getRectangle(),
									enemiesBullets.get(i).getRectangle())) {
								GameParameter.toTalScore += GameParameter.BULLET_HURT_SCORE;//加分
								playerBullets.get(j).setAlive(false);// 玩家子弹死亡
								enemiesBullets.get(i).setAlive(false);// 敌人子弹死亡
							}
						}
					}
				}
			}
		}
		// 魔法物品碰撞
		if (!magics.isEmpty()) {
			for (int i = 0; i < magics.size(); i++) {
				if (magics.get(i).isAlive()) {
					if (GameUtil.isCrossing(magics.get(i).getRectangle(), player.getRectangle())) {
						if (magics.get(i).getType() == MagicType.HEALTH)
							player.hurt(-GameParameter.MAGIC_HEALTH_POINT);//加生命值
						else
							player.bulletAdd(GameParameter.MAGIC_BULLET_POINT);//加子弹数目
						magics.get(i).setAlive(false);
					}
				}
			}
		}
	}

	void deathRemove() {
		//玩家死亡设置
		if(player.getHealth()<=0) player.setAlive(false);
		
		// 死亡玩家子弹移除
		if (!playerBullets.isEmpty()) {
			for (int i = 0; i < playerBullets.size(); i++) {
				if (!playerBullets.get(i).isAlive())
					playerBullets.remove(i);
			}
		}
		// 死亡敌人移除
		if (!enemies.isEmpty()) {
			for (int i = 0; i < enemies.size(); i++) {
				if (!enemies.get(i).isAlive())
					enemies.remove(i);
			}
		}
		// 死亡敌人子弹移除
		if (!enemiesBullets.isEmpty()) {
			for (int i = 0; i < enemiesBullets.size(); i++) {
				if (!enemiesBullets.get(i).isAlive())
					enemiesBullets.remove(i);
			}
		}
		// 死亡魔法物品移除
		if (!magics.isEmpty()) {
			for (int i = 0; i < magics.size(); i++) {
				if (!magics.get(i).isAlive())
					magics.remove(i);
			}
		}	
	}

	boolean gameEndDetecte() {
		if (player.isAlive() == false || player.getHealth() <= 0) {
			GameParameter.suspendFlag = false;
			return true;
		}
		return false;
	}

	boolean nextLevelDetecte() {
		if (GameParameter.toTalScore >= GameParameter.TARGET_SCORE[GameParameter.currentLevel])
			return true;
		return false;
	}
}
