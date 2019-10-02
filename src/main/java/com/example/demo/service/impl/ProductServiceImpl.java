package com.example.demo.service.impl;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.PriceListService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final PriceListService priceListService;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, PriceListService priceListService) {
        this.repository = repository;
        this.priceListService = priceListService;
    }

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        Optional<Product> optionalProduct = repository.findById(id);
        optionalProduct.ifPresent(this::setActualPrice);
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = repository.findAll();
        products.forEach(this::setActualPrice);
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Product entity) {
        repository.delete(entity);
    }

    private void setActualPrice(Product product) {
        Optional<PriceList> optionalPriceList =
                priceListService.findFirstByTypeAndDateFromLessThanEqualAndDateToGreaterThanEqual(new Date(), product.getType());
        if (optionalPriceList.isPresent()){
            product.updateActualPrice(optionalPriceList.get().getCoefficient());
        } else {
            product.updateActualPrice(1d);
        }
        //optionalPriceList.map(PriceList::getCoefficient).ifPresent(product::updateActualPrice);
    }

}
