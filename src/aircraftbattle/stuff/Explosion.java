package aircraftbattle.stuff;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import aircraftbattle.game.GameParameter;

public class Explosion {

	private int x;
	private int y;
	private int index;
	private boolean isAlive;
	private String[] images = { "Blast1.png", "Blast2.png", "Blast3.png", "Blast4.png", "Blast5.png", "Blast6.png"};

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Explosion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.index = 0;
		this.isAlive = true;
		
		new Music("runaway.mp3", false).start();//ÒôÐ§
	}

	public void draw(Graphics g) throws IOException {
		Image img = ImageIO.read(new File("img\\" + images[index]));
	
		g.drawImage(img, x + GameParameter.AIRCRAFTE_IMAGE_LENGTH / 2, y + GameParameter.AIRCRAFTE_IMAGE_LENGTH / 2,
			GameParameter.AIRCRAFTE_IMAGE_LENGTH, GameParameter.AIRCRAFTE_IMAGE_LENGTH, null);
			
		index++;
		
		if (index >= images.length) {
			setAlive(false);
		}
	
	}

}
