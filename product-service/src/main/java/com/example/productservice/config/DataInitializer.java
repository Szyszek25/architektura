package com.example.productservice.config;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product("Laptop Dell XPS 15", "Laptop z procesorem i7, 16GB RAM, 512GB SSD", 5499.99, 10));
                repository.save(new Product("iPhone 15 Pro", "Smartfon Apple z chipem A17 Pro", 5999.00, 25));
                repository.save(new Product("Samsung Galaxy S24", "Smartfon Samsung z AI", 4299.00, 30));
                repository.save(new Product("Sony WH-1000XM5", "Sluchawki bezprzewodowe z ANC", 1499.00, 50));
                repository.save(new Product("Logitech MX Master 3S", "Mysz bezprzewodowa ergonomiczna", 449.99, 100));
                System.out.println("Zaladowano przykladowe produkty do bazy danych.");
            }
        };
    }
}
