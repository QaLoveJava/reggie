package com.demo.takeaway.controller;

import com.demo.takeaway.service.OrderDetailSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetaiController {

    @Autowired
    private OrderDetailSerivce orderDetailSerivce;
}
