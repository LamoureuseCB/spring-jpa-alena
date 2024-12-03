package org.example.springjpaalena;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.OptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class OptionRepositoryTest {
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private Category laptops;
    private Category smartphones;

    @BeforeEach
    void setUp() {
        laptops = new Category();
        laptops.setName("Laptops");
        categoryRepository.save(laptops);
        smartphones = new Category();
        smartphones.setName("Smartphones");
        categoryRepository.save(smartphones);
    }



    @Test
    void findAllByCategory_IdIn(){
        Option optionForLaptop = new Option();
        optionForLaptop.setCategory(laptops);
        optionForLaptop.setName("Option for lapltop 1");
        optionRepository.save(optionForLaptop);

        Option optionForSmartphone1 = new Option();
        optionForSmartphone1.setCategory(smartphones);
        optionForSmartphone1.setName("Option for smartphone 1");
        optionRepository.save(optionForSmartphone1);


        Option optionForSmartphone2 = new Option();
        optionForSmartphone2.setCategory(smartphones);
        optionForSmartphone2.setName("Option for smartphone 2");
        optionRepository.save(optionForSmartphone2);

        List<Option> options = optionRepository.findAllByCategoryIn(List.of(laptops,smartphones));
        Assertions.assertEquals(3, options.size());

    }



}
