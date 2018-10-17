package com.kondoumh.sbsampleservice.datasource;

import com.kondoumh.sbsampleservice.entiry.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDao {

    private MongoOperations mongoOperations;

    @Autowired
    public CustomerDao(MongoOperations mongoOperations) { this.mongoOperations = mongoOperations; }

    public Long register(Customer customer) {
        mongoOperations.save(customer);
        return customer.getId();
    }

    public Customer get(Long id) {
        return mongoOperations.findOne(buildQueryById(id), Customer.class);
    }

    private Query buildQueryById(Long id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return query;
    }

    public List<Customer> findByName(String name) {
        return mongoOperations.find(buildQueryByName(name), Customer.class);
    }

    private Query buildQueryByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return query;
    }

    public int update(Customer customer) {
        Customer c = get(customer.getId());
        if (c != null) {
            c.setName(customer.getName());
            mongoOperations.save(c);
            return 1;
        }
        return 0;
    }

    public int delete(Long id) {
        Customer customer = mongoOperations.findOne(buildQueryById(id), Customer.class);
        if (customer != null) {
            mongoOperations.remove(customer);
            return 1;
        }
        return 0;
    }
}
