package aircraftbattle.stuff;

import java.io.IOException;

import aircraftbattle.game.GameFrame;
import aircraftbattle.game.GameParameter;

public class EnemyThread extends Thread {
	
	private GameFrame gameFrame;
	
	public  EnemyThread(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void run() {
		while (GameParameter.suspendFlag) {
			try {
				//自动化生成敌人以及敌人行为
				gameFrame.getService().generateEnemy();
				gameFrame.getService().enemyBulletsGenerate();

				// 暂停
				sleep(10);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
