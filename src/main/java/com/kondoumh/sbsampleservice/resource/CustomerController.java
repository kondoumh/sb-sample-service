package com.kondoumh.sbsampleservice.resource;

import com.kondoumh.sbsampleservice.resource.dto.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @RequestMapping(value = "/usr/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {

        Customer c = new Customer();
        c.setId(1234L);
        c.setName("Bob");
        return new ResponseEntity<Customer>(c, HttpStatus.OK);
    }
}
