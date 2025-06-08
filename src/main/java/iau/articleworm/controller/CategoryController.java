package iau.articleworm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.dto.Category.CategoryDto;
import iau.articleworm.dto.Category.CategoryListDto;
import iau.articleworm.service.concrete.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            categoryService.createCategory(categoryDto);
            return ResponseEntity.ok("Category created");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + ex.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryListDto>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    // NOT working yet, need to fix the delete method in CategoryService
    @DeleteMapping("/delete") // This should be used very carefully
    // as it deletes categories based on the name and there might be some dependencies with other entities.
    public String deleteCategory(String categoryName) {
        return categoryService.deleteCategory(categoryName);
    }

    @GetMapping("/getCategoryIdByName/{categoryName}")
    public ResponseEntity<Integer> getCategoryIdByName(@PathVariable String categoryName) {
        try {
            Integer categoryId = categoryService.getCategoryIdByName(categoryName);
            return ResponseEntity.ok(categoryId);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // NOT USABLE YET
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Integer categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting category: " + ex.getMessage());
        }
    }


}
