package com.kondoumh.sbsampleservice.service;

import com.kondoumh.sbsampleservice.datasource.CustomerDao;
import com.kondoumh.sbsampleservice.entiry.Customer;
import com.kondoumh.sbsampleservice.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return toDto(entity);
    }

    public List<CustomerDto> findByName(String name) {
        List<Customer> customers = dao.findByName(name);
        return customers.stream().map(this::toDto).collect(Collectors.toList());
    }

    private CustomerDto toDto(Customer entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public Long register(CustomerDto input) {
        Customer c = new Customer();
        c.setId(input.getId());
        c.setName(input.getName());
        return dao.register(c);
    }

    public int update(CustomerDto dto) {
        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return dao.update(entity);
    }

    public int delete(Long id) {
        return dao.delete(id);
    }
}
