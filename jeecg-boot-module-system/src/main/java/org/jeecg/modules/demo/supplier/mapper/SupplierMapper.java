package org.jeecg.modules.demo.supplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.supplier.entity.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.torder.entity.Torder;

/**
 * @Description: supplier
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
public interface SupplierMapper extends BaseMapper<Supplier> {
    void SaveOrUpdateBatch2(List<Supplier> list);
}
