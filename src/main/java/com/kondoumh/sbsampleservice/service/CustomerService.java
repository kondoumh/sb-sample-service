package com.kondoumh.sbsampleservice.service;

import com.kondoumh.sbsampleservice.presentation.exception.ResourceNotFoundException;
import com.kondoumh.sbsampleservice.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public CustomerDto get(Long id) {

        if (id == 1L) {
            LOGGER.warn("Not Found id:{}", id);
            throw new ResourceNotFoundException();
        }
        CustomerDto c = new CustomerDto();
        c.setId(id);
        c.setName("Bob");
        return c;
    }

    public List<CustomerDto> findByName(String name) {
        CustomerDto a = new CustomerDto();
        a.setId(1111L);
        a.setName(name);
        CustomerDto b = new CustomerDto();
        b.setId(2222L);
        b.setName(name);
        return Arrays.asList(a, b);
    }

    public Long register(CustomerDto customer) {
        Long id = Long.valueOf(1234L);
        CustomerDto c = new CustomerDto();
        c.setId(id);
        c.setName(customer.getName());
        return c.getId();
    }

    public int update(CustomerDto customer) {
        return 1;
    }

    public int delete(Long id) {
        return 1;
    }
}
