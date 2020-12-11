package org.jeecg.modules.demo.torder.controller;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.product.entity.Product;
import org.jeecg.modules.demo.product.mapper.ProductMapper;
import org.jeecg.modules.demo.torder.entity.Torder;
import org.jeecg.modules.demo.torder.entity.TorderAndPic;
import org.jeecg.modules.demo.torder.mapper.TorderMapper;
import org.jeecg.modules.demo.torder.service.ITorderService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
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

import static org.jeecgframework.poi.excel.entity.enmus.CellValueType.Date;

/**
 * @Description: torder
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Api(tags="torder")
@RestController
@RequestMapping("/torder/torder")
@Slf4j
public class TorderController extends JeecgController<Torder, ITorderService> {

	@Autowired
	private ITorderService torderService;
	@Autowired
	private TorderMapper torderMapper;
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param torder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "torder-分页列表查询")
	@ApiOperation(value="torder-分页列表查询", notes="torder-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Torder torder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="30") Integer pageSize,
								   HttpServletRequest req) {


		QueryWrapper<Torder> queryWrapper = QueryGenerator.initQueryWrapper(torder, req.getParameterMap());

		Page<Torder> page = new Page<Torder>(pageNo, pageSize);

		IPage<Torder> pageList = torderMapper.getDetail(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param torder
	 * @return
	 */
	@AutoLog(value = "torder-添加")
	@ApiOperation(value="torder-添加", notes="torder-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Torder torder) {
//		System.currentTimeMillis()
		Date date = new Date();
		System.out.println("jinru");
		torderService.save(torder);
		Date date1 = new Date();
		System.out.println(date.toString());
		System.out.println(date1.toString());

		return Result.ok("添加成功！");
	}


	 @GetMapping(value = "/season")
	 public Result<?> selectSeason() {
		 ArrayList<String> strings = (ArrayList<String>) torderMapper.selectSeason();
		 System.out.println(strings.toString());
		 return Result.ok(strings);
	 }
	/**
	 *  编辑
	 *
	 * @param torder
	 * @return
	 */
	@AutoLog(value = "torder-编辑")
	@ApiOperation(value="torder-编辑", notes="torder-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Torder torder) {
		torderService.updateById(torder);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "torder-通过id删除")
	@ApiOperation(value="torder-通过id删除", notes="torder-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		torderService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "torder-批量删除")
	@ApiOperation(value="torder-批量删除", notes="torder-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.torderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "torder-通过id查询")
	@ApiOperation(value="torder-通过id查询", notes="torder-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Torder torder = torderService.getById(id);
		if(torder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(torder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param torder
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Torder torder) {

//        return super.exportXls(request, torder, Torder.class, "torder");
		// Step.1 组装查询条件
		QueryWrapper<Torder> queryWrapper = QueryGenerator.initQueryWrapper(torder, request.getParameterMap());
		System.out.println(queryWrapper.toString());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		// Step.2 获取导出数据
		List<TorderAndPic> pageList = torderMapper.getOrderAndPic(queryWrapper);
		System.out.println("yes");

		List<TorderAndPic> exportList = null;

		// 过滤选中数据
		String selections = request.getParameter("selections");
		if (oConvertUtils.isNotEmpty(selections)) {
			List<String> selectionList = Arrays.asList(selections.split(","));
			exportList = pageList.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
			if(exportList.size()<1){
				System.out.println("空指针");
				System.out.println(exportList.size());
			}
			if(exportList.size()>1){
				System.out.println("bus空指针");
				System.out.println(exportList.size());
			}
		} else {
			exportList = pageList;
		}

		// Step.3 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "torder"+System.currentTimeMillis()); //此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.CLASS, TorderAndPic.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("TotalOrder" , "TotalOrderList", ExcelType.XSSF));
		mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		return mv;
    }


	 private String getId(TorderAndPic item) {
		 try {
			 return PropertyUtils.getProperty(item, "id").toString();
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }
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
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<TorderAndPic> list = ExcelImportUtil.importExcel(file.getInputStream(), TorderAndPic.class, params);
                //update-begin-author:taoyan date:20190528 for:批量插入数据
                long start = System.currentTimeMillis();
				System.out.println(list.toString());
				List<Product> productList = new ArrayList<>();
				list.forEach(item->{
					Product product = new Product();

					product.setParamData(item.getParamData());
					product.setPhotoString(item.getPhotoString());
					product.setSeason(item.getSeason());
					product.setSku(item.getProduct());
					productList.add(product);
				});
				productMapper.SaveOrUpdateBatch2(productList);
                torderMapper.SaveOrUpdateBatch3(list);


                //update-end-author:taoyan date:20190528 for:批量插入数据
                return Result.ok("文件导入成功！数据行数：" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("文件导入失败！");

    }

}
