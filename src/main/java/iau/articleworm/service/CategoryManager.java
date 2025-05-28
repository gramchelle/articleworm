package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.business.abstracts.CategoryService;
import iau.articleworm.entities.abstracts.CategoryDao;

@Service
public class CategoryManager implements CategoryService{

    private CategoryDao categoryDao;

    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<String> getAllCategories() {
        return categoryDao.findAll().stream()
                .map(category -> category.getCategoryName())
                .toList();
    }

}
