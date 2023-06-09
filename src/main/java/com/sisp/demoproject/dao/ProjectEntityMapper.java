package com.sisp.demoproject.dao;

import com.sisp.demoproject.dao.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface ProjectEntityMapper {
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);

    ProjectEntity selectProjectById(@Param("id") String id);

    List<ProjectEntity> selectProjectbyName(@Param("name") String name);
    int insert(ProjectEntity projectEntity);

    int updateByPrimaryKeySelective(ProjectEntity projectEntity);

    int deleteProjectById(ProjectEntity projectEntity);
}
