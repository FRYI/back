package org.jeecg.modules.demo.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.product.entity.Product;
import org.jeecg.modules.demo.product.mapper.ProductMapper;
import org.jeecg.modules.demo.product.service.IProductService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.demo.torder.entity.Torder;
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
 * @Description: product
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Api(tags="product")
@RestController
@RequestMapping("/product/product")
@Slf4j
public class ProductController extends JeecgController<Product, IProductService> {
	@Autowired
	private IProductService productService;
	@Autowired
	 ProductMapper productMapper;
	/**
	 * 分页列表查询
	 *
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "product-分页列表查询")
	@ApiOperation(value="product-分页列表查询", notes="product-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Product product,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		QueryWrapper<Product> queryWrapper = QueryGenerator.initQueryWrapper(product, req.getParameterMap());
		Page<Product> page = new Page<Product>(pageNo, pageSize);
		IPage<Product> pageList = productService.page(page, queryWrapper);

		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param product
	 * @return
	 */
	@AutoLog(value = "product-添加")
	@ApiOperation(value="product-添加", notes="product-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Product product) {
		productService.save(product);
		return Result.ok("添加成功！");
	}




	
	/**
	 *  编辑
	 *
	 * @param product
	 * @return
	 */
	@AutoLog(value = "product-编辑")
	@ApiOperation(value="product-编辑", notes="product-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Product product) {
		System.out.println(product.getPhoto());
		productService.updateById(product);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "product-通过id删除")
	@ApiOperation(value="product-通过id删除", notes="product-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		productService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "product-批量删除")
	@ApiOperation(value="product-批量删除", notes="product-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "product-通过id查询")
	@ApiOperation(value="product-通过id查询", notes="product-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Product product = productService.getById(id);
		if(product==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(product);
	}


     /**
      * 通过id查询
      *
      * @param product
      * @return
      */
     @AutoLog(value = "product-通过name查询")
     @ApiOperation(value="product-通过name查询", notes="product-通过name查询")
     @GetMapping(value = "/queryByName")
     public Result<?> queryByName(@RequestParam(name="product",required=true) String product) {
		 System.out.println(product);
		 JSONObject jsonObject = JSON.parseObject(product);
		 System.out.println(jsonObject);
		 Product product1 = new Product();
		 product1.setSku((String) jsonObject.get("product"));
		 product1.setSeason((String) jsonObject.get("season"));
		 try {
			 product1 = productService.getOne(new QueryWrapper<>(product1));
		 }catch (Exception e){
		 	product1 = productService.list(new QueryWrapper<>(product1)).get(0);
		 }

         if(product1==null) {
             return Result.error("未找到对应数据");
         }
         return Result.ok(product1);
     }
    /**
    * 导出excel
    *
    * @param request
    * @param product
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Product product) {
        return super.exportXls(request, product, Product.class, "product");
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
				List<Product> list = ExcelImportUtil.importExcel(file.getInputStream(), Product.class, params);
				//update-begin-author:taoyan date:20190528 for:批量插入数据
				long start = System.currentTimeMillis();

				productMapper.SaveOrUpdateBatch2(list);
				//400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
				//1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒

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
