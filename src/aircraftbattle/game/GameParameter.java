package aircraftbattle.game;


public class GameParameter {

	// 游戏进行时参数————————————————————————————————————————————————————————————————————————————
	public static boolean isW, isA, isS, isD, isSpace;// 各键是否按下
	public static boolean isWisdom;// 是否是智慧型战机
	public static int toTalScore;// 本次游戏总分
	public static int currentLevel;// 当前关卡
	public static boolean suspendFlag;//游戏线程暂停标志
	

	// 游戏固定参数—————————————————————————————————————————————————————————————————————————————————
	public static final int[] TARGET_SCORE = { 1000, 2000, 3000, 4000, 5000 };// 目标分数
	// 玩家参数
	public static final int PLAYER_SPPEED_X = 4;// 玩家横向速度
	public static final int PLAYER_SPPEED_Y = 5;// 玩家纵向速度
	public static final int PLAYER_SPAWN_X = 360;// 玩家发生点横坐标
	public static final int PLAYER_SPAWN_Y = 820;// 玩家发生点纵坐标
	public static final int START_SCORE = 0;// 起始分数
	public static final int START_LEVEL = 0;// 起始关卡
	public static final int START_HEALTH = 100;// 起始生命值
	public static final int[] START_BULLETS = { 100, 200, 300, 400, 500 };// 起始子弹
	// 敌人参数
	public static final int[] ENEMY_BULLETS = { 2, 5, 10, 15, 10000 };// 敌人子弹
	public static final int ENEMY_HEALTH = 3;// 敌人生命值
	public static final int ENEMY_SPEED_X = 0;// 敌人横向速度
	public static final int BOSS_SPEED_X = 1;// BOSS横向速度
	public static final int BOSS_SPEED_Y = 1;// BOSS纵向速度
	public static final int[] ENEMY_SPEED_Y = { 2, 2, 3, 3, 1 };// 敌人纵向速度

	// 子弹参数
	public static final int BULLET_SPEED_X = 0;// 子弹横向速度
	public static final int BULLET_SPEED_Y = 6;// 子弹纵向速度

	public enum BulletType {
		PLAYER, ENEMY
	}
	
	//魔法物品参数
	public static final int MAGIC_SPEED_X = 0;//魔法物品横向移动速度
	public static final int MAGIC_SPEED_Y = 5;//魔法物品纵向移动速度
	public static final int MAGIC_HEALTH_POINT = 5;//加生命
	public static final int MAGIC_BULLET_POINT = 10;//加子弹数目
	public enum MagicType {
		HEALTH,BULLET
	}

	// 碰撞伤害参数
	public static final int BULLET_HURT_POINT = 3;// 子弹和飞机碰撞伤害点数
	public static final int AIRCRAFT_HURT_POINT = 5;// 飞机和飞机碰撞伤害点数
	//子弹和子弹碰撞直接双方子弹死亡
	
	//得分参数
	public static final int BULLET_HURT_SCORE = 5;//敌人子弹死亡
	public static final int ENEMY_HURT_SCORE = 20;//敌人死亡

	// 界面参数————————————————————————————————————————————————————————————————————————————————
	public static final int FRAME_WIDTH = 800;// 界面宽度
	public static final int FRAME_HIGHT = 1000;// 界面高度

	public static final int AIRCRAFTE_IMAGE_LENGTH = 80;// 飞机图片边长 80
	public static final int BULLET_IMAGE_WIDTH = 25;// 子弹图片宽
	public static final int BULLET_IMAGE_HEIGHT = 15;// 子弹图片高
	public static final int MAGIC_IMAGE_HEIGHT = 30;// 魔法物品图片边长 

	public static final int NUMLABEL_WIDTH = 120;// 计数标签宽度
	public static final int NUMLABEL_HIGHT = 50;// 计数标签高度
	public static final String NUMLABEL_FONT = "Broadway";// 计数标签字体
	public static final int NUMLABEL_FONT_SIZE = 40;// 计数标签字体大小

}
