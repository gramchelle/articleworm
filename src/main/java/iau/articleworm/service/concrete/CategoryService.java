package iau.articleworm.service.concrete;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iau.articleworm.dto.Category.CategoryDto;
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

    public String listCategories() {
        StringBuilder categoriesList = new StringBuilder("Categories:\n");
        categoryRepository.findAll().forEach(category -> 
            categoriesList.append(category.getCategoryName()).append("\n")
        );
        return categoriesList.toString();
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
