//package com.app.authentication.service;
//
//import com.app.authentication.model.Employee;
//import com.app.authentication.repository.EmployeeRepository;
//import com.mdimension.jchronic.Chronic;
//import com.mdimension.jchronic.utils.Span;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Override
//    public void saveData(Employee employee) {
//
//        Span span = Chronic.parse(employee.getDate());
//        employee.setDate(span.toString());
//        employee.setAction(employee.getAction());
//        employeeRepository.save(employee);
//}
//}