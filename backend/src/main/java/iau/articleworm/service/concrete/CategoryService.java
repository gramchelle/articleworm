package iau.articleworm.service.concrete;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iau.articleworm.dto.Category.CategoryDto;
import iau.articleworm.dto.Category.CategoryListDto;
import iau.articleworm.model.Category;
import iau.articleworm.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void createCategory(CategoryDto categoryDto) {
        if (categoryDto.getCategoryName() == null || categoryDto.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        categoryRepository.save(category);
    }

    public List<CategoryListDto> listCategories() {
        return categoryRepository.findAll().stream()
            .map(category -> new CategoryListDto(category.getCategoryId(), category.getCategoryName()))
            .collect(Collectors.toList());
    }

    public String deleteCategory(String categoryName) {
        if (!categoryRepository.existsByCategoryName(categoryName)) {
            return "Category does not exist";
        }
        
        categoryRepository.deleteByCategoryName(categoryName);
        return "Category deleted successfully";
    }

    public Integer getCategoryIdByName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));
        return category.getCategoryId();
    }

    public void deleteCategoryById(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found with ID: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

}
