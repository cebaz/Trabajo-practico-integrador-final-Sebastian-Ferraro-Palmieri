package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final ProductClient productClient;

    public CustomerController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping
    public List<Map<String, Object>> getCustomers() {
        List<Map<String, Object>> customers = new ArrayList<>();

        Map<String, Object> c1 = new HashMap<>();
        c1.put("id", 1);
        c1.put("nombre", "Sebastian");

        customers.add(c1);

        return customers;
    }

    @GetMapping("/products")
    public List<Map<String, Object>> getCustomerProducts() {
        return productClient.getProducts();
    }
}
