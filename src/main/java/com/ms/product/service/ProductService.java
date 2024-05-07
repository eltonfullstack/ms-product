package com.ms.product.service;

import com.ms.product.entity.Product;
import com.ms.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        Product createdProduct = productRepository.save(product);
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

//    @Transactional
//    public Product update(Long id, Product product) {
//        product.setId(id);
//        return productRepository.save(product);
//    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, String name, String image, String description, Double price) {
        Product product = findById(id);
        product.setName(name);
        product.setImage(image);
        product.setDescription(description);
        product.setPrice(price);
        return productRepository.save(product);
    }
}
