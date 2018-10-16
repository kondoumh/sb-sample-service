package com.kondoumh.sbsampleservice.service;

import com.kondoumh.sbsampleservice.datasource.CustomerDao;
import com.kondoumh.sbsampleservice.entiry.Customer;
import com.kondoumh.sbsampleservice.presentation.exception.ResourceNotFoundException;
import com.kondoumh.sbsampleservice.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private CustomerDao dao;

    @Autowired
    CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public CustomerDto get(Long id) {
        Customer entity = dao.get(id);
        if (entity == null) {
            LOGGER.warn("Not Found id:{}", id);
            return null;
        }
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
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

    public Long register(CustomerDto input) {
        Customer c = new Customer();
        c.setId(input.getId());
        c.setName(input.getName());
        return dao.register(c);
    }

    public int update(CustomerDto customer) {
        return 1;
    }

    public int delete(Long id) {
        return 1;
    }
}
