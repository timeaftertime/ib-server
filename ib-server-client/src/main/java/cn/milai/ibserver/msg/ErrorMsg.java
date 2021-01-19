package cn.milai.ibserver.msg;

/**
 * （鉴权失败、参数错误、异常）时服务端返回错误的消息
 * @author milai
 * @date 2020.12.29
 */
public class ErrorMsg {

	private String desc;

	public ErrorMsg(String desc) {
		this.desc = desc;
	}

	/**
	 * 获取发生错误的原因
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

}
