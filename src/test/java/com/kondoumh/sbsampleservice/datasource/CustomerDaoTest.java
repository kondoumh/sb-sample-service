package com.kondoumh.sbsampleservice.datasource;

import com.kondoumh.sbsampleservice.entiry.Customer;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

public class CustomerDaoTest {

    @Mock
    private MongoOperations mongoOperations;

    private CustomerDao dao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dao = new CustomerDao(mongoOperations);
    }

    @Test
    public void testGet() {
        Customer entity = createCustomer(1234L, "Foo");

        when(mongoOperations.findOne(any(Query.class), eq(Customer.class))).thenReturn(entity);

        Customer actual = dao.get(1234L);
        assertThat(actual).isEqualTo(entity);
        verify(mongoOperations, times(1)).findOne(any(Query.class), eq(Customer.class));
    }

    @Test
    public void testRegister() {
        Customer entity = createCustomer(1111L, "Hoge");

        Long actual = dao.register(entity);

        assertThat(actual).isEqualTo(entity.getId());
        verify(mongoOperations, times(1)).save(any(Customer.class));
    }

    @Test
    public void testFindByName() {
        Customer entity1 = createCustomer(1000L, "Bar");
        Customer entity2 = createCustomer(1001L, "Bar");
        List<Customer> entities = Arrays.asList(entity1, entity2);

        when(mongoOperations.find(any(Query.class), eq(Customer.class))).thenReturn(entities);

        List<Customer> actual = dao.findByName("Bar");
        assertThat(actual).containsSequence(entity1, entity2);
        verify(mongoOperations, times(1)).find(any(Query.class), eq(Customer.class));
    }

    @Test
    public void testUpdate() {
        Customer entity = createCustomer(1111L, "Fuga");
        when(mongoOperations.findOne(any(Query.class), eq(Customer.class))).thenReturn(entity);
        int actual = dao.update(entity);

        assertThat(actual).isEqualTo(1);
        verify(mongoOperations, times(1)).findOne(any(Query.class), eq(Customer.class));
        verify(mongoOperations, times(1)).save(eq(entity));
    }

    @Test
    public void testUpdateNotFound() {
        Customer entity = createCustomer(1111L, "Fuga");
        when(mongoOperations.findOne(any(Query.class), eq(Customer.class))).thenReturn(null);
        int actual = dao.update(entity);

        assertThat(actual).isEqualTo(0);
        verify(mongoOperations, times(1)).findOne(any(Query.class), eq(Customer.class));
    }

    @Test
    public void testDelete() {
        Customer entity = createCustomer(1111L, "Fuga");
        when(mongoOperations.findOne(any(Query.class), eq(Customer.class))).thenReturn(entity);
        int actual = dao.delete(1111L);

        assertThat(actual).isEqualTo(1);
        verify(mongoOperations, times(1)).findOne(any(Query.class), eq(Customer.class));
        verify(mongoOperations, times(1)).remove(eq(entity));
    }

    @Test
    public void testDeleteNotFound() {
        when(mongoOperations.findOne(any(Query.class), eq(Customer.class))).thenReturn(null);
        int actual = dao.delete(1111L);
        assertThat(actual).isEqualTo(0);
        verify(mongoOperations, times(1)).findOne(any(Query.class), eq(Customer.class));
    }

    private Customer createCustomer(Long id, String name) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        return customer;
    }
}