
package com.cigreja.employeewebsite.controllers;

import com.cigreja.employeewebsite.entities.Address;
import com.cigreja.employeewebsite.entities.Employee;
import com.cigreja.employeewebsite.data.repositories.AddressDAO;
import com.cigreja.employeewebsite.data.repositories.EmployeeDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 * GetController
 * @author Carlos Igreja
 * @since  Feb 26, 2016
 */
@Controller
@RequestMapping(value = "/Get")
public class GetController {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    AddressDAO addressDAO;

    @RequestMapping(method = POST)
    public void get(HttpServletRequest request, HttpServletResponse response){

        HashMap<String,Object> model = new HashMap<>();
        String view = "home";
        PrintWriter out = null;

        // json object for test
        JSONObject jsonObject = new JSONObject();

        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get posted information
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        System.out.println("this is parameters");
        System.out.println(firstName);
        System.out.println(lastName);

        // check if user is in the database
        Employee employee = employeeDAO.getEmployee(firstName,lastName);

        System.out.println("this is employee");
        System.out.println(employee.getFirstName());
        System.out.println(employee.getLastName());

        if(employee != null){

            //employeeDAO.persist(employee);
            addressDAO.getAddresses(employee);
            // get employee addresses
            List<Address> addresses = addressDAO.getAddresses(employee);
            model.put("addresses", addresses);
            for(Address a: addresses){
                try {
                    jsonObject.put("id", a.getAddressID());
                    jsonObject.put("address", a.getAddress());
                    System.out.println(jsonObject);
                    out.print(jsonObject);
                    System.out.println(jsonObject);
                } catch (JSONException ex) {
                    Logger.getLogger(GetController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            // display error employee doesn't exist
            //model.put("getErrMsg", "Employee doesn't exist");
            out.print("Employee doesn't exist");
        }
        
        //return new ModelAndView(view,model);
    }
}
