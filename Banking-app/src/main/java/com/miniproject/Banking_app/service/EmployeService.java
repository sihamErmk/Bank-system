package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.EmployeDto;
import com.miniproject.Banking_app.entity.Employe;

import java.util.List;

public interface EmployeService {
    Employe createEmploye(EmployeDto employeDto);  // Utilise EmployeDto
    List<Employe> findAll();
}
