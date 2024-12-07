package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.CategoryDTO;
import com.example.Expense.Management.dto.SubCategoryDTO;
import com.example.Expense.Management.dto.TaxDTO;
import com.example.Expense.Management.entity.Category;
import com.example.Expense.Management.entity.SubCategory;
import com.example.Expense.Management.entity.Tax;
import com.example.Expense.Management.repository.CategoryRepository;
import com.example.Expense.Management.repository.SubCategoryRepository;
import com.example.Expense.Management.repository.TaxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GeneralSettingsServiceImpl implements GeneralSettingsService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(this::convertToCategoryDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching categories: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch categories");
        }
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = new Category();
            BeanUtils.copyProperties(categoryDTO, category);
            category = categoryRepository.save(category);
            return convertToCategoryDTO(category);
        } catch (Exception e) {
            log.error("Error creating category: {}", e.getMessage());
            throw new RuntimeException("Failed to create category");
        }
    }

    @Override
    public List<SubCategoryDTO> getSubcategoriesByCategory(String categoryId) {
        try {
            List<SubCategory> subCategories = subCategoryRepository.findByCategoryId(categoryId);
            return subCategories.stream()
                    .map(this::convertToSubCategoryDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching subcategories for category {}: {}", categoryId, e.getMessage());
            throw new RuntimeException("Failed to fetch subcategories");
        }
    }

    @Override
    public SubCategoryDTO createSubcategory(SubCategoryDTO subCategoryDTO) {
        try {
            SubCategory subCategory = new SubCategory();
            BeanUtils.copyProperties(subCategoryDTO, subCategory);
            subCategory = subCategoryRepository.save(subCategory);
            return convertToSubCategoryDTO(subCategory);
        } catch (Exception e) {
            log.error("Error creating subcategory: {}", e.getMessage());
            throw new RuntimeException("Failed to create subcategory");
        }
    }

    @Override
    public List<TaxDTO> getAllTaxes() {
        try {
            List<Tax> taxes = taxRepository.findAll();
            return taxes.stream()
                    .map(this::convertToTaxDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching taxes: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch taxes");
        }
    }

    @Override
    public TaxDTO createTax(TaxDTO taxDTO) {
        try {
            Tax tax = new Tax();
            BeanUtils.copyProperties(taxDTO, tax);
            tax = taxRepository.save(tax);
            return convertToTaxDTO(tax);
        } catch (Exception e) {
            log.error("Error creating tax: {}", e.getMessage());
            throw new RuntimeException("Failed to create tax");
        }
    }

    // Private helper methods for conversion
    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

    private SubCategoryDTO convertToSubCategoryDTO(SubCategory subCategory) {
        SubCategoryDTO dto = new SubCategoryDTO();
        BeanUtils.copyProperties(subCategory, dto);
        return dto;
    }

    private TaxDTO convertToTaxDTO(Tax tax) {
        TaxDTO dto = new TaxDTO();
        BeanUtils.copyProperties(tax, dto);
        return dto;
    }
}
