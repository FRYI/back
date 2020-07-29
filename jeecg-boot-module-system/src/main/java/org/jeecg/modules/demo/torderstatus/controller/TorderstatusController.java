package org.jeecg.modules.demo.torderstatus.controller;

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
import org.jeecg.modules.demo.torderstatus.entity.Torderstatus;
import org.jeecg.modules.demo.torderstatus.service.ITorderstatusService;

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
 * @Description: torderstatus
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Api(tags="torderstatus")
@RestController
@RequestMapping("/torderstatus/torderstatus")
@Slf4j
public class TorderstatusController extends JeecgController<Torderstatus, ITorderstatusService> {
	@Autowired
	private ITorderstatusService torderstatusService;
	
	/**
	 * 分页列表查询
	 *
	 * @param torderstatus
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "torderstatus-分页列表查询")
	@ApiOperation(value="torderstatus-分页列表查询", notes="torderstatus-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Torderstatus torderstatus,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Torderstatus> queryWrapper = QueryGenerator.initQueryWrapper(torderstatus, req.getParameterMap());
		Page<Torderstatus> page = new Page<Torderstatus>(pageNo, pageSize);
		IPage<Torderstatus> pageList = torderstatusService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param torderstatus
	 * @return
	 */
	@AutoLog(value = "torderstatus-添加")
	@ApiOperation(value="torderstatus-添加", notes="torderstatus-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Torderstatus torderstatus) {
		torderstatusService.save(torderstatus);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param torderstatus
	 * @return
	 */
	@AutoLog(value = "torderstatus-编辑")
	@ApiOperation(value="torderstatus-编辑", notes="torderstatus-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Torderstatus torderstatus) {
		torderstatusService.updateById(torderstatus);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "torderstatus-通过id删除")
	@ApiOperation(value="torderstatus-通过id删除", notes="torderstatus-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		torderstatusService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "torderstatus-批量删除")
	@ApiOperation(value="torderstatus-批量删除", notes="torderstatus-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.torderstatusService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "torderstatus-通过id查询")
	@ApiOperation(value="torderstatus-通过id查询", notes="torderstatus-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Torderstatus torderstatus = torderstatusService.getById(id);
		if(torderstatus==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(torderstatus);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param torderstatus
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Torderstatus torderstatus) {
        return super.exportXls(request, torderstatus, Torderstatus.class, "torderstatus");
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
        return super.importExcel(request, response, Torderstatus.class);
    }

}
