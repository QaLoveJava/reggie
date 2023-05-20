package com.demo.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.takeaway.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
