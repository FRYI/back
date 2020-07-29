package org.jeecg.modules.demo.project.service.impl;

import org.jeecg.modules.demo.project.entity.Project;
import org.jeecg.modules.demo.project.mapper.ProjectMapper;
import org.jeecg.modules.demo.project.service.IProjectService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: project
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
