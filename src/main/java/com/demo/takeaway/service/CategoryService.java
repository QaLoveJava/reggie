package com.demo.takeaway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.takeaway.entity.Category;


public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
