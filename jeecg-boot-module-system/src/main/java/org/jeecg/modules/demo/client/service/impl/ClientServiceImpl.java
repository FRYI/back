package org.jeecg.modules.demo.client.service.impl;

import org.jeecg.modules.demo.client.entity.Client;
import org.jeecg.modules.demo.client.mapper.ClientMapper;
import org.jeecg.modules.demo.client.service.IClientService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: client
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
