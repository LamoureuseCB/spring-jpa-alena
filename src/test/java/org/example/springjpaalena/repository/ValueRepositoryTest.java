package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.model.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class ValueRepositoryTest {
    @Autowired
    private ValueRepository valueRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private Category smartphones;
    private Category laptops;


    @Test
    void productsWithOptionsAndValues_Test1() {
        smartphones = new Category();
        smartphones.setName("Smartphones");
        smartphones = categoryRepository.save(smartphones);

        Option colorOption = new Option();
        colorOption.setName("Color");
        colorOption.setCategory(smartphones);
        optionRepository.save(colorOption);

        Option storage = new Option();
        storage.setName("Storage");
        storage.setCategory(smartphones);
        optionRepository.save(storage);

        Product product = new Product();
        product.setName("iPhone 15");
        product.setCategory(smartphones);
        productRepository.save(product);

        Value colorBlack = new Value();
        colorBlack.setName("Black");
        colorBlack.setProduct(product);
        colorBlack.setOption(colorOption);
        valueRepository.save(colorBlack);

        Value storage128GB = new Value();
        storage128GB.setName("128GB");
        storage128GB.setProduct(product);
        storage128GB.setOption(storage);
        valueRepository.save(storage128GB);


        List<Value> productValues = valueRepository.findAllByAndProduct(product);

        Assertions.assertEquals("Smartphones", smartphones.getName());
        Assertions.assertEquals("iPhone 15", product.getName());
        Assertions.assertEquals(2, productValues.size());
    }

    @Test
    void findProductsByValue_Test2() {
        smartphones = new Category();
        smartphones.setName("Smartphones");
        smartphones = categoryRepository.save(smartphones);

        laptops = new Category();
        laptops.setName("Laptops");
        laptops = categoryRepository.save(laptops);

        Product iphone = new Product();
        iphone.setCategory(smartphones);
        iphone.setName("iPhone 12");
        productRepository.save(iphone);

        Product macBook = new Product();
        macBook.setCategory(laptops);
        macBook.setName("MacBook Pro 14");
        productRepository.save(macBook);

        Option manufacturerOptionSmartphone = new Option();
        manufacturerOptionSmartphone.setName("Manufacturer");
        manufacturerOptionSmartphone.setCategory(smartphones);
        optionRepository.save(manufacturerOptionSmartphone);

        Option manufacturerOptionLaptop = new Option();
        manufacturerOptionLaptop.setName("Manufacturer");
        manufacturerOptionLaptop.setCategory(laptops);
        optionRepository.save(manufacturerOptionLaptop);

        Option storageOptionSmartphone = new Option();
        storageOptionSmartphone.setName("Storage");
        storageOptionSmartphone.setCategory(smartphones);
        optionRepository.save(storageOptionSmartphone);

        Option storageOptionLaptop = new Option();
        storageOptionLaptop.setName("Storage");
        storageOptionLaptop.setCategory(laptops);
        optionRepository.save(storageOptionLaptop);

        Value manufacturerValueIphone = new Value();
        manufacturerValueIphone.setProduct(iphone);
        manufacturerValueIphone.setName("Apple");
        manufacturerValueIphone.setOption(manufacturerOptionSmartphone);
        valueRepository.save(manufacturerValueIphone);

        Value storageValueIphone = new Value();
        storageValueIphone.setProduct(iphone);
        storageValueIphone.setName("128GB");
        storageValueIphone.setOption(storageOptionSmartphone);
        valueRepository.save(storageValueIphone);

        Value manufacturerValueMacBook = new Value();
        manufacturerValueMacBook.setProduct(macBook);
        manufacturerValueMacBook.setName("Apple");
        manufacturerValueMacBook.setOption(manufacturerOptionLaptop);
        valueRepository.save(manufacturerValueMacBook);

        Value storageValueMacBook = new Value();
        storageValueMacBook.setProduct(macBook);
        storageValueMacBook.setName("512GB");
        storageValueMacBook.setOption(storageOptionLaptop);
        valueRepository.save(storageValueMacBook);


        List<Product> appleProducts = productRepository.findAllByValueListAndName("Apple");
        Assertions.assertEquals(2, appleProducts.size());

        List<Product> gb512 = productRepository.findAllByValueListAndName("512GB");
        Assertions.assertEquals("MacBook Pro 14", gb512.get(0).getName());

    }

}

