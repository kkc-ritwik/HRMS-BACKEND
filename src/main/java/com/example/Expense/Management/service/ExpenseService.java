package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ExpenseDto;
import com.example.Expense.Management.entity.Expense;
import java.util.List;

public interface ExpenseService {
    Expense addExpense(ExpenseDto expenseDto);
    List<Expense> getAllExpenses();
    Expense getExpenseById(String id);
    Expense updateExpense(String id, ExpenseDto expenseDto);
    void deleteExpense(String id);
    Expense updateExpenseStatus(String id, String newStatus); // New method
}