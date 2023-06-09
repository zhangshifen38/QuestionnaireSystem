package com.sisp.demoproject;

import com.sisp.demoproject.common.utils.UUIDUtil;
import com.sisp.demoproject.dao.ProjectEntityMapper;
import com.sisp.demoproject.dao.UserEntityMapper;
import com.sisp.demoproject.dao.entity.ProjectEntity;
import com.sisp.demoproject.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

@SpringBootTest
class DemoprojectApplicationTests {

    Logger log = Logger.getLogger(DemoprojectApplicationTests.class);
    //@Test
    public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

    //@Test
    public void selectUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123");
        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>qselectUserInfo用户登录测试成功");
        }
    }

    //@Test
    public void insertUser() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.insert(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert用户插入测试成功");
        }
    }

    //@Test
    public void deleteUserByName() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("aaaaa");
        int i = userEntityMapper.deleteUserByName(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }

    @Test
    public void queryProjectList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity=new ProjectEntity();
        List<ProjectEntity> res = projectEntityMapper.queryProjectList(projectEntity);
        if(CollectionUtils.isEmpty(res)){
            // 记录error级别的信息
            log.error(">>query项目列表查询测试失败");
        }else{
            System.out.println(res);
            // 记录info级别的信息
            log.info(">>query项目列表查询测试成功");
        }
    }

    @Test
    public void insertProject() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("666666");
        int i = projectEntityMapper.insert(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.error(">>insert项目插入测试失败");
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert项目插入测试成功");
        }
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("123456");
        projectEntity.setLastUpdatedBy("admin");
        projectEntity.setLastUpdateDate(new Date());
        int i = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.error(">>update项目更新测试失败");
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>update项目更新测试成功");
        }
    }

    @Test
    public void deleteProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("123456");
        int i = projectEntityMapper.deleteProjectById(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.error(">>delete项目删除测试失败");
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete项目删除测试成功");
        }
    }




}
