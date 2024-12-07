//package com.example.Expense.Management.service;
//
//import com.example.Expense.Management.dto.ExpenseDto;
//import com.example.Expense.Management.entity.Expense;
//import com.example.Expense.Management.repository.ExpenseRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ExpenseServiceImpl implements ExpenseService {
//
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    @Override
//    public Expense addExpense(ExpenseDto expenseDto) {
//        Expense expense = new Expense();
//        BeanUtils.copyProperties(expenseDto, expense);
//        return expenseRepository.save(expense);
//    }
//
//    @Override
//    public List<Expense> getAllExpenses() {
//        return expenseRepository.findAll();
//    }
//
//    @Override
//    public Expense getExpenseById(String id) {
//        return expenseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//    }
//
//    @Override
//    public Expense updateExpense(String id, ExpenseDto expenseDto) {
//        Expense existingExpense = expenseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//
//        BeanUtils.copyProperties(expenseDto, existingExpense);
//        existingExpense.setId(id);
//        return expenseRepository.save(existingExpense);
//    }
//
//    @Override
//    public void deleteExpense(String id) {
//        expenseRepository.deleteById(id);
//    }
//    @Override
//    public Expense updateExpenseStatus(String id, String newStatus) {
//        Expense existingExpense = expenseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//        existingExpense.setStatus(newStatus);
//        return expenseRepository.save(existingExpense);
//    }
//
//}


package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ExpenseDto;
import com.example.Expense.Management.entity.Expense;
import com.example.Expense.Management.repository.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Expense addExpense(ExpenseDto expenseDto) {
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public Expense updateExpense(String id, ExpenseDto expenseDto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        modelMapper.map(expenseDto, existingExpense); // Map updated fields from expenseDto to existingExpense
        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpenseStatus(String id, String newStatus) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        existingExpense.setStatus(newStatus);
        return expenseRepository.save(existingExpense);
    }
}
