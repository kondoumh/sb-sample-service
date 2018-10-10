package com.kondoumh.sbsampleservice.resource;

import com.kondoumh.sbsampleservice.resource.dto.Customer;
import com.kondoumh.sbsampleservice.service.CustomerService;
import com.kondoumh.sbsampleservice.service.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService servie;

    @Autowired
    public CustomerController(CustomerService servie) {
        this.servie = servie;
    }

    @ResponseBody
    @RequestMapping(value = "/usr/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("id") Long id) {
        CustomerDto dto = servie.get(id);
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        return customer;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public List<Customer> findCustomer(@PathVariable("name") String name) {
        Customer a = new Customer();
        a.setId(1111L);
        a.setName(name);
        Customer b = new Customer();
        b.setId(2222L);
        b.setName(name);
        return Arrays.asList(a, b);
    }

    @ResponseBody
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public Long registerCustomer(@RequestBody @Validated Customer customer) {

        Long id = Long.valueOf(1234L);
        Customer c = new Customer();
        c.setId(id);
        c.setName(customer.getName());
        return c.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public int deleteCustomer(@PathVariable("id") Long id) {
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    public int updateCustomer(@RequestBody @Validated Customer customer) {
        return 1;
    }
}
