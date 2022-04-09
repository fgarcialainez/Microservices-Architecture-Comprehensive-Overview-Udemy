package com.fgarcialainez.microservices.productinventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class ProductInventoryService {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @PostMapping("/inventory")
    public Mono<ProductInventory> addProductInventory(@RequestBody ProductInventory product) {
        return mongoTemplate.insert(product);
    }

    @PutMapping("/inventory")
    public Mono <ProductInventory> updateProductInventory(@RequestBody ProductInventory product)  {
        return mongoTemplate.save(product);
    }

    @GetMapping("/inventory/{id}")
    public Mono <ProductInventory> getProductInventory(@PathVariable String id) {
        return mongoTemplate.findById(id, ProductInventory.class);
    }
}
