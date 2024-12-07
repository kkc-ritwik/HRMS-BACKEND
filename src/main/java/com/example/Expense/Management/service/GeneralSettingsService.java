package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.CategoryDTO;
import com.example.Expense.Management.dto.SubCategoryDTO;
import com.example.Expense.Management.dto.TaxDTO;

import java.util.List;

public interface GeneralSettingsService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<SubCategoryDTO> getSubcategoriesByCategory(String categoryId);
    SubCategoryDTO createSubcategory(SubCategoryDTO subCategoryDTO);
    List<TaxDTO> getAllTaxes();
    TaxDTO createTax(TaxDTO taxDTO);
}
