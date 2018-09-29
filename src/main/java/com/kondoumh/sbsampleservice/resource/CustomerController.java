package com.kondoumh.sbsampleservice.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @RequestMapping(value = "/usr/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") String id) {
        return new ResponseEntity<>("hello.", HttpStatus.OK);
    }
}
