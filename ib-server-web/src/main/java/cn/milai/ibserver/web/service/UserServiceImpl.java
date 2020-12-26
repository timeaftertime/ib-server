package cn.milai.ibserver.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.milai.common.api.Resp;
import cn.milai.ibserver.web.IBServerResp;
import cn.milai.ibserver.web.controller.vo.UserVO;
import cn.milai.ibserver.web.dao.UserDAO;
import cn.milai.ibserver.web.dao.po.UserPO;
import cn.milai.ibserver.web.service.UserService;
import cn.milai.ibserver.web.util.MD5Util;
import cn.milai.ibserver.web.util.RandomUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	/**
	 * 若登录成功将设置 user 的 id
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Resp<Void> login(UserVO user) {
		UserPO po = userDAO.selectUserByUsername(user.getUsername());
		if (po == null) {
			return Resp.fail(IBServerResp.USERNAME_NOT_EXISTS);
		}
		if (!MD5Util.md5(user.getPassword() + po.getSalt()).equals(po.getPassword())) {
			return Resp.fail(IBServerResp.PASSWORD_ERROR);
		}
		user.setId(po.getId());
		return Resp.success();
	}

	@Override
	public Resp<Void> register(UserVO user) {
		UserPO po = userDAO.selectUserByUsername(user.getUsername());
		if (po != null) {
			return Resp.fail(IBServerResp.USERNAME_ALREADY_EXISTS);
		}
		po = createUserPO(user);
		userDAO.insertUser(po);
		return Resp.success();
	}

	private UserPO createUserPO(UserVO user) {
		UserPO po = new UserPO();
		po.setUsername(user.getUsername());
		String salt = RandomUtil.randString();
		po.setSalt(salt);
		po.setPassword(MD5Util.md5(user.getPassword() + salt));
		return po;
	}

}
