package com.demo.takeaway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.takeaway.entity.Orders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

    public void again(Orders orders);
}
