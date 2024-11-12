package com.miniproject.Banking_app.web;

import com.miniproject.Banking_app.dto.EmployeDto;
import com.miniproject.Banking_app.entity.Employe;
import com.miniproject.Banking_app.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeRestController {

    private final EmployeService employeService;

    @Autowired
    public EmployeRestController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employe> createEmploye(@RequestBody EmployeDto employeDto) {
        Employe createdEmploye = employeService.createEmploye(employeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmploye);
    }

    @GetMapping("/employees")
    public List<Employe> findAll() {
        return this.employeService.findAll();
    }
}
