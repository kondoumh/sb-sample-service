package com.kondoumh.sbsampleservice.datasource;

import com.kondoumh.sbsampleservice.entiry.Customer;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void testRegister() {
        Customer entity = new Customer();
        entity.setId(1111L);
        entity.setName("Hoge");

        Long actual = dao.register(entity);

        assertThat(actual).isEqualTo(entity.getId());
        verify(mongoOperations, times(1)).save(any(Customer.class));
    }

}