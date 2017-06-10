package kz.alisher.greetgo.mappers;

import kz.alisher.greetgo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by alisher
 */
@Mapper
public interface UserMapper {
    @Select("select * from USERS where ID = #{id}")
    User findById(@Param("id") long id);

    @Insert("INSERT INTO USERS (name) VALUES (#{name})")
    void insert(User user);

    @Select("select * from users")
    List<User> findAllUsers();

    @Update("update users set name=#{name} where id=#{id}")
    void update(User user);
}