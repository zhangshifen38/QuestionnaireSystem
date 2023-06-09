package com.sisp.demoproject.controller;

import com.sisp.demoproject.beans.HttpResponseEntity;
import com.sisp.demoproject.dao.entity.UserEntity;
import com.sisp.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasUser = userService.selectUserInfo(userEntity);
            if (!CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser.get(0));
                httpResponseEntity.setMessage("登录成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("用户名或密码错误");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * @brief 用户列表查询
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserList(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasUser = userService.queryUserList(userEntity);
            //System.out.println(hasUser);
            if (!CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("查询成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("无用户信息");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 用户添加
     */
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int res = userService.addUserInfo(userEntity);
            if (res != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("创建成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("创建失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 用户修改
     */
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUser(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int res = userService.modifyUserInfo(userEntity);
            if (res != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("修改成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 用户删除
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUser(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int res = userService.deleteUserByName(userEntity);
            if (res != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/closeUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity closeUser(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int res = userService.closeUserByName(userEntity);
            if (res != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
