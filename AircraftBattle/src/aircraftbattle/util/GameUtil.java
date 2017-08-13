package aircraftbattle.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import aircraftbattle.game.GameParameter;
import aircraftbattle.game.GameParameter.BulletType;
import aircraftbattle.game.GameParameter.MagicType;

public class GameUtil {

	public static JButton getIconSizeJButton(String ImagePath, int X, int Y) {
		JButton button = new JButton();
		ImageIcon icon = new ImageIcon(ImagePath);
		button.setIcon(icon);
		button.setSize(icon.getIconWidth(), icon.getIconHeight());
		button.setLocation(X, Y);
		return button;
	}

	public static JLabel getIconSizeJLabel(String ImagePath, int X, int Y) {
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon(ImagePath);
		label.setIcon(icon);
		label.setSize(icon.getIconWidth(), icon.getIconHeight());
		label.setLocation(X, Y);
		return label;
	}

	public static JLabel getNumJLabel(int X, int Y) {
		JLabel numlabel = new JLabel();
		numlabel.setSize(GameParameter.NUMLABEL_WIDTH, GameParameter.NUMLABEL_HIGHT);
		numlabel.setLocation(X, Y);
		numlabel.setFont(new Font(GameParameter.NUMLABEL_FONT, Font.BOLD, GameParameter.NUMLABEL_FONT_SIZE));
		return numlabel;
	}

	public static JPanel getBackgroundJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.setSize(GameParameter.FRAME_WIDTH, GameParameter.FRAME_HIGHT);

		return panel;

	}

	public static String getTypedPlayerPath(boolean isWisdom) {
		StringBuffer path = new StringBuffer("img/Player");
		if (isWisdom)
			path.append("2.png");
		else
			path.append("1.png");

		return path.toString();
	}

	public static String getTypedBulletPath(BulletType type) {
		StringBuffer path = new StringBuffer("img/Bullet");
		if (type == BulletType.PLAYER)
			path.append("1.png");
		else
			path.append("2.png");

		return path.toString();
	}

	public static String getTypedMagicPath(MagicType type) {
		StringBuffer path = new StringBuffer("img/Magic");
		if (type == MagicType.HEALTH)
			path.append("Health.png");
		else
			path.append("Bullet.png");

		return path.toString();
	}
	
	public static String getLevelEnemyPath(int level) {
		level++;
		return new String("img/Enemy" + level + ".png");
	}

	public static int getRandomNum(int range) {
		int ret = (int) (Math.random() * range);
		if (ret >= range - GameParameter.AIRCRAFTE_IMAGE_LENGTH)
			ret -= GameParameter.AIRCRAFTE_IMAGE_LENGTH;// 对超出屏幕范围的飞机做一个修正
		return ret;
	}

	public static int getRandomNum(int low, int high) {
		return (int) (Math.random() * (high - low)) + low;
	}

	public static boolean isCrossing(Rectangle r1,Rectangle r2){
		int deltaX = (int)Math.abs(r1.getX()-r2.getX());
		
		if((r2.getX()<r1.getX()&& deltaX<=r2.getWidth())||(r1.getX()<r2.getX()&&deltaX<=r1.getWidth()) ){//如果横向坐标之差小于横坐标小的矩形的宽	
			int deltaY = (int)Math.abs(r1.getY()-r2.getY());
			if((r2.getY()<r1.getY()&&deltaY<=r2.getHeight())||(r1.getY()<r2.getY()&&deltaY<=r1.getHeight()))//如果纵向坐标之差小于横坐标小的矩形的高
				return true;	
		}
		return false;	
	}

	public static boolean isMagicFall(){
		if(Math.random()*10>=9)
			return true;
		return false;
	}

	public static MagicType getRandomMagicType() {
		if(Math.random()*10>=5)
			return MagicType.BULLET;
		return MagicType.HEALTH;
	}
}
