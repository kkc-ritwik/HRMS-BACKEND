package com.texsmartly.PayrollPage.service.impl;

import com.texsmartly.PayrollPage.dto.Form16ADTO;
import com.texsmartly.PayrollPage.model.payroll.Form16A;
import com.texsmartly.PayrollPage.repository.payroll.Form16ARepository;
import com.texsmartly.PayrollPage.service.interf.Form16Aservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Form16AServiceImpl implements Form16Aservice {
    @Autowired
    private Form16ARepository form16ARepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Form16A saveForm16A(Form16ADTO form16ADto) {
        // Using ModelMapper to map DTO to Entity
        Form16A form16A = modelMapper.map(form16ADto, Form16A.class);

        // Save the mapped entity to the database
        return form16ARepository.save(form16A);
    }

    @Override
    public Form16ADTO getForm16AById(String id) {
        Optional<Form16A> form16AOptional = form16ARepository.findById(id);
        if (form16AOptional.isPresent()) {
            return modelMapper.map(form16AOptional.get(), Form16ADTO.class);
        } else {
            throw new RuntimeException("Form16A not found with id: " + id); // You can replace with custom exception
        }
    }

    @Override
    public List<Form16ADTO> getAllForm16A() {
        List<Form16A> form16AList = form16ARepository.findAll();
        return form16AList.stream()
                .map(form16A -> modelMapper.map(form16A, Form16ADTO.class))
                .toList();
    }
}
