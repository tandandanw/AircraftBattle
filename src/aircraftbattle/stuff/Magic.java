package aircraftbattle.stuff;

import java.io.IOException;

import aircraftbattle.game.GameParameter;
import aircraftbattle.game.GameParameter.MagicType;
import aircraftbattle.util.GameUtil;

public class Magic extends GameComponent {

	private boolean isAlive;
	private MagicType type; 
	
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public MagicType getType() {
		return type;
	}
	public void setType(MagicType type) {
		this.type = type;
	}
	
	public Magic(int x, int y, MagicType type) throws IOException {
		super(x, y, GameParameter.MAGIC_SPEED_X, GameParameter.MAGIC_SPEED_Y,GameUtil.getTypedMagicPath(type));
		this.type = type;
		this.isAlive = true;
		this.type = type;
	}

	@Override
	public void move() {
		if(y>=GameParameter.FRAME_HIGHT){
			isAlive =false;
			return;
		}
		y+=speedY;
	}

}
