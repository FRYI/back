package org.jeecg.modules.demo.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.torder.entity.Torder;

/**
 * @Description: product
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
public interface ProductMapper extends BaseMapper<Product> {
    void SaveOrUpdateBatch2(List<Product> list);
}
