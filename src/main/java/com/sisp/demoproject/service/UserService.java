package com.sisp.demoproject.service;

import com.sisp.demoproject.common.utils.UUIDUtil;
import com.sisp.demoproject.dao.UserEntityMapper;
import com.sisp.demoproject.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    /**
     * 查询用户列表
     */
    public List<UserEntity> queryUserList(UserEntity userEntity){
        List<UserEntity> result=userEntityMapper.queryUserList(userEntity);
        return result;
    }
    /**
     * 创建用户
     */
    public int addUserInfo(UserEntity userEntity){
        userEntity.setId(UUIDUtil.getOneUUID());
        int userResult=userEntityMapper.insert(userEntity);
        if(userResult!=0){
            return 3;   //数字3代表用户存在
        }else{
            return userResult;
        }
    }
    /**
     * 修改用户信息
     */
    public int modifyUserInfo(UserEntity userEntity){
        int userResult=userEntityMapper.updateByPrimaryKeySelective(userEntity);
        return userResult;
    }
    /**
     * 删除用户信息
     */
    public int deleteUserById(UserEntity userEntity){
        int userResult=userEntityMapper.deleteUsrById(userEntity);
        return userResult;
    }

    public int deleteUserByName(UserEntity userEntity){
        int userResult=userEntityMapper.deleteUserByName(userEntity);
        return userResult;
    }

    public int closeUserByName(UserEntity userEntity){
        int res=userEntityMapper.closeUserByName(userEntity);
        return res;
    }

    /**
     * 登录
     * @param userEntity
     * @return
     */
    public List<UserEntity> selectUserInfo(UserEntity userEntity){
        List<UserEntity> res= userEntityMapper.selectUserInfo(userEntity);
        return res;
    }

}
