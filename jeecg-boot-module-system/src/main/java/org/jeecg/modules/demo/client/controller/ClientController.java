package org.jeecg.modules.demo.client.controller;

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
import org.jeecg.modules.demo.client.entity.Client;
import org.jeecg.modules.demo.client.service.IClientService;

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
 * @Description: client
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Api(tags="client")
@RestController
@RequestMapping("/client/client")
@Slf4j
public class ClientController extends JeecgController<Client, IClientService> {
	@Autowired
	private IClientService clientService;
	
	/**
	 * 分页列表查询
	 *
	 * @param client
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "client-分页列表查询")
	@ApiOperation(value="client-分页列表查询", notes="client-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Client client,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Client> queryWrapper = QueryGenerator.initQueryWrapper(client, req.getParameterMap());
		Page<Client> page = new Page<Client>(pageNo, pageSize);
		IPage<Client> pageList = clientService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param client
	 * @return
	 */
	@AutoLog(value = "client-添加")
	@ApiOperation(value="client-添加", notes="client-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Client client) {
		clientService.save(client);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param client
	 * @return
	 */
	@AutoLog(value = "client-编辑")
	@ApiOperation(value="client-编辑", notes="client-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Client client) {
		clientService.updateById(client);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "client-通过id删除")
	@ApiOperation(value="client-通过id删除", notes="client-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		clientService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "client-批量删除")
	@ApiOperation(value="client-批量删除", notes="client-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.clientService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "client-通过id查询")
	@ApiOperation(value="client-通过id查询", notes="client-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Client client = clientService.getById(id);
		if(client==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(client);
	}


	 /**
	  * 通过id查询
	  *
	  * @param  client
	  * @return
	  */
	 @AutoLog(value = "client-通过name查询")
	 @ApiOperation(value="client-通过name查询", notes="client-通过name查询")
	 @GetMapping(value = "/queryByName")
	 public Result<?> queryByName(@RequestParam(name="client",required=true) String client) {
		 Client client1 = new Client().setClient(client);
	 	 client1 = clientService.getOne(new QueryWrapper<>(client1));
		 if(client1==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(client1);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param client
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Client client) {
        return super.exportXls(request, client, Client.class, "client");
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
        return super.importExcel(request, response, Client.class);
    }

}
