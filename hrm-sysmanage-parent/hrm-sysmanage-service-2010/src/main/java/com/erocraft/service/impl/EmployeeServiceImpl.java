package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Employee;
import com.erocraft.mapper.EmployeeMapper;
import com.erocraft.service.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-15
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
