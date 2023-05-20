package com.demo.takeaway.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.takeaway.DTO.OrderDto;
import com.demo.takeaway.common.BaseContext;
import com.demo.takeaway.common.R;
import com.demo.takeaway.entity.OrderDetail;
import com.demo.takeaway.entity.Orders;
import com.demo.takeaway.service.OrderDetailSerivce;
import com.demo.takeaway.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailSerivce orderDetailSerivce;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 后台查看订单
     */
    @GetMapping("/page")
    public R<Page> backendPage(int page,int pageSize,String number,String beginTime,String endTime){
        Page<Orders> page1 = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(number != null,Orders::getNumber,number);
        if(beginTime!=null && endTime!=null){
            wrapper.ge(Orders::getOrderTime,beginTime);
            wrapper.le(Orders::getOrderTime,endTime);
        }

        wrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(page1,wrapper);
        return R.success(page1);
    }

    /**
     * 用户查看订单
     */
    @GetMapping("/userPage")
    public R<Page> frontPage(int page,int pageSize){
        //后台查出orders   然后给orderDto赋值
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
        wrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo,wrapper);

        //创建OrderDto  复制数据
        Page<OrderDto> page1 = new Page<>();
        BeanUtils.copyProperties(pageInfo,page1,"records");
        List<Orders> orders = pageInfo.getRecords();
        List<OrderDto> collect = orders.stream().map((item) -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(item, orderDto);
            String number = orderDto.getNumber();
            LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(number != null, OrderDetail::getOrderId, number);
            List<OrderDetail> list = orderDetailSerivce.list(queryWrapper);
            int sum = 0;
            for (OrderDetail orderDetail : list) {
                sum += orderDetail.getNumber().intValue();
            }
            orderDto.setSumNum(sum);
            return orderDto;
        }).collect(Collectors.toList());
        page1.setRecords(collect);

        return R.success(page1);
    }

    /**
     * 后台修改订单状态
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> putOrder(@RequestBody Orders orders){
        Long id = orders.getId();
        Orders orders1 = orderService.getById(id);
        orders1.setStatus(orders.getStatus());
        orderService.updateById(orders1);

        return R.success("修改成功");
    }


    @PostMapping("/again")
    public R<String> again(@RequestBody Orders orders){
        orderService.again(orders);
        return R.success("再来一单");
    }
}
