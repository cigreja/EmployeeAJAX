
package com.cigreja.employeewebsite.controllers;

import com.cigreja.employeewebsite.entities.Address;
import com.cigreja.employeewebsite.entities.Employee;
import com.cigreja.employeewebsite.data.repositories.AddressDAO;
import com.cigreja.employeewebsite.data.repositories.EmployeeDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * AddController
 * @author Carlos Igreja
 * @since  Feb 21, 2016
 */
@Controller
@RequestMapping(value = "/Add")
public class AddController {
    
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    AddressDAO addressDAO;

    @RequestMapping(method = POST)
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Response Writer
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();

        // Employee
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Employee employee = employeeDAO.getEmployee(firstName,lastName);
        if(employee == null){
            employee = new Employee(firstName, lastName);
        }

        // Address
        Address address = new Address(request.getParameter("address"));
        List<Address> allAddresses = addressDAO.getAddresses();
        address = addressDAO.containsAddress(allAddresses,address);

        // add employee address
        List<Address> employeeAddresses = addressDAO.getAddresses(employee);
        if(!addressDAO.addressExists(employeeAddresses,address)){
            employee.getAddresses().add(address);
            employeeDAO.merge(employee);
        }
        else{
            writer.print("Employee address already exists");
        }
    }
}
