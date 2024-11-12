package com.miniproject.Banking_app.repository;

import com.miniproject.Banking_app.entity.Operation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OperationRepository extends JpaRepository<Operation,Long>{

}
