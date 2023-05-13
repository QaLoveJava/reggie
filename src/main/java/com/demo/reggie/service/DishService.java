package com.demo.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.reggie.DTO.DishDto;
import com.demo.reggie.entity.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DishService extends IService<Dish> {

    //新增菜品  同时插入菜品对应的口味数据  操作dish dishFlavor两张表
    public void saveWithFlavor(DishDto dishDto);

    //根据id查信息
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);

    public void deleteWithFlavor(List<Long> ids);
}
