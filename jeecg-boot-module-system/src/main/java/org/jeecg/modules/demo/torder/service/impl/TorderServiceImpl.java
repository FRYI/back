package org.jeecg.modules.demo.torder.service.impl;

import org.jeecg.modules.demo.torder.entity.Torder;
import org.jeecg.modules.demo.torder.mapper.TorderMapper;
import org.jeecg.modules.demo.torder.service.ITorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: torder
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Service
public class TorderServiceImpl extends ServiceImpl<TorderMapper, Torder> implements ITorderService {
//    @Autowired
//    TorderMapper torderMapper;
//
//    public void SaveOrUpdateBatch2(List<Torder> list){
//        torderMapper.SaveOrUpdateBatch2(list);
//    }
}
