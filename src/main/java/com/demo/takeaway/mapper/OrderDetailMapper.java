package com.demo.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.takeaway.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
