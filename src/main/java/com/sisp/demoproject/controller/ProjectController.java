package com.sisp.demoproject.controller;

import com.sisp.demoproject.beans.HttpResponseEntity;
import com.sisp.demoproject.dao.entity.ProjectEntity;
import com.sisp.demoproject.dao.entity.UserEntity;
import com.sisp.demoproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/queryProjectList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<ProjectEntity> projectList = projectService.queryProjectList(projectEntity);
            //System.out.println(projectList);
            if (!CollectionUtils.isEmpty(projectList)) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(projectList);
                httpResponseEntity.setMessage("查询成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(projectList);
                httpResponseEntity.setMessage("无项目信息");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectProjectById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity selectProjectById(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            ProjectEntity res = projectService.selectProjectById(projectEntity.getId());
            //System.out.println(res);
            //System.out.println(projectEntity);
            if (res!=null) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("无项目信息");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectProjectByName",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity selectProjectByName(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<ProjectEntity> res = projectService.selectProjectByName(projectEntity.getProjectName());
            //System.out.println(res);
            //System.out.println(projectEntity);
            if (!CollectionUtils.isEmpty(res)) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("无项目信息");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addProject",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addProject(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int res = projectService.addProjectInfo(projectEntity);
            //System.out.println(res);
            //System.out.println(projectEntity);
            if (res!=0) {
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("添加成功");
            } else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("添加失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteProjectById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int res = projectService.deleteProjectById(projectEntity);
            //System.out.println(res);
            //System.out.println(projectEntity);
            if (res!=0) {
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    @RequestMapping(value = "/modifyProject",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity modifyProject(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {

            int res= projectService.modifyProjectInfo(projectEntity);
            if (res!=0) {
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("修改成功");
            } else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
