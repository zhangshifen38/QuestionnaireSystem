package com.sisp.demoproject.service;

import com.sisp.demoproject.common.utils.UUIDUtil;
import com.sisp.demoproject.dao.ProjectEntityMapper;
import com.sisp.demoproject.dao.entity.ProjectEntity;
import com.sisp.demoproject.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){
        List<ProjectEntity> res = projectEntityMapper.queryProjectList(projectEntity);
        return res;
    }

    public ProjectEntity selectProjectById(String id){
        ProjectEntity res = projectEntityMapper.selectProjectById(id);
        return res;
    }
    public List<ProjectEntity> selectProjectByName(String projectName){
        List<ProjectEntity> res = projectEntityMapper.selectProjectbyName(projectName);
        return res;
    }

    public int addProjectInfo(ProjectEntity projectEntity){
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setCreationDate(new Date());
        projectEntity.setLastUpdateDate(new Date());
        System.out.println(projectEntity.getUserId());
        int res=projectEntityMapper.insert(projectEntity);
        if(res!=0){
            return 3;
        }else {
            return res;
        }
    }

    public int deleteProjectById(ProjectEntity projectEntity){
        int res=projectEntityMapper.deleteProjectById(projectEntity);
        return res;
    }

    public int modifyProjectInfo(ProjectEntity projectEntity){
        projectEntity.setLastUpdateDate(new Date());
        int res=projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        return res;
    }

}
