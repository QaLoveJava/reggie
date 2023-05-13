package com.demo.reggie.DTO;

import com.demo.reggie.entity.Orders;
import lombok.Data;

@Data
public class OrderDto extends Orders {
    private Integer sumNum;
}
