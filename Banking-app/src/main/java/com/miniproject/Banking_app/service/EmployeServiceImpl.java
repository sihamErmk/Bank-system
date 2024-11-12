package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.EmployeDto;
import com.miniproject.Banking_app.entity.Employe;
import com.miniproject.Banking_app.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe createEmploye(EmployeDto employeDto) {
        Employe employe = new Employe();
        employe.setNom(employeDto.getNom());  // Utilisation de getNom() après vérification
        return employeRepository.save(employe);
    }


    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll(); // Retourner tous les employés
    }
}
