package cn.milai.ibserver.landvsair.cmd;

import cn.milai.ib.container.plugin.control.cmd.Cmd;

/**
 * {@link Cmd} 类型
 * @author milai
 * @date 2021.05.12
 */
public enum CmdType {

	NOOP(Cmd.NOOP),
	PAUSE(Cmd.PAUSE),
	MOVE(Cmd.MOVE),
	CLICK(Cmd.CLICK),

	// 设置 上 指令
	UP(1),
	// 取消 上 指令
	U_UP(2),
	// 设置 下 指令
	DOWN(3),
	// 取消 下 指令
	U_DOWN(4),
	// 设置 左 指令
	LEFT(5),
	// 取消 左 指令
	U_LEFT(6),
	// 设置 右 指令
	RIGHT(7),
	// 取消 右 指令
	U_RIGHT(8),
	// 设置 A 动作指令
	A(9),
	// 取消 A 动作指令
	U_A(10),
	// 设置 B 动作指令
	B(11),
	// 取消 B 动作指令
	U_B(12),
	// 设置 C 动作指令
	C(13),
	// 取消 C 动作指令
	U_C(14),
	// 设置 D 动作指令
	D(15),
	// 取消 D 动作指令
	U_D(16);

	private int value;

	private static final CmdType[] KEY_CMDS = { UP, U_UP, DOWN, U_DOWN, LEFT, U_LEFT, RIGHT, U_RIGHT, A, U_A, B, U_B, C,
		U_C, D, U_D };

	private static final CmdType[] MOUSE_CMDS = { CLICK, MOVE };

	CmdType(int value) {
		this.value = value;
	}

	public int getValue() { return value; }

	/**
	 * 获取 {@code value} 对应的 {@link CmdType} ，若不存在，抛出异常
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static CmdType of(int value) {
		CmdType find = find(value);
		if (find == null) {
			throw new IllegalArgumentException("未知 value:" + value);
		}
		return find;
	}

	/**
	 * 获取 {@code value} 对应的 {@link CmdType} ，若不存在，返回 {@code null}
	 * @param value
	 * @return
	 */
	public static CmdType find(int value) {
		for (CmdType type : values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 判断指定 {@link CmdType} 是否为按键类型
	 * @param type
	 * @return
	 */
	public static boolean isKeyCmd(CmdType type) {
		for (CmdType t : KEY_CMDS) {
			if (t == type) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断给定类型是否为鼠标类型
	 * @param type
	 * @return
	 */
	public static boolean isMouseCmd(CmdType type) {
		for (CmdType t : MOUSE_CMDS) {
			if (t == type) {
				return true;
			}
		}
		return false;
	}

}
