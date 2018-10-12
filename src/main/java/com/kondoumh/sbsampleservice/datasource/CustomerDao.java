package com.kondoumh.sbsampleservice.datasource;

import com.kondoumh.sbsampleservice.entiry.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomerDao {

    @Autowired
    protected MongoOperations mongoOperations;

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

    public List<Customer> getAll() {
        return mongoOperations.findAll(Customer.class);
    }

    public void update(Customer customer) {
        mongoOperations.save(customer);
    }

    public void delete(Long id) {
        Customer customer = mongoOperations.findOne(buildQueryById(id), Customer.class);
        mongoOperations.remove(customer);
    }
}
