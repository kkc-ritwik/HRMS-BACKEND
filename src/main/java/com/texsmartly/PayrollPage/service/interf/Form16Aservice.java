package com.texsmartly.PayrollPage.service.interf;

import com.texsmartly.PayrollPage.dto.Form16ADTO;
import com.texsmartly.PayrollPage.model.payroll.Form16A;

import java.util.List;

public interface Form16Aservice {

    Form16A saveForm16A(Form16ADTO form16ADto);

    Form16ADTO getForm16AById(String id);

    List<Form16ADTO> getAllForm16A();
}
