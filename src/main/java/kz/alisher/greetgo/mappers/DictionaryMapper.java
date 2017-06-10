package kz.alisher.greetgo.mappers;

import kz.alisher.greetgo.domain.Dictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by alisher
 */
@Mapper
public interface DictionaryMapper {
    @Select("select * from dictionary where id = #{id}")
    Dictionary findById(@Param("id") long id);

    @Select("select * from dictionary")
    List<Dictionary> findAllDictionary();
}