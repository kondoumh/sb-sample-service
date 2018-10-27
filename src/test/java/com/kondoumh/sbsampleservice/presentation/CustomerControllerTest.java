package com.kondoumh.sbsampleservice.presentation;

import com.kondoumh.sbsampleservice.dto.CustomerDto;
import com.kondoumh.sbsampleservice.presentation.exception.ResourceNotFoundException;
import com.kondoumh.sbsampleservice.service.CustomerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @Mock
    private CustomerService service;

    private CustomerController controller;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CustomerController(service);
    }

    @Test
    public void testGetCustomer() {
        when(service.get(any(Long.class))).thenReturn(createDto(1234L, "Hoge"));
        CustomerDto actual = controller.getCustomer(1234L);
        assertThat(actual.getId()).isEqualTo(1234L);
        verify(service, times(1)).get(any(Long.class));
    }

    private CustomerDto createDto(Long id, String name) {
        CustomerDto dto = new CustomerDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

    @Test
    public void testGetCustomerNotFound() {
        expectedException.expect(ResourceNotFoundException.class);
        when(service.get(any(Long.class))).thenReturn(null);
        controller.getCustomer(1234L);
        verify(service, times(1)).get(any(Long.class));
    }

    @Test
    public void testFindCustomer() {
        List<CustomerDto> dtos = Arrays.asList(new CustomerDto(), new CustomerDto());
        when(service.findByName(any(String.class))).thenReturn(dtos);
        List<CustomerDto> actual = controller.findCustomer("Bob");

        assertThat(actual.size()).isEqualTo(2);
        verify(service, times(1)).findByName(eq("Bob"));
    }

    @Test
    public void testRegisterCustomer() {
        CustomerDto dto = new CustomerDto();
        dto.setId(1234L);
        when(service.register(any(CustomerDto.class))).thenReturn(dto.getId());

        Long actual = controller.registerCustomer(dto);
        assertThat(actual).isEqualTo(1234L);
        verify(service, times(1)).register(any(CustomerDto.class));
    }

    @Test
    public void testDeleteCustomer() {
        when(service.delete(any(Long.class))).thenReturn(1);

        int actual = controller.deleteCustomer(1234L);
        assertThat(actual).isEqualTo(1);
        verify(service, times(1)).delete(eq(1234L));
    }

    @Test
    public void testUpdateCustomer() {
        CustomerDto dto = new CustomerDto();
        when(service.update(any(CustomerDto.class))).thenReturn(1);

        int actual = controller.updateCustomer(dto);
        assertThat(actual).isEqualTo(1);
        verify(service, times(1)).update(any(CustomerDto.class));
    }
}