package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/customer/{customerId}")
    List<Map<String, Object>> getProductsByCustomerId(@PathVariable Long customerId);
}
