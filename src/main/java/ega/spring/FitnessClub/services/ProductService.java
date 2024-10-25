package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.Product;
import ega.spring.FitnessClub.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(int product_id) {
        if (productRepository.existsById(product_id)) {
            return productRepository.findById(product_id).get();
        }
        return null;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
