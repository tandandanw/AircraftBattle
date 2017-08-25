package aircraftbattle.stuff;

import java.io.IOException;

import aircraftbattle.game.GameFrame;
import aircraftbattle.game.GameParameter;

public class PlayerThread extends Thread {
	
	private GameFrame gameFrame;
	
	public PlayerThread(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void run() {
		while (GameParameter.suspendFlag) {
			try {
				//玩家行为自己在一个线程
				gameFrame.getService().playerBulletsGenerate();
				gameFrame.getService().playerMove();
				gameFrame.getService().playerBulletsMove();
							
				// 重绘
				gameFrame.repaint();

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
