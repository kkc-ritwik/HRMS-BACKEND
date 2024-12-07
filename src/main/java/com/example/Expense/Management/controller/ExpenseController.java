package com.example.Expense.Management.controller;

import com.example.Expense.Management.config.ApiVersionConfig;
import com.example.Expense.Management.dto.ExpenseDto;
import com.example.Expense.Management.entity.Expense;
import com.example.Expense.Management.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/expenses")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addExpense(
            @RequestParam("expenseDto") String expenseDtoJson,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ExpenseDto expenseDto = mapper.readValue(expenseDtoJson, ExpenseDto.class);

            if (attachment != null && !attachment.isEmpty()) {
                expenseDto.setAttachmentData(attachment.getBytes());
                expenseDto.setAttachmentName(attachment.getOriginalFilename());
                expenseDto.setAttachmentType(attachment.getContentType());
            }

            Expense expense = expenseService.addExpense(expenseDto);
            return ResponseEntity.ok(expense);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process attachment: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add expense: " + e.getMessage());
        }
    }

    @GetMapping
public ResponseEntity<?> getAllExpenses() {
    try {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(expenses);
    } catch (Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Collections.singletonMap("error", e.getMessage()));
    }
}


    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable String id) {
        try {
            Expense expense = expenseService.getExpenseById(id);
            return ResponseEntity.ok(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get expense: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/attachment")
    public ResponseEntity<?> getAttachment(@PathVariable String id) {
        try {
            Expense expense = expenseService.getExpenseById(id);
            if (expense.getAttachmentData() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + expense.getAttachmentName() + "\"")
                    .contentType(MediaType.parseMediaType(expense.getAttachmentType()))
                    .body(expense.getAttachmentData());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get attachment: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(
            @PathVariable String id,
            @RequestParam("expenseDto") String expenseDtoJson,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ExpenseDto expenseDto = mapper.readValue(expenseDtoJson, ExpenseDto.class);

            // Only update attachment if a new one is provided
            if (attachment != null && !attachment.isEmpty()) {
                expenseDto.setAttachmentData(attachment.getBytes());
                expenseDto.setAttachmentName(attachment.getOriginalFilename());
                expenseDto.setAttachmentType(attachment.getContentType());
            }

            Expense expense = expenseService.updateExpense(id, expenseDto);
            return ResponseEntity.ok(expense);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process attachment: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update expense: " + e.getMessage());
        }
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateExpenseStatus(@PathVariable String id, @RequestBody Map<String, String> statusUpdate) {
        try {
            String newStatus = statusUpdate.get("status");
            if (newStatus == null || newStatus.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Status cannot be empty");
            }
            Expense updatedExpense = expenseService.updateExpenseStatus(id, newStatus);
            return ResponseEntity.ok(updatedExpense);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update expense status: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete expense: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}