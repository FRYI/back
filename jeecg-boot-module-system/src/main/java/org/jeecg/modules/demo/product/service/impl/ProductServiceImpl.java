package org.jeecg.modules.demo.product.service.impl;

import org.jeecg.modules.demo.product.entity.Product;
import org.jeecg.modules.demo.product.mapper.ProductMapper;
import org.jeecg.modules.demo.product.service.IProductService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: product
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
