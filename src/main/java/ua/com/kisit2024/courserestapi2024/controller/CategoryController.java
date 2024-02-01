package ua.com.kisit2024.courserestapi2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kisit2024.courserestapi2024.converted.ConvertedInfo;
import ua.com.kisit2024.courserestapi2024.dto.CategoryDto;
import ua.com.kisit2024.courserestapi2024.entity.Category;
import ua.com.kisit2024.courserestapi2024.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final ConvertedInfo convertedInfo;
    private final CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        try {
            List<Category> categories;
            categories = categoryRepository.findAll();

            return (categories.isEmpty())
                    ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(convertedInfo.mapCategoriesToListDao(categories), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryDaoById(@PathVariable("id") Long id){

        Optional<Category> category = categoryRepository.findById(id);

        return (category.isPresent()) ?
                new ResponseEntity<>(convertedInfo.mapCategoryToDto(category.get()), HttpStatus.OK )
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping("/category")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto){

        try {
            Category category = convertedInfo.mapDtoToCategory(categoryDto);
            Category category1 = categoryRepository.save(category);
            CategoryDto categoryDto1 = convertedInfo.mapCategoryToDto(category1);

            return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Long id,
                                              @RequestBody CategoryDto categoryDto){

        try {

            Optional<Category> categoryById = categoryRepository.findById(id);
            if (categoryById.isPresent()) {
                Category category = convertedInfo.mapDtoToCategory(categoryDto);
                Category category1 = categoryRepository.save(category);
                CategoryDto categoryDto1 = convertedInfo.mapCategoryToDto(category1);
                return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long id){
        try {
            categoryRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/category")
    public ResponseEntity<HttpStatus> deleteAllCategory(){
        try {
            categoryRepository.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
