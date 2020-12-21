package cn.milai.ib.server.web.dao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

	private int id;
	private String username;
	private String password;
	private String salt;
	
}
