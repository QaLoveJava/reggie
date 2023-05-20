package com.demo.takeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.takeaway.entity.SetmealDish;
import com.demo.takeaway.mapper.SetMealDishMapper;
import com.demo.takeaway.service.SetmealDishService;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetMealDishMapper, SetmealDish> implements SetmealDishService {
}
