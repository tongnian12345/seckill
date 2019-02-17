package com.imooc.dao;

import com.imooc.domain.MiaoshaUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);

    @Insert("insert into user(id, name) values (#{id}, #{name}")
    public int insert(MiaoshaUser user);

    @Select("select * from user where nickname = #{nickname}")
    public MiaoshaUser getByNickname(@Param("nickname") String nickname);

    @Select("select * from user where nickname = #{nickname} and password = #{password}")
    public MiaoshaUser getByNamePassword(@Param("nickname") String nickname, @Param("password") String password);

    @Update("update user set password = #{password} where id = #{id}")
    public void update(MiaoshaUser toBeUpdate);
}
