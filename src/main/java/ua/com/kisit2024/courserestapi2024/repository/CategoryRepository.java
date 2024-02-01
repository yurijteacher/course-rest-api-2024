package ua.com.kisit2024.courserestapi2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit2024.courserestapi2024.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByName(String name);

}
