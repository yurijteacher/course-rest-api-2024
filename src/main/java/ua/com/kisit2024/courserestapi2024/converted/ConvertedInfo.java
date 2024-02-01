package ua.com.kisit2024.courserestapi2024.converted;

import org.springframework.stereotype.Component;
import ua.com.kisit2024.courserestapi2024.dto.CategoryDto;
import ua.com.kisit2024.courserestapi2024.entity.Category;

import java.util.List;

@Component
public class ConvertedInfo {

    public CategoryDto mapCategoryToDto(Category category){

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setImg(category.getImg());

        return categoryDto;
    }

    public List<CategoryDto> mapCategoriesToListDao(List<Category> categories){
         return categories.stream().map(entity -> mapCategoryToDto(entity)).toList();
    }


    public Category mapDtoToCategory(CategoryDto categoryDto){

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setImg(categoryDto.getImg());
        category.setDescription(categoryDto.getDescription());

        return category;
    }


}
