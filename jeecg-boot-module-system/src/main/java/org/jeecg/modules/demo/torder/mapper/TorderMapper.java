package org.jeecg.modules.demo.torder.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.torder.entity.Torder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.torder.entity.TorderAndPic;

/**
 * @Description: torder
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
public interface TorderMapper extends BaseMapper<Torder> {

     IPage<Torder> getDetail(Page<Torder> page, @Param(Constants.WRAPPER) Wrapper<Torder> queryWrapper);
     List<String> selectSeason();
     void SaveOrUpdateBatch2(List<Torder> list);
     void SaveOrUpdateBatch3(List<TorderAndPic> list);
     List<TorderAndPic> getOrderAndPic(@Param(Constants.WRAPPER) Wrapper<Torder> queryWrapper);
     List<TorderAndPic> getOrderAndPic(@Param("ids")List<String> ids);
}


