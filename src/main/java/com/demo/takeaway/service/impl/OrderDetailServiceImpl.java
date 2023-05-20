package com.demo.takeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.takeaway.entity.OrderDetail;
import com.demo.takeaway.mapper.OrderDetailMapper;
import com.demo.takeaway.service.OrderDetailSerivce;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailSerivce {
}
