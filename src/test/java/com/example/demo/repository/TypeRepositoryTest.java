package com.example.demo.repository;

import com.example.demo.entity.Type;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class TypeRepositoryTest {
    @Autowired
    private TypeRepository repository;

    @Test
    public void successSaveType() {
        Type type = new Type();
        type.setName("name");
        Integer id = (repository.save(type)).getId();

        Type find = repository.findById(id).orElse(new Type());

        Assert.assertEquals(type.getName(), find.getName());
    }
}