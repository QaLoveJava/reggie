package com.demo.takeaway.DTO;

import com.demo.takeaway.entity.Orders;
import lombok.Data;

@Data
public class OrderDto extends Orders {
    private Integer sumNum;
}
