package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void successSave() {
        Type type = new Type();
        type.setName("name");
        typeRepository.save(type);

        Product product = new Product();
        product.setName("name");
        product.setType(type);

        productRepository.save(product);

        System.out.println(productRepository.findAll());
    }
}