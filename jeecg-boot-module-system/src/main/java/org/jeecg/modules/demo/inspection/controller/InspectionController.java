package org.jeecg.modules.demo.inspection.controller;

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
import org.jeecg.modules.demo.inspection.entity.Inspection;
import org.jeecg.modules.demo.inspection.service.IInspectionService;

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
 * @Description: inspection
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Api(tags="inspection")
@RestController
@RequestMapping("/inspection/inspection")
@Slf4j
public class InspectionController extends JeecgController<Inspection, IInspectionService> {
	@Autowired
	private IInspectionService inspectionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param inspection
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "inspection-分页列表查询")
	@ApiOperation(value="inspection-分页列表查询", notes="inspection-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Inspection inspection,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Inspection> queryWrapper = QueryGenerator.initQueryWrapper(inspection, req.getParameterMap());
		Page<Inspection> page = new Page<Inspection>(pageNo, pageSize);
		IPage<Inspection> pageList = inspectionService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param inspection
	 * @return
	 */
	@AutoLog(value = "inspection-添加")
	@ApiOperation(value="inspection-添加", notes="inspection-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Inspection inspection) {
		inspectionService.save(inspection);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param inspection
	 * @return
	 */
	@AutoLog(value = "inspection-编辑")
	@ApiOperation(value="inspection-编辑", notes="inspection-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Inspection inspection) {
		inspectionService.updateById(inspection);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "inspection-通过id删除")
	@ApiOperation(value="inspection-通过id删除", notes="inspection-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		inspectionService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "inspection-批量删除")
	@ApiOperation(value="inspection-批量删除", notes="inspection-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.inspectionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "inspection-通过id查询")
	@ApiOperation(value="inspection-通过id查询", notes="inspection-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Inspection inspection = inspectionService.getById(id);
		if(inspection==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(inspection);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param inspection
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Inspection inspection) {
        return super.exportXls(request, inspection, Inspection.class, "inspection");
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
        return super.importExcel(request, response, Inspection.class);
    }

}
