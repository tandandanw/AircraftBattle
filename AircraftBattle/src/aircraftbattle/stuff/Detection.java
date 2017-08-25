package aircraftbattle.stuff;

import aircraftbattle.game.GameFrame;
import aircraftbattle.game.GameParameter;

public class Detection extends Thread {

	private GameFrame gameFrame;
	
	public Detection(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void run() {
		while (GameParameter.suspendFlag) {
			try {
				gameFrame.getService().collisionDetecte();// 所有物体碰撞检测
				gameFrame.getService().deathRemove(); //移除死亡物体(移除和移动必须在一个线程里)
				
				gameFrame.getService().othersMove();//移动
			
				if (gameFrame.getService().gameEndDetecte())
					gameFrame.CardChange("end");
				if(gameFrame.getService().nextLevelDetecte())
					gameFrame.CardChange("go");
					
				//暂停
				sleep(10);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
