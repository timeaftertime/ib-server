package cn.milai.ib.server.web.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.milai.ib.server.web.dao.po.UserPO;

@Mapper
public interface UserDAO {

	String E_USER = " e_user ";
	
	@Select({"select * from", E_USER, "where username = #{username}"})
	UserPO selectUserByUsername(@Param("username") String username);
	
	@Insert({"insert into", E_USER, "(username, password, salt) values(#{user.username}, #{user.password}, #{user.salt})"})
	Integer insertUser(@Param("user") UserPO user);

}
