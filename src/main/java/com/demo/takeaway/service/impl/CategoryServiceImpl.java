package com.demo.takeaway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.takeaway.common.CustomException;
import com.demo.takeaway.entity.Category;
import com.demo.takeaway.entity.Dish;
import com.demo.takeaway.entity.Setmeal;
import com.demo.takeaway.mapper.CategoryMapper;
import com.demo.takeaway.service.CategoryService;
import com.demo.takeaway.service.DishService;
import com.demo.takeaway.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;


    /**
     * 根据id来删除分类 删除之前进行判断
     * @param id
     */

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联菜品 若已关联，抛出业务异常
        if(count>0){
            throw new CustomException("当前分类下关联了菜品，无法删除");
        }
        //查询当前分类是否关联套餐 若已关联，抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类下关联了套餐，无法删除");
        }

        //正常删除
        super.removeById(id);
    }
}
