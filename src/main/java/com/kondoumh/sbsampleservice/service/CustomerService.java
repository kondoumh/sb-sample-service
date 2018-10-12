package com.kondoumh.sbsampleservice.service;

import com.kondoumh.sbsampleservice.presentation.exception.ResourceNotFoundException;
import com.kondoumh.sbsampleservice.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
