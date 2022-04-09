package com.fgarcialainez.microservices.productcatalogservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCatalogService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(ProductCatalogService.class);

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return mongoTemplate.insert(product);
    }


    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return mongoTemplate.save(product);
    }

    @GetMapping("/product/{id}")
    public Product getProductDetails(@PathVariable String id) {
        logger.info("get product details - process started");

        Product product = mongoTemplate.findById(id, Product.class);

        if (product != null)
            logger.info("get product details - product found");
        else
            logger.info("get product details - product not found");

        return product;
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable String id) {
        Product toDeleteProduct = new Product();
        toDeleteProduct.setId(id);
        mongoTemplate.remove(toDeleteProduct);
        return "Product Deleted-" + id;
    }

    @GetMapping("/product")
    public List < Product > getProductList() {
        return mongoTemplate.findAll(Product.class);
    }
}