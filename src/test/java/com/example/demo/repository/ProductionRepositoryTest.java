package com.example.demo.repository;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import com.example.demo.entity.Type;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ProductionRepositoryTest {
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void successSaveAndFind() {
        Type type = new Type();
        type.setName("name");
        typeRepository.save(type);

        Product product = new Product();
        product.setName("name");
        product.setType(type);
        productRepository.save(product);

        Producer producer = new Producer();
        producer.setName("prodName");
        producer.setHeadName("headName");
        producer.setAddress("address");
        producer.setHeadPosition("headPosition");
        producer.setPhoneMarketingDepartment(12345678L);
        producer.setPhoneMarketingHead(98765432L);
        producer.setContactName("contactName");
        producerRepository.save(producer);

        Production production = new Production();
        production.setProduct(product);
        production.setProducer(producer);
        production.setDate(new Date());
        production.setPlace("minsk");

        productionRepository.save(production);
        System.out.println(production);
        System.out.println(productionRepository.findAll());

        Assert.assertEquals(production.toString(), productionRepository.findAll().get(0).toString());
    }

}