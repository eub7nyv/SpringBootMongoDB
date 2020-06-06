package com.gclass.mongodb.mongodbex.Controller;

import java.util.List;

import com.gclass.mongodb.mongodbex.Services.CustomerRepository;
import com.gclass.mongodb.mongodbex.Vo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoDBController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping
    public Customer createDept(@RequestBody Customer department) {
        customerRepository.save(department);
	    return department;
    }
    @GetMapping
    public List listDepts(){
    return customerRepository.findAll();
    }

    @PutMapping("/{deptId}")
    public Customer updateDept(@RequestBody Customer department, @PathVariable String deptId) {
        department.setId(deptId);
        customerRepository.save(department);
        return department;
    }

    @RequestMapping(path="/saveDataName/{firstName}/{lastname}")
    public String saveData(@PathVariable("firstName") String name, @PathVariable("lastname") String lastname) {

                                Customer employee =new Customer(name,lastname );
                                customerRepository.save(employee);
                               return "Saved to DB"+employee.getFirstName();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Customer addNewUsers(@RequestBody Customer user) {
    //LOG.info("Saving user.");
    return customerRepository.save(user);
    }
    


}