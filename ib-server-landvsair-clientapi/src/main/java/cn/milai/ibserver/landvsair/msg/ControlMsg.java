package cn.milai.ibserver.landvsair.msg;

/**
 * 控制指令
 * @author milai
 * @date 2021.05.02
 */
public class ControlMsg {

	private int type;

	private int x = 0;

	private int y = 0;

	public int getType() { return type; }

	public void setType(int type) { this.type = type; }

	public int getX() { return x; }

	public void setX(int x) { this.x = x; }

	public int getY() { return y; }

	public void setY(int y) { this.y = y; }

	@Override
	public String toString() {
		return "ControlMsg [type=" + type + ", x=" + x + ", y=" + y + "]";
	}

}
