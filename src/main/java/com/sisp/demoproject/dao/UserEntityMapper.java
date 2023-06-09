package com.sisp.demoproject.dao;

import com.sisp.demoproject.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserEntityMapper {
    /*
    查询用户列表
     */
    List<UserEntity> queryUserList(UserEntity userEntity);
    /*
    查询用户基本信息
     */
    int insert(UserEntity userEntity);
    /*
    根据ID删除用户信息
     */
    int deleteUsrById(UserEntity userEntity);

    int deleteUserByName(UserEntity userEntity);
    /*
    编辑用户信息
     */
    int updateByPrimaryKeySelective(UserEntity userEntity);

    List<UserEntity> selectUserInfo(UserEntity userEntity);

    int closeUserByName(UserEntity userEntity);

}
