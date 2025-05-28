package iau.articleworm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.service.CategoryService;
import iau.articleworm.repository.CategoryRepository;

@Service
public class CategoryManager{

    private final CategoryRepository categoryDao;

    @Autowired
    public CategoryManager(CategoryRepository categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<String> getAllCategories() {
        return categoryDao.findAll().stream()
                .map(category -> category.getCategoryName())
                .toList();
    }

}
