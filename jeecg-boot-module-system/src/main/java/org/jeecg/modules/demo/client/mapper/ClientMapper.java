package org.jeecg.modules.demo.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.client.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.product.entity.Product;

/**
 * @Description: client
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
public interface ClientMapper extends BaseMapper<Client> {
    void SaveOrUpdateBatch2(List<Client> list);
}
