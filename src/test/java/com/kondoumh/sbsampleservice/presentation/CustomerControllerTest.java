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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    }

    @Test
    public void testRegisterCustomer() {
    }

    @Test
    public void testDeleteCustomer() {
    }

    @Test
    public void testUpdateCustomer() {
    }
}