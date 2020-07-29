package org.jeecg.modules.demo.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.project.entity.Project;
import org.jeecg.modules.demo.project.service.IProjectService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: project
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Api(tags="project")
@RestController
@RequestMapping("/project/project")
@Slf4j
public class ProjectController extends JeecgController<Project, IProjectService> {
	@Autowired
	private IProjectService projectService;
	
	/**
	 * 分页列表查询
	 *
	 * @param project
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "project-分页列表查询")
	@ApiOperation(value="project-分页列表查询", notes="project-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Project project,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Project> queryWrapper = QueryGenerator.initQueryWrapper(project, req.getParameterMap());
		Page<Project> page = new Page<Project>(pageNo, pageSize);
		IPage<Project> pageList = projectService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param project
	 * @return
	 */
	@AutoLog(value = "project-添加")
	@ApiOperation(value="project-添加", notes="project-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Project project) {
		projectService.save(project);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param project
	 * @return
	 */
	@AutoLog(value = "project-编辑")
	@ApiOperation(value="project-编辑", notes="project-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Project project) {
		projectService.updateById(project);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "project-通过id删除")
	@ApiOperation(value="project-通过id删除", notes="project-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		projectService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "project-批量删除")
	@ApiOperation(value="project-批量删除", notes="project-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.projectService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "project-通过id查询")
	@ApiOperation(value="project-通过id查询", notes="project-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Project project = projectService.getById(id);
		if(project==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(project);
	}



	 /**
	  * 通过id查询
	  *
	  * @param project
	  * @return
	  */
	 @AutoLog(value = "project-通过name查询")
	 @ApiOperation(value="project-通过name查询", notes="project-通过name查询")
	 @GetMapping(value = "/queryByName")
	 public Result<?> queryByName(@RequestParam(name="project",required=true) String project) {
	 	Project project1 = new Project().setProjectName(project);
		  project1 = projectService.getOne(new QueryWrapper<>(project1));
		 if(project1==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(project1);
	 }
    /**
    * 导出excel
    *
    * @param request
    * @param project
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Project project) {
        return super.exportXls(request, project, Project.class, "project");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Project.class);
    }

}
