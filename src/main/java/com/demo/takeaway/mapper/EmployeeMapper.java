package com.demo.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.takeaway.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
