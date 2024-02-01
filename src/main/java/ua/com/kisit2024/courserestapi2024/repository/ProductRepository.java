package ua.com.kisit2024.courserestapi2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit2024.courserestapi2024.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
