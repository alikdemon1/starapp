package kz.alisher.greetgo.mappers;

import kz.alisher.greetgo.domain.Star;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by alisher
 */
@Mapper
public interface StarMapper {
    @Select("select * from STARS")
    @Results(value = {
            @Result(property = "coordX", column = "coord_x"),
            @Result(property = "coordY", column = "coord_y"),
            @Result(property = "dictionary", column = "DICT_ID",
                    one = @One(select = "kz.alisher.greetgo.mappers.DictionaryMapper.findById")),
            @Result(property = "user", column = "USER_ID",
                    one = @One(select = "kz.alisher.greetgo.mappers.UserMapper.findById"))
    })
    List<Star> findAllStars();

    @Insert("INSERT INTO STARS (name, coord_x, coord_y, dict_id, user_id) VALUES (#{name}, #{coordX}, #{coordY}, #{dictionary.id}, #{user.id})")
    void insert(Star star);

    @Update("UPDATE STARS SET name=#{name}, coord_x=#{coordX}, coord_y=#{coordY}, dict_id=#{dictionary.id}, user_id=#{user.id} WHERE id = #{id}")
    void update(Star star);

    @Delete("DELETE FROM STARS WHERE id=#{id}")
    void delete(long id);

    @Select("select * from stars where id=#{id}")
    @Results(value = {
            @Result(property = "coordX", column = "coord_x"),
            @Result(property = "coordY", column = "coord_y"),
            @Result(property = "dictionary", column = "DICT_ID",
                    one = @One(select = "kz.alisher.greetgo.mappers.DictionaryMapper.findById")),
            @Result(property = "user", column = "USER_ID",
                    one = @One(select = "kz.alisher.greetgo.mappers.UserMapper.findById"))
    })
    Star findStarById(long id);
}
