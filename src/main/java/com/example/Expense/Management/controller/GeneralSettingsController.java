package com.example.Expense.Management.controller;

import com.example.Expense.Management.config.ApiVersionConfig;
import com.example.Expense.Management.dto.CategoryDTO;
import com.example.Expense.Management.dto.SubCategoryDTO;
import com.example.Expense.Management.dto.TaxDTO;
import com.example.Expense.Management.service.GeneralSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/general-settings")
@CrossOrigin("*")
public class GeneralSettingsController {

    @Autowired
    private GeneralSettingsService generalSettingsService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(generalSettingsService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(generalSettingsService.createCategory(categoryDTO));
    }

    @GetMapping("/subcategories/{categoryId}")
    public ResponseEntity<List<SubCategoryDTO>> getSubcategoriesByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(generalSettingsService.getSubcategoriesByCategory(categoryId));
    }

    @PostMapping("/subcategories")
    public ResponseEntity<SubCategoryDTO> createSubcategory(@RequestBody SubCategoryDTO subCategoryDTO) {
        return ResponseEntity.ok(generalSettingsService.createSubcategory(subCategoryDTO));
    }

    @GetMapping("/taxes")
    public ResponseEntity<List<TaxDTO>> getAllTaxes() {
        return ResponseEntity.ok(generalSettingsService.getAllTaxes());
    }

    @PostMapping("/taxes")
    public ResponseEntity<TaxDTO> createTax(@RequestBody TaxDTO taxDTO) {
        return ResponseEntity.ok(generalSettingsService.createTax(taxDTO));
    }
}
