package ua.com.kisit2024.courserestapi2024.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private String img;

}
