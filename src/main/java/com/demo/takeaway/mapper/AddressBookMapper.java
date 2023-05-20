package com.demo.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.takeaway.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
