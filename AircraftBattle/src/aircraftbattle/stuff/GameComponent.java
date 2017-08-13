package aircraftbattle.stuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class GameComponent {

	protected int x;// 横坐标
	protected int y;// 纵坐标
	protected int speedX;// 横向速度
	protected int speedY;// 纵向速度
	protected Image image;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(String path) throws IOException {
		this.image = ImageIO.read(GameComponent.class.getClassLoader().getResourceAsStream(path));	}

	public GameComponent(int x, int y, int speedX, int speedY, String path) throws IOException {
		super();
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.image = ImageIO.read(GameComponent.class.getClassLoader().getResourceAsStream(path));
		//只用命令行运行，则删去AircraftBattle目录下的img文件夹
		
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	public Rectangle getRectangle() {
		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
	}
	
	public abstract void move();
	
	
}
