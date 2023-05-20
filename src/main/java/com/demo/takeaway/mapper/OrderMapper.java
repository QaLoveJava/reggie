package com.demo.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.takeaway.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
