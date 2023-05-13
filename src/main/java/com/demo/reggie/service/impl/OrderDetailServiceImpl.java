package com.demo.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.reggie.entity.OrderDetail;
import com.demo.reggie.mapper.OrderDetailMapper;
import com.demo.reggie.service.OrderDetailSerivce;
import com.demo.reggie.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailSerivce {
}
