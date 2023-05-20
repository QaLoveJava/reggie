package com.demo.takeaway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.takeaway.entity.AddressBook;
import com.demo.takeaway.mapper.AddressBookMapper;
import com.demo.takeaway.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
