package com.miniproject.Banking_app.repository;

import com.miniproject.Banking_app.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeRepository extends JpaRepository<Employe,Long> {

        }
