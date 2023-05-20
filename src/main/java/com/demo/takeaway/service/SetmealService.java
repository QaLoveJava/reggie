package com.demo.takeaway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.takeaway.DTO.SetmealDto;
import com.demo.takeaway.entity.Setmeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SetmealService extends IService<Setmeal> {

    void saveWithDish(SetmealDto setmealDto);

    //删除套餐   同时删除套餐和菜品的关联数据
    void removeWithDish(List<Long> ids);

    SetmealDto getWithDish(Long id);

    void updateWithDish(SetmealDto setmealDto);

}
