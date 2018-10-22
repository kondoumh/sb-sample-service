package com.kondoumh.sbsampleservice.service;

import com.kondoumh.sbsampleservice.datasource.CustomerDao;
import com.kondoumh.sbsampleservice.dto.CustomerDto;
import com.kondoumh.sbsampleservice.entiry.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.*;

public class CustomerServiceTest {

    @Mock
    private CustomerDao dao;

    private CustomerService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new CustomerService(dao);
    }

    @Test
    public void testGet() {
        Customer entity = new Customer();
        entity.setId(1234L);
        entity.setName("Bob");
        when(dao.get(any(Long.class))).thenReturn(entity);

        CustomerDto actual = service.get(1234L);
        assertThat(actual.getId()).isEqualTo(1234L);
        assertThat(actual.getName()).isEqualTo("Bob");
        verify(dao, times(1)).get(any(Long.class));
    }

    @Test
    public void testGetNotFound() {
        when(dao.get(any(Long.class))).thenReturn(null);
        CustomerDto actual = service.get(1234L);
        assertThat(actual).isNull();
        verify(dao, times(1)).get(any(Long.class));
    }

    @Test
    public void testFindByName() {
        List<Customer> entities = Arrays.asList(new Customer(), new Customer());
        when(dao.findByName(any(String.class))).thenReturn(entities);
        List<CustomerDto> actual = service.findByName("Bob");
        assertThat(actual.size()).isEqualTo(2);
        verify(dao, times(1)).findByName(eq("Bob"));
    }

    @Test
    public void testRegister() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}