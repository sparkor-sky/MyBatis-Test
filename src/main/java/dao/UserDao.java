package dao;

import entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from user where id = #{id}")
    User queryById(Integer id);
}
